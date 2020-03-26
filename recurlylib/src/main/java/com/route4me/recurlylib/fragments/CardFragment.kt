package com.route4me.recurlylib.fragments

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import com.braintreepayments.cardform.view.CardForm
import com.recurly.v3.ApiException
import com.recurly.v3.QueryParams
import com.recurly.v3.requests.*
import com.route4me.recurlylib.R
import com.route4me.recurlylib.api.RecurlyAPIClient
import com.route4me.recurlylib.api.RoutePlan
import com.route4me.recurlylib.model.RecurlyPreferences
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.subscription_fragment.*

class CardFragment : Fragment() {

    companion object {
        const val TAG: String = "CardFragment"
        const val SUBSCRIPTION_ID: String = "SUBSCRIPTION_ID"
    }

    private lateinit var apiClient: RecurlyAPIClient

    private var disposables: MutableList<Disposable> = mutableListOf()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.subscription_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        apiClient = RecurlyAPIClient.getInstance(RecurlyPreferences(context!!).apiKey!!)
        initCardForm()
        val billingAddress = readAddress(arguments)
        continueBtn.setOnClickListener {
            if (cardForm.isValid) {
                progressBar.visibility = View.VISIBLE
                createSubscription(buildStubAccount(RecurlyPreferences(context!!).accountCode!!, billingAddress))
            } else {
                showToast("Please check fields.")
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        for (disposable in disposables) {
            disposable.dispose()
        }
    }

    private fun readAddress(arguments: Bundle?): Address {
        return Address().apply {
            country = arguments?.getString(AddressFragment.COUNTRY).toString()
            city = arguments?.getString(AddressFragment.CITY).toString()
            region = arguments?.getString(AddressFragment.REGION).toString()
            street1 = arguments?.getString(AddressFragment.STREET).toString()
        }
    }

    private fun initCardForm() {
        cardForm.cardRequired(true)
            .cvvRequired(true)
            .cardholderName(CardForm.FIELD_REQUIRED)
            .postalCodeRequired(true)
            .actionLabel("Subscribe")
            .setup(activity as AppCompatActivity)
    }

    private fun buildStubAccount(accountCode: String, billingAddress: Address): AccountCreate {
        val billingInfoCreate = BillingInfoCreate()
        billingAddress.postalCode = cardForm.postalCode
        billingInfoCreate.apply {
            firstName = cardForm.cardholderName.split(" ")[0]
            lastName = cardForm.cardholderName.split(" ")[1]
            address = billingAddress
            cvv = cardForm.cvv
            number = cardForm.cardNumber
        }

        return AccountCreate().apply {
            code = accountCode // -> unique buyer identifier code
            //address = accountBillingInfo.address
            billingInfo = billingInfoCreate
        }

    }

    @SuppressLint("CheckResult")
    private fun createSubscription(buyerAccount: AccountCreate) {
        val createSubscription = apiClient.createAccount(buyerAccount)
            .flatMap {
                val subscriptionCreate = SubscriptionCreate()
                val customerAccount = AccountCreate()
                customerAccount.apply {
                    code = it.code // -> unique client identifier code
                }
                subscriptionCreate.apply {
                    account = customerAccount
                    currency = "USD"
                    unitAmount = 55f
                    planCode = RoutePlan.PLAN_VOICE_YEAR.code
                }
                apiClient.createSubscription(subscriptionCreate)
            }
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                progressBar.visibility = View.GONE
                Log.d(TAG, "Subscription :: $it")
                showToast("Congrats, subscribed successfully!")
                navigateSubscriptionStatusFragment(it.id)

            },
                {
                    progressBar.visibility = View.GONE
                    Log.e(TAG, "Error :: $it")
                    showToast("Failed to subscribe: ${(it as ApiException).error.message}")
                })
        disposables.add(createSubscription)
    }

    @SuppressLint("CheckResult")
    private fun testCreatePurchase(buyerAccount: AccountCreate) {
        val createPurchase = apiClient.createAccount(buyerAccount)
            .flatMap {
                val purchase = PurchaseCreate()
                val accountPurchase = AccountPurchase()

                accountPurchase.apply {
                    code = buyerAccount.code
                }

                val purchasedSubscriptions: MutableList<SubscriptionPurchase> = ArrayList()
                val sub = SubscriptionPurchase()
                sub.planCode = RoutePlan.PLAN_YEAR.code
                purchasedSubscriptions.add(sub)

                purchase.apply {
                    currency = "USD"
                    account = accountPurchase
                    subscriptions = purchasedSubscriptions
                }
                apiClient.createPurchase(purchase)
            }
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                progressBar.visibility = View.GONE
                Log.d(TAG, "InvoiceCollection :: $it")
            },
                {
                    progressBar.visibility = View.GONE
                    Log.e(TAG, "Error :: $it")
                    showToast("Failed to purchase.")
                })
        disposables.add(createPurchase)
    }

    //Here is a way to check subscription status for the client
    @SuppressLint("CheckResult")
    private fun testListAccountSubscriptions(id: String) {
        val params = QueryParams()
        params.setLimit(200)
        val listAccountSubscriptions = apiClient.listAccountTransactions(id, params)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                progressBar.visibility = View.GONE
                Log.d(TAG, "Result: $it")
            },
                {
                    progressBar.visibility = View.GONE
                    Log.e(TAG, "Error :: $it")
                    showToast("Failed to load.")
                })
        disposables.add(listAccountSubscriptions)
    }

    private fun navigateSubscriptionStatusFragment(id: String) {
        val transaction: FragmentTransaction = activity?.supportFragmentManager!!.beginTransaction()
        val subscriptionFragment: DialogFragment = SubscriptionStatusDialog()
        val bundle = Bundle()
        bundle.apply {
            putString(SUBSCRIPTION_ID, id)
        }
        subscriptionFragment.arguments = bundle
        subscriptionFragment.show(transaction, SubscriptionStatusDialog.TAG)
    }

    private fun showToast(message: String) {
        val layout: View = layoutInflater.inflate(
            R.layout.toast_layout,
            view?.findViewById(R.id.toastContainer) as ViewGroup?
        )
        (layout.findViewById(R.id.toastContainer) as TextView).text = message
        Toast(context).apply {
            setGravity(Gravity.BOTTOM, 0, 100)
            duration = Toast.LENGTH_LONG
            view = layout
        }.show()
    }
}
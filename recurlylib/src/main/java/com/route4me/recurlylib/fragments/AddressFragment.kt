package com.route4me.recurlylib.fragments

import android.graphics.Typeface
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import com.route4me.recurlylib.R
import com.route4me.recurlylib.RecurlyAPI.Companion.ACCOUNT_CODE
import com.route4me.recurlylib.RecurlyAPI.Companion.API_KEY
import com.route4me.recurlylib.model.RecurlyPreferences
import kotlinx.android.synthetic.main.address_fragment.*

class AddressFragment : Fragment() {

    companion object {
        const val TAG: String = "AddressFragment"
        const val COUNTRY: String = "COUNTRY"
        const val CITY: String = "CITY"
        const val REGION: String = "REGION"
        const val STREET: String = "STREET"
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.address_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        RecurlyPreferences(context!!).apiKey = arguments!!.getString(API_KEY)!!
        RecurlyPreferences(context!!).accountCode = arguments!!.getString(ACCOUNT_CODE)!!
        countryPicker.setTypeFace(Typeface.createFromAsset(context!!.assets, "fonts/OpenSansRegular.ttf"))
        continueBtn.setOnClickListener { navigateToBillingInfo() }
    }

    private fun navigateToBillingInfo() {
        val transaction: FragmentTransaction = activity?.supportFragmentManager!!.beginTransaction()
        val cardFragment: Fragment = CardFragment()
        val bundle = Bundle()
        bundle.apply {
            putString(COUNTRY, countryPicker.defaultCountryNameCode)
            putString(CITY, city.text.toString())
            putString(REGION, region.text.toString())
            putString(STREET, street.text.toString())
        }
        cardFragment.arguments = bundle
        transaction.apply {
            setCustomAnimations(android.R.animator.fade_in, android.R.animator.fade_out)
            add(R.id.container, cardFragment, CardFragment.TAG)
            addToBackStack(TAG)
            commit()
        }
    }

}
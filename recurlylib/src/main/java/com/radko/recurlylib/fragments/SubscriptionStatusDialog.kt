package com.radko.recurlylib.fragments

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.DialogFragment
import com.radko.recurlylib.R
import com.radko.recurlylib.api.RecurlyAPIClient
import com.radko.recurlylib.model.RecurlyPreferences
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.subscription_status_fragment.*

class SubscriptionStatusDialog : DialogFragment() {

    companion object {
        const val TAG: String = "SubscriptionStatus"
    }

    private lateinit var apiClient: RecurlyAPIClient
    private lateinit var disposable: Disposable

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        dialog?.setCanceledOnTouchOutside(false)
        return inflater.inflate(R.layout.subscription_status_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        apiClient = RecurlyAPIClient.getInstance(RecurlyPreferences(context!!).apiKey!!)
        loadDetails(arguments?.getString(CardFragment.SUBSCRIPTION_ID)!!)
        okBtn.setOnClickListener { dismiss() }
    }

    override fun onDestroy() {
        super.onDestroy()
        disposable.dispose()
    }

    @SuppressLint("CheckResult")
    private fun loadDetails(id: String) {
        disposable = apiClient.getSubscription(id)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                progressBar.visibility = View.GONE
                detailsView.bind(it)
                Log.d(TAG, "Result: $it")
            },
                {
                    progressBar.visibility = View.GONE
                    Log.e(TAG, "Error :: $it")
                    Toast.makeText(context, "Failed to load.", Toast.LENGTH_LONG).show()
                })
    }


}
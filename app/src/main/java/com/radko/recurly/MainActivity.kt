package com.radko.recurly

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import com.radko.recurlylib.RecurlyAPI.Companion.ACCOUNT_CODE
import com.radko.recurlylib.RecurlyAPI.Companion.API_KEY
import com.radko.recurlylib.fragments.AddressFragment

class MainActivity : AppCompatActivity() {

    companion object {
        //TODO: for testing, should not be stored in the apk
        const val KEY = "a52e28fd4e304068936bae14560fd23a"
        // Unique user identifier
        const val CODE = "test_customer_83"
    }

    @SuppressLint("CheckResult")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        //put private api key and user code here
        navigateToSubscriptionFlow(KEY, CODE)
    }

    private fun navigateToSubscriptionFlow(apiKey: String, accountCode: String) {
        val transaction: FragmentTransaction = supportFragmentManager.beginTransaction()
        val addressFragment: Fragment = AddressFragment()
        val bundle = Bundle()
        bundle.apply {
            putString(API_KEY, apiKey)
            putString(ACCOUNT_CODE, accountCode)
        }
        addressFragment.arguments = bundle
        transaction.replace(R.id.container, addressFragment)
        transaction.commit()
    }

}

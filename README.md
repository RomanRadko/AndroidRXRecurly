This lib aimed for perfoming subscribing/purchase using [`Recurly`](https://recurly.com/) platform.
Here is a list of all subscription plans:

	- Mobile Free
	- Mobile Unlimeted Annual
	- Mobile Unlimited Month
	- Voice-Guided Annual
	- Voice-Guided Month

As an input should be provided: 
	
	- Private API key of the recurly account. Can be found on the recurly console: 
		https://[*your_site_id*].recurly.com/integrations/api_keys
	
	- User (buyer) identifier.

Thise keys should be forwarded for the entry subscription flow fragment, which is AddressFragment:

```kotlin
    private fun navigateToSubscriptionFlow(apiKey: String, accountCode: String) {
        val transaction: FragmentTransaction = supportFragmentManager.beginTransaction()
        val addressFragment: Fragment = AddressFragment()
        val bundle = Bundle()
        bundle.putString(API_KEY, apiKey)
        bundle.putString(ACCOUNT_CODE, accountCode)
        addressFragment.arguments = bundle
        transaction.replace(R.id.container, addressFragment)
        transaction.commit()
    }
```

Next info should be provided on the entry address screen:
	
	- Country
	- City
	- State 
	- Street

After Billing Details page appears with the next fields:

	- Cardholder Name (first and last name)
	- Card Number
	- CVV 
	- Postal Code

With all info above provided, possible to perform subscription. 

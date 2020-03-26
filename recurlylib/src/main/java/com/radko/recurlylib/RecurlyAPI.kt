package com.radko.recurlylib

import com.recurly.v3.Pager
import com.recurly.v3.QueryParams
import com.recurly.v3.requests.*
import com.recurly.v3.resources.*
import io.reactivex.Single

interface RecurlyAPI {

    companion object {
        const val API_KEY = "API_KEY"
        const val ACCOUNT_CODE = "ACCOUNT_CODE"
    }

    fun getAccount(id: String): Single<Account>

    fun getAccountBalance(id: String): Single<AccountBalance>

    fun getAccountAcquisition(id: String): Single<AccountAcquisition>

    fun getAccountNote(accountId: String, accountNoteId: String): Single<AccountNote>

    fun getActiveCouponRedemption(accountId: String): Single<CouponRedemption>

    fun getAddOn(id: String): Single<AddOn>

    fun getBillingInfo(id: String): Single<BillingInfo>

    fun getCoupon(id: String): Single<Coupon>

    fun getCreditPayment(id: String): Single<CreditPayment>

    fun getCustomFieldDefinition(id: String): Single<CustomFieldDefinition>

    fun getInvoice(id: String): Single<Invoice>

    fun getItem(id: String): Single<Item>

    fun getLineItem(id: String): Single<LineItem>

    fun getSubscriptions(id: String): Single<Pager<Subscription>>

    fun getSubscription(id: String): Single<Subscription>

    fun getSite(id: String): Single<Site>

    fun getPlan(id: String): Single<Plan>

    fun getPlanAddOn(planId: String, addOnId: String): Single<AddOn>

    fun getShippingAddress(accountId: String, shippingAddressId: String): Single<ShippingAddress>

    fun getSubscriptionChange(subscriptionId: String): Single<SubscriptionChange>

    fun getTransaction(id: String): Single<Transaction>

    fun getUniqueCouponCode(id: String): Single<UniqueCouponCode>

    fun createPurchase(body: PurchaseCreate): Single<InvoiceCollection>

    fun createSubscription(body: SubscriptionCreate): Single<Subscription>

    fun createAccount(body: AccountCreate): Single<Account>

    fun createCoupon(body: CouponCreate): Single<Coupon>

    fun createCouponRedemption(
        accountId: String,
        body: CouponRedemptionCreate
    ): Single<CouponRedemption>

    fun createInvoice(accountId: String, body: InvoiceCreate): Single<InvoiceCollection>

    fun createItem(body: ItemCreate): Single<Item>

    fun createLineItem(accountId: String, body: LineItemCreate): Single<LineItem>

    fun createPlan(body: PlanCreate): Single<Plan>

    fun createPlanAddOn(planId: String, body: AddOnCreate): Single<AddOn>

    fun createShippingAddress(
        accountId: String,
        body: ShippingAddressCreate
    ): Single<ShippingAddress>

    fun createSubscriptionChange(
        subscriptionId: String,
        body: SubscriptionChangeCreate
    ): Single<SubscriptionChange>

    fun updateBillingInfo(accountId: String, body: BillingInfoCreate): Single<BillingInfo>

    fun updateAccount(accountId: String, body: AccountUpdate): Single<Account>

    fun updateAccountAcquisition(
        accountId: String,
        body: AccountAcquisitionUpdatable
    ): Single<AccountAcquisition>

    fun updateCoupon(couponId: String, body: CouponUpdate): Single<Coupon>

    fun updateItem(itemId: String, body: ItemUpdate): Single<Item>

    fun updatePlan(planId: String, body: PlanUpdate): Single<Plan>

    fun updatePlanAddOn(planId: String, addOnId: String, body: AddOnUpdate): Single<AddOn>

    fun updateShippingAddress(
        accountId: String,
        shippingAddressId: String,
        body: ShippingAddressUpdate
    ): Single<ShippingAddress>

    fun listAccountAcquisition(queryParams: QueryParams): Single<List<AccountAcquisition>>

    fun listAccountCouponRedemptions(
        accountId: String,
        queryParams: QueryParams
    ): Single<List<CouponRedemption>>

    fun listAccountCreditPayments(
        accountId: String,
        queryParams: QueryParams
    ): Single<List<CreditPayment>>

    fun listAccountInvoices(accountId: String, queryParams: QueryParams): Single<List<Invoice>>

    fun listAccountLineItems(accountId: String, queryParams: QueryParams): Single<List<LineItem>>

    fun listAccountNotes(accountId: String, queryParams: QueryParams): Single<List<AccountNote>>

    fun listAccounts(queryParams: QueryParams): Single<List<Account>>

    fun listAccountSubscriptions(
        accountId: String,
        queryParams: QueryParams
    ): Single<List<Subscription>>

    fun listAccountTransactions(
        accountId: String,
        queryParams: QueryParams
    ): Single<List<Transaction>>

    fun listAddOns(queryParams: QueryParams): Single<List<AddOn>>

    fun listChildAccounts(accountId: String, queryParams: QueryParams): Single<List<Account>>

    fun listCoupons(queryParams: QueryParams): Single<List<Coupon>>

    fun listCreditPayments(queryParams: QueryParams): Single<List<CreditPayment>>

    fun listCustomFieldDefinitions(queryParams: QueryParams): Single<List<CustomFieldDefinition>>

    fun listInvoiceCouponRedemptions(
        invoiceId: String,
        queryParams: QueryParams
    ): Single<List<CouponRedemption>>

    fun listInvoiceLineItems(
        invoiceId: String,
        queryParams: QueryParams
    ): Single<List<LineItem>>

    fun listInvoices(queryParams: QueryParams): Single<List<Invoice>>

    fun listItems(queryParams: QueryParams): Single<List<Item>>

    fun listLineItems(queryParams: QueryParams): Single<List<LineItem>>

    fun listPlanAddOns(planId: String, queryParams: QueryParams): Single<List<AddOn>>

    fun listPlans(queryParams: QueryParams): Single<List<Plan>>

    fun listRelatedInvoices(invoiceId: String): Single<List<Invoice>>

    fun listShippingAddresses(
        accountId: String,
        queryParams: QueryParams
    ): Single<List<ShippingAddress>>

    fun listShippingMethods(queryParams: QueryParams): Single<List<ShippingMethod>>

    fun listSites(queryParams: QueryParams): Single<List<Site>>

    fun listSubscriptionCouponRedemptions(
        subscriptionId: String,
        queryParams: QueryParams
    ): Single<List<CouponRedemption>>

    fun listSubscriptionInvoices(
        subscriptionId: String,
        queryParams: QueryParams
    ): Single<List<Invoice>>

    fun listSubscriptionLineItems(
        subscriptionId: String,
        queryParams: QueryParams
    ): Single<List<LineItem>>

    fun listSubscriptions(queryParams: QueryParams): Single<List<Subscription>>

    fun listTransactions(queryParams: QueryParams): Single<List<Transaction>>

    fun listUniqueCouponCodes(
        couponId: String,
        queryParams: QueryParams
    ): Single<List<UniqueCouponCode>>

    fun reactivateAccount(id: String): Single<Account>

    fun reactivateItem(id: String): Single<Item>

    fun reactivateSubscription(id: String): Single<Subscription>

    fun reactivateUniqueCouponCode(id: String): Single<UniqueCouponCode>

    fun removeAccountAcquisition(id: String)

    fun removeBillingInfo(id: String)

    fun removeCouponRedemption(id: String): Single<CouponRedemption>

    fun removeLineItem(id: String)

    fun removePlan(id: String): Single<Plan>

    fun removePlanAddOn(planId: String, addOnId: String): Single<AddOn>

    fun removeShippingAddress(accountId: String, shippingAddressId: String)

    fun removeSubscriptionChange(id: String)

    fun pauseSubscription(id: String, body: SubscriptionPause): Single<Subscription>

    fun resumeSubscription(id: String): Single<Subscription>

    fun cancelSubscription(id: String): Single<Subscription>

    fun cancelSubscription(id: String, body: SubscriptionCancel): Single<Subscription>

    fun terminateSubscription(id: String, queryParams: QueryParams): Single<Subscription>
}
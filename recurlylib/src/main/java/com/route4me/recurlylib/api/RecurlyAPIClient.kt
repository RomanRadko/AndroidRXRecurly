package com.route4me.recurlylib.api

import com.recurly.v3.Client
import com.recurly.v3.Pager
import com.recurly.v3.QueryParams
import com.recurly.v3.requests.*
import com.recurly.v3.resources.*
import com.route4me.recurlylib.RecurlyAPI
import com.route4me.recurlylib.model.RecurlySingle
import com.route4me.recurlylib.model.SingletonHolder
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class RecurlyAPIClient(private val apiKey: String) :
    RecurlyAPI {

    private var single: RecurlySingle = RecurlySingle(Client(apiKey))

    companion object : SingletonHolder<RecurlyAPIClient, String>({
        RecurlyAPIClient(it)
    })

    override fun getAccount(id: String): Single<Account> {
        return single.get(Account::class, id)
    }

    override fun getAccountBalance(id: String): Single<AccountBalance> {
        return single.get(AccountBalance::class, id)
    }

    override fun getAccountAcquisition(id: String): Single<AccountAcquisition> {
        return single.get(AccountAcquisition::class, id)
    }

    override fun getAccountNote(accountId: String, accountNoteId: String): Single<AccountNote> {
        return single.get(AccountNote::class, accountId, accountNoteId)
    }

    override fun getSubscription(id: String): Single<Subscription> {
        return single.get(Subscription::class, id)
    }

    override fun getPlan(id: String): Single<Plan> {
        return single.get(RoutePlan::class, id)
    }

    override fun getPlanAddOn(planId: String, addOnId: String): Single<AddOn> {
        return single.get(AddOn::class, planId, addOnId)
    }

    override fun getShippingAddress(
        accountId: String,
        shippingAddressId: String
    ): Single<ShippingAddress> {
        return single.get(ShippingAddress::class, accountId, shippingAddressId)
    }

    override fun getSubscriptionChange(subscriptionId: String): Single<SubscriptionChange> {
        return single.get(SubscriptionChange::class, subscriptionId)
    }

    override fun getTransaction(id: String): Single<Transaction> {
        return single.get(Transaction::class, id)
    }

    override fun getUniqueCouponCode(id: String): Single<UniqueCouponCode> {
        return single.get(UniqueCouponCode::class, id)
    }

    override fun getSite(id: String): Single<Site> {
        return single.get(Site::class, id)
    }

    override fun getSubscriptions(id: String): Single<Pager<Subscription>> {
        val params = QueryParams()
        params.params["site_id"] = id
        return Single.fromCallable { Client(apiKey).listSubscriptions(params) }
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
    }

    override fun getActiveCouponRedemption(accountId: String): Single<CouponRedemption> {
        return single.get(CouponRedemption::class, accountId)
    }

    override fun getAddOn(id: String): Single<AddOn> {
        return single.get(AddOn::class, id)
    }

    override fun getBillingInfo(id: String): Single<BillingInfo> {
        return single.get(BillingInfo::class, id)
    }

    override fun getCoupon(id: String): Single<Coupon> {
        return single.get(Coupon::class, id)
    }

    override fun getCreditPayment(id: String): Single<CreditPayment> {
        return single.get(CreditPayment::class, id)
    }

    override fun getCustomFieldDefinition(id: String): Single<CustomFieldDefinition> {
        return single.get(CustomFieldDefinition::class, id)
    }

    override fun getInvoice(id: String): Single<Invoice> {
        return single.get(Invoice::class, id)
    }

    override fun getItem(id: String): Single<Item> {
        return single.get(Item::class, id)
    }

    override fun getLineItem(id: String): Single<LineItem> {
        return single.get(LineItem::class, id)
    }

    override fun createSubscription(body: SubscriptionCreate): Single<Subscription> {
        return single.create(Subscription::class, body)
    }

    override fun createAccount(body: AccountCreate): Single<Account> {
        return single.create(Account::class, body)
    }

    override fun createCoupon(body: CouponCreate): Single<Coupon> {
        return single.create(Coupon::class, body)
    }

    override fun createCouponRedemption(
        accountId: String,
        body: CouponRedemptionCreate
    ): Single<CouponRedemption> {
        return single.create(CouponRedemption::class, accountId, body)
    }

    override fun createInvoice(accountId: String, body: InvoiceCreate): Single<InvoiceCollection> {
        return single.create(InvoiceCollection::class, accountId, body)
    }

    override fun createItem(body: ItemCreate): Single<Item> {
        return single.create(Item::class, body)
    }

    override fun createLineItem(accountId: String, body: LineItemCreate): Single<LineItem> {
        return single.create(LineItem::class, accountId, body)
    }

    override fun createPlan(body: PlanCreate): Single<Plan> {
        return single.create(Plan::class, body)
    }

    override fun createPlanAddOn(planId: String, body: AddOnCreate): Single<AddOn> {
        return single.create(AddOn::class, planId, body)
    }

    override fun createShippingAddress(
        accountId: String,
        body: ShippingAddressCreate
    ): Single<ShippingAddress> {
        return single.create(ShippingAddress::class, accountId, body)
    }

    override fun createSubscriptionChange(
        subscriptionId: String,
        body: SubscriptionChangeCreate
    ): Single<SubscriptionChange> {
        return single.create(SubscriptionChange::class, subscriptionId, body)
    }

    override fun createPurchase(body: PurchaseCreate): Single<InvoiceCollection> {
        return single.create(InvoiceCollection::class, body)
    }

    override fun updateBillingInfo(
        accountId: String,
        body: BillingInfoCreate
    ): Single<BillingInfo> {
        return single.update(BillingInfo::class, accountId, body)
    }

    override fun updateAccount(accountId: String, body: AccountUpdate): Single<Account> {
        return single.update(Account::class, accountId, body)
    }

    override fun updateAccountAcquisition(
        accountId: String,
        body: AccountAcquisitionUpdatable
    ): Single<AccountAcquisition> {
        return single.update(AccountAcquisition::class, accountId, body)
    }

    override fun updateCoupon(couponId: String, body: CouponUpdate): Single<Coupon> {
        return single.update(Coupon::class, couponId, body)
    }

    override fun updateItem(itemId: String, body: ItemUpdate): Single<Item> {
        return single.update(Item::class, itemId, body)
    }

    override fun updatePlan(planId: String, body: PlanUpdate): Single<Plan> {
        return single.update(Plan::class, planId, body)
    }

    override fun updatePlanAddOn(
        planId: String,
        addOnId: String,
        body: AddOnUpdate
    ): Single<AddOn> {
        return single.update(AddOn::class, body, planId, addOnId)
    }

    override fun updateShippingAddress(
        accountId: String,
        shippingAddressId: String,
        body: ShippingAddressUpdate
    ): Single<ShippingAddress> {
        return single.update(ShippingAddress::class, body, accountId, shippingAddressId)
    }

    override fun listAccountAcquisition(queryParams: QueryParams): Single<List<AccountAcquisition>> {
        return single.listAccount(AccountAcquisition::class, queryParams)
    }

    override fun listAccountCouponRedemptions(
        accountId: String,
        queryParams: QueryParams
    ): Single<List<CouponRedemption>> {
        return single.listAccount(CouponRedemption::class, accountId, queryParams)
    }

    override fun listAccountCreditPayments(
        accountId: String,
        queryParams: QueryParams
    ): Single<List<CreditPayment>> {
        return single.listAccount(CreditPayment::class, accountId, queryParams)
    }

    override fun listAccountInvoices(
        accountId: String,
        queryParams: QueryParams
    ): Single<List<Invoice>> {
        return single.listAccount(Invoice::class, accountId, queryParams)
    }

    override fun listAccountLineItems(
        accountId: String,
        queryParams: QueryParams
    ): Single<List<LineItem>> {
        return single.listAccount(LineItem::class, accountId, queryParams)
    }

    override fun listAccountNotes(
        accountId: String,
        queryParams: QueryParams
    ): Single<List<AccountNote>> {
        return single.listAccount(AccountNote::class, accountId, queryParams)
    }

    override fun listAccounts(queryParams: QueryParams): Single<List<Account>> {
        return single.listAccount(Account::class, queryParams)
    }

    override fun listAccountSubscriptions(
        accountId: String,
        queryParams: QueryParams
    ): Single<List<Subscription>> {
        return single.listAccount(Subscription::class, accountId, queryParams)
    }

    override fun listAccountTransactions(
        accountId: String,
        queryParams: QueryParams
    ): Single<List<Transaction>> {
        return single.listAccount(Transaction::class, accountId, queryParams)
    }

    override fun listAddOns(queryParams: QueryParams): Single<List<AddOn>> {
        return single.list(AddOn::class, queryParams)
    }

    override fun listChildAccounts(
        accountId: String,
        queryParams: QueryParams
    ): Single<List<Account>> {
        return single.list(Account::class, accountId, queryParams)
    }

    override fun listCoupons(queryParams: QueryParams): Single<List<Coupon>> {
        return single.list(Coupon::class, queryParams)
    }

    override fun listCreditPayments(queryParams: QueryParams): Single<List<CreditPayment>> {
        return single.list(CreditPayment::class, queryParams)
    }

    override fun listCustomFieldDefinitions(queryParams: QueryParams): Single<List<CustomFieldDefinition>> {
        return single.list(CustomFieldDefinition::class, queryParams)
    }

    override fun listInvoiceCouponRedemptions(
        invoiceId: String,
        queryParams: QueryParams
    ): Single<List<CouponRedemption>> {
        return single.listInvoice(CouponRedemption::class, invoiceId, queryParams)
    }

    override fun listInvoiceLineItems(
        invoiceId: String,
        queryParams: QueryParams
    ): Single<List<LineItem>> {
        return single.listInvoice(LineItem::class, invoiceId, queryParams)
    }

    override fun listInvoices(queryParams: QueryParams): Single<List<Invoice>> {
        return single.list(Invoice::class, queryParams)
    }

    override fun listItems(queryParams: QueryParams): Single<List<Item>> {
        return single.list(Item::class, queryParams)
    }

    override fun listLineItems(queryParams: QueryParams): Single<List<LineItem>> {
        return single.list(LineItem::class, queryParams)
    }

    override fun listPlanAddOns(planId: String, queryParams: QueryParams): Single<List<AddOn>> {
        return single.list(AddOn::class, planId, queryParams)
    }

    override fun listPlans(queryParams: QueryParams): Single<List<Plan>> {
        return single.list(Plan::class, queryParams)
    }

    override fun listRelatedInvoices(invoiceId: String): Single<List<Invoice>> {
        return Single.fromCallable {
            val pager = Client(apiKey).listRelatedInvoices(invoiceId)
            pager.getNextPage()
            return@fromCallable pager.data
        }
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
    }

    override fun listShippingAddresses(
        accountId: String,
        queryParams: QueryParams
    ): Single<List<ShippingAddress>> {
        return single.list(ShippingAddress::class, accountId, queryParams)
    }

    override fun listShippingMethods(queryParams: QueryParams): Single<List<ShippingMethod>> {
        return single.list(ShippingMethod::class, queryParams)
    }

    override fun listSites(queryParams: QueryParams): Single<List<Site>> {
        return single.list(Site::class, queryParams)
    }

    override fun listSubscriptionCouponRedemptions(
        subscriptionId: String,
        queryParams: QueryParams
    ): Single<List<CouponRedemption>> {
        return single.listSubscription(CouponRedemption::class, subscriptionId, queryParams)
    }

    override fun listSubscriptionInvoices(
        subscriptionId: String,
        queryParams: QueryParams
    ): Single<List<Invoice>> {
        return single.listSubscription(Invoice::class, subscriptionId, queryParams)
    }

    override fun listSubscriptionLineItems(
        subscriptionId: String,
        queryParams: QueryParams
    ): Single<List<LineItem>> {
        return single.listSubscription(LineItem::class, subscriptionId, queryParams)
    }

    override fun listSubscriptions(queryParams: QueryParams): Single<List<Subscription>> {
        return single.list(Subscription::class, queryParams)
    }

    override fun listTransactions(queryParams: QueryParams): Single<List<Transaction>> {
        return single.list(Transaction::class, queryParams)
    }

    override fun listUniqueCouponCodes(
        couponId: String,
        queryParams: QueryParams
    ): Single<List<UniqueCouponCode>> {
        return single.list(UniqueCouponCode::class, couponId, queryParams)
    }

    override fun reactivateAccount(id: String): Single<Account> {
        return single.reactivate(Account::class, id)
    }

    override fun reactivateItem(id: String): Single<Item> {
        return single.reactivate(Item::class, id)
    }

    override fun reactivateSubscription(id: String): Single<Subscription> {
        return single.reactivate(Subscription::class, id)
    }

    override fun reactivateUniqueCouponCode(id: String): Single<UniqueCouponCode> {
        return single.reactivate(UniqueCouponCode::class, id)
    }

    override fun removeAccountAcquisition(id: String) {
        return single.remove(AccountAcquisition::class, id)
    }

    override fun removeBillingInfo(id: String) {
        return single.remove(BillingInfo::class, id)
    }

    override fun removeCouponRedemption(id: String): Single<CouponRedemption> {
        return single.removeWithResult(CouponRedemption::class, id)
    }

    override fun removeLineItem(id: String) {
        return single.remove(LineItem::class, id)
    }

    override fun removePlan(id: String): Single<Plan> {
        return single.removeWithResult(Plan::class, id)
    }

    override fun removePlanAddOn(planId: String, addOnId: String): Single<AddOn> {
        return single.removeWithResult(AddOn::class, planId, addOnId)
    }

    override fun removeShippingAddress(accountId: String, shippingAddressId: String) {
        return single.remove(ShippingAddress::class, accountId, shippingAddressId)
    }

    override fun removeSubscriptionChange(id: String) {
        return single.remove(SubscriptionChange::class, id)
    }

    override fun pauseSubscription(id: String, body: SubscriptionPause): Single<Subscription> {
        return Single.fromCallable { Client(apiKey).pauseSubscription(id, body) }
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
    }

    override fun resumeSubscription(id: String): Single<Subscription> {
        return Single.fromCallable { Client(apiKey).resumeSubscription(id) }
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
    }

    override fun cancelSubscription(id: String): Single<Subscription> {
        return Single.fromCallable { Client(apiKey).cancelSubscription(id) }
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
    }

    override fun cancelSubscription(id: String, body: SubscriptionCancel): Single<Subscription> {
        return Single.fromCallable { Client(apiKey).cancelSubscription(id, body) }
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
    }

    override fun terminateSubscription(id: String, queryParams: QueryParams): Single<Subscription> {
        return Single.fromCallable { Client(apiKey).terminateSubscription(id, queryParams) }
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
    }

}
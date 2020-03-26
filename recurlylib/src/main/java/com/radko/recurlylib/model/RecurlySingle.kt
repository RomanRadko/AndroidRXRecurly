package com.radko.recurlylib.model

import android.annotation.SuppressLint
import com.recurly.v3.Client
import com.recurly.v3.QueryParams
import com.recurly.v3.requests.*
import com.recurly.v3.resources.*
import com.radko.recurlylib.api.RoutePlan
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class RecurlySingle(private val client: Client) {

    @Suppress("UNCHECKED_CAST")
    fun <T : Any> create(input: Any, body: Any): (Single<T>) {
        return Single.fromCallable {
            when (input) {
                Account::class -> {
                    client.createAccount(body as AccountCreate)
                }
                Subscription::class -> {
                    client.createSubscription(body as SubscriptionCreate)
                }
                InvoiceCollection::class -> {
                    client.createPurchase(body as PurchaseCreate)
                }
                Coupon::class -> {
                    client.createCoupon(body as CouponCreate)
                }
                Item::class -> {
                    client.createItem(body as ItemCreate)
                }
                Plan::class -> {
                    client.createPlan(body as PlanCreate)
                }
                else -> null
            }
        }
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread()) as Single<T>
    }

    @Suppress("UNCHECKED_CAST")
    fun <T : Any> create(input: Any, id: String, body: Any): (Single<T>) {
        return Single.fromCallable {
            when (input) {
                CouponRedemption::class -> {
                    client.createCouponRedemption(id, body as CouponRedemptionCreate)
                }
                InvoiceCollection::class -> {
                    client.createInvoice(id, body as InvoiceCreate)
                }
                LineItem::class -> {
                    client.createLineItem(id, body as LineItemCreate)
                }
                AddOn::class -> {
                    client.createPlanAddOn(id, body as AddOnCreate)
                }
                ShippingAddress::class -> {
                    client.createShippingAddress(id, body as ShippingAddressCreate)
                }
                SubscriptionChange::class -> {
                    client.createSubscriptionChange(id, body as SubscriptionChangeCreate)
                }

                else -> null
            }
        }
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread()) as Single<T>
    }

    @Suppress("UNCHECKED_CAST")
    fun <T : Any> get(input: Any, id: String): (Single<T>) {

        return Single.fromCallable {
            when (input) {
                Site::class -> {
                    client.getSite(id)
                }
                RoutePlan::class -> {
                    client.getPlan(id)
                }
                Account::class -> {
                    client.getAccount(id)
                }
                AccountBalance::class -> {
                    client.getAccountBalance(id)
                }
                Subscription::class -> {
                    client.getSubscription(id)
                }
                AccountAcquisition::class -> {
                    client.getAccountAcquisition(id)
                }
                CouponRedemption::class -> {
                    client.getActiveCouponRedemption(id)
                }
                AddOn::class -> {
                    client.getAddOn(id)
                }
                BillingInfo::class -> {
                    client.getBillingInfo(id)
                }
                Coupon::class -> {
                    client.getCoupon(id)
                }
                CreditPayment::class -> {
                    client.getCreditPayment(id)
                }
                CustomFieldDefinition::class -> {
                    client.getCustomFieldDefinition(id)
                }
                Invoice::class -> {
                    client.getInvoice(id)
                }
                Item::class -> {
                    client.getItem(id)
                }
                SubscriptionChange::class -> {
                    client.getSubscriptionChange(id)
                }
                Transaction::class -> {
                    client.getTransaction(id)
                }
                UniqueCouponCode::class -> {
                    client.getUniqueCouponCode(id)
                }
                else -> null
            }
        }
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread()) as Single<T>
    }

    @Suppress("UNCHECKED_CAST")
    fun <T : Any> get(input: Any, firstId: String, secondId: String): (Single<T>) {
        return Single.fromCallable {
            when (input) {
                AccountNote::class -> {
                    client.getAccountNote(firstId, secondId)
                }
                AddOn::class -> {
                    client.getPlanAddOn(firstId, secondId)
                }
                ShippingAddress::class -> {
                    client.getShippingAddress(firstId, secondId)
                }
                else -> null
            }
        }
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread()) as Single<T>
    }

    @Suppress("UNCHECKED_CAST")
    fun <T : Any> update(input: Any, id: String, data: Any): (Single<T>) {
        return Single.fromCallable {
            when (input) {
                Account::class -> {
                    client.updateAccount(id, data as AccountUpdate)
                }
                AccountAcquisition::class -> {
                    client.updateAccountAcquisition(id, data as AccountAcquisitionUpdatable)
                }
                BillingInfo::class -> {
                    client.updateBillingInfo(id, data as BillingInfoCreate)
                }
                Coupon::class -> {
                    client.updateCoupon(id, data as CouponUpdate)
                }
                Item::class -> {
                    client.updateItem(id, data as ItemUpdate)
                }
                Plan::class -> {
                    client.updatePlan(id, data as PlanUpdate)
                }
                else -> null
            }
        }
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread()) as Single<T>
    }

    @Suppress("UNCHECKED_CAST")
    fun <T : Any> update(input: Any, data: Any, firstId: String, secondId: String): (Single<T>) {
        return Single.fromCallable {
            when (input) {
                AddOn::class -> {
                    client.updatePlanAddOn(firstId, secondId, data as AddOnUpdate)
                }
                ShippingAddress::class -> {
                    client.updateShippingAddress(firstId, secondId, data as ShippingAddressUpdate)
                }
                else -> null
            }
        }
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread()) as Single<T>
    }

    @Suppress("UNCHECKED_CAST")
    fun <T : Any> list(input: Any, queryParams: QueryParams): (Single<T>) {
        return Single.fromCallable {
            val pager = when (input) {
                AddOn::class -> {
                    client.listAddOns(queryParams)
                }
                Coupon::class -> {
                    client.listCoupons(queryParams)
                }
                CreditPayment::class -> {
                    client.listCreditPayments(queryParams)
                }
                CustomFieldDefinition::class -> {
                    client.listCustomFieldDefinitions(queryParams)
                }
                Invoice::class -> {
                    client.listInvoices(queryParams)
                }
                Item::class -> {
                    client.listItems(queryParams)
                }
                LineItem::class -> {
                    client.listLineItems(queryParams)
                }
                Plan::class -> {
                    client.listPlans(queryParams)
                }
                ShippingMethod::class -> {
                    client.listShippingMethods(queryParams)
                }
                Site::class -> {
                    client.listSites(queryParams)
                }
                Subscription::class -> {
                    client.listSubscriptions(queryParams)
                }
                Transaction::class -> {
                    client.listTransactions(queryParams)
                }

                else -> null
            }
            pager?.getNextPage()
            return@fromCallable pager?.data
        }
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread()) as Single<T>
    }

    @Suppress("UNCHECKED_CAST")
    fun <T : Any> listInvoice(input: Any, id: String, queryParams: QueryParams): (Single<T>) {
        return Single.fromCallable {
            val pager = when (input) {
                CouponRedemption::class -> {
                    client.listInvoiceCouponRedemptions(id, queryParams)
                }
                LineItem::class -> {
                    client.listInvoiceLineItems(id, queryParams)
                }
                else -> null
            }
            pager?.getNextPage()
            return@fromCallable pager?.data
        }
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread()) as Single<T>
    }

    @Suppress("UNCHECKED_CAST")
    fun <T : Any> list(input: Any, id: String, queryParams: QueryParams): (Single<T>) {
        return Single.fromCallable {
            val pager = when (input) {
                Account::class -> {
                    client.listChildAccounts(id, queryParams)
                }
                AddOn::class -> {
                    client.listPlanAddOns(id, queryParams)
                }
                ShippingAddress::class -> {
                    client.listShippingAddresses(id, queryParams)
                }
                UniqueCouponCode::class -> {
                    client.listUniqueCouponCodes(id, queryParams)
                }
                else -> null
            }
            pager?.getNextPage()
            return@fromCallable pager?.data
        }
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread()) as Single<T>
    }

    @Suppress("UNCHECKED_CAST")
    fun <T : Any> listSubscription(input: Any, id: String, queryParams: QueryParams): (Single<T>) {
        return Single.fromCallable {
            val pager = when (input) {
                CouponRedemption::class -> {
                    client.listSubscriptionCouponRedemptions(id, queryParams)
                }
                Invoice::class -> {
                    client.listSubscriptionInvoices(id, queryParams)
                }
                LineItem::class -> {
                    client.listSubscriptionLineItems(id, queryParams)
                }
                else -> null
            }
            pager?.getNextPage()
            return@fromCallable pager?.data
        }
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread()) as Single<T>
    }

    @Suppress("UNCHECKED_CAST")
    fun <T : Any> listAccount(input: Any, queryParams: QueryParams): (Single<T>) {
        return Single.fromCallable {
            val pager = when (input) {
                Account::class -> {
                    client.listAccounts(queryParams)
                }
                AccountAcquisition::class -> {
                    client.listAccountAcquisition(queryParams)
                }
                else -> null
            }
            pager?.getNextPage()
            return@fromCallable pager?.data
        }
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread()) as Single<T>
    }

    @Suppress("UNCHECKED_CAST")
    fun <T : Any> listAccount(input: Any, id: String, queryParams: QueryParams): (Single<T>) {
        return Single.fromCallable {
            val pager = when (input) {
                CouponRedemption::class -> {
                    client.listAccountCouponRedemptions(id, queryParams)
                }
                CreditPayment::class -> {
                    client.listAccountCreditPayments(id, queryParams)
                }
                Invoice::class -> {
                    client.listAccountInvoices(id, queryParams)
                }
                LineItem::class -> {
                    client.listAccountLineItems(id, queryParams)
                }
                AccountNote::class -> {
                    client.listAccountNotes(id, queryParams)
                }
                Subscription::class -> {
                    client.listAccountSubscriptions(id, queryParams)
                }
                Transaction::class -> {
                    client.listAccountTransactions(id, queryParams)
                }
                else -> null
            }
            pager?.getNextPage()
            return@fromCallable pager?.data
        }
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread()) as Single<T>
    }

    @Suppress("UNCHECKED_CAST")
    fun <T : Any> reactivate(input: Any, id: String): (Single<T>) {

        return Single.fromCallable {
            when (input) {
                Account::class -> {
                    client.reactivateAccount(id)
                }
                Item::class -> {
                    client.reactivateItem(id)
                }
                Subscription::class -> {
                    client.reactivateSubscription(id)
                }
                UniqueCouponCode::class -> {
                    client.reactivateUniqueCouponCode(id)
                }
                else -> null
            }
        }
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread()) as Single<T>
    }

    @SuppressLint("CheckResult")
    @Suppress("UNCHECKED_CAST")
    fun remove(input: Any, id: String) {
        Single.fromCallable {
            when (input) {
                AccountAcquisition::class -> {
                    client.removeAccountAcquisition(id)
                }
                BillingInfo::class -> {
                    client.removeBillingInfo(id)
                }
                LineItem::class -> {
                    client.removeLineItem(id)
                }
                SubscriptionChange::class -> {
                    client.removeSubscriptionChange(id)
                }
                else -> null
            }
        }
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
    }

    @SuppressLint("CheckResult")
    @Suppress("UNCHECKED_CAST")
    fun remove(input: Any, firstId: String, secondId: String) {
        Single.fromCallable {
            when (input) {
                ShippingAddress::class -> {
                    client.removeShippingAddress(firstId, secondId)
                }
                else -> null
            }
        }
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
    }

    @SuppressLint("CheckResult")
    @Suppress("UNCHECKED_CAST")
    fun <T : Any> removeWithResult(input: Any, firstId: String, secondId: String): (Single<T>) {
        return Single.fromCallable {
            when (input) {
                AddOn::class -> {
                    client.removePlanAddOn(firstId, secondId)
                }
                else -> null
            }
        }
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread()) as Single<T>
    }

    @Suppress("UNCHECKED_CAST")
    fun <T : Any> removeWithResult(input: Any, id: String): (Single<T>) {

        return Single.fromCallable {
            when (input) {
                CouponRedemption::class -> {
                    client.removeCouponRedemption(id)
                }
                Plan::class -> {
                    client.removePlan(id)
                }
                else -> null
            }
        }
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread()) as Single<T>
    }

    @Suppress("UNCHECKED_CAST")
    fun <T : Any> pause(input: Any, id: String, body: Any): (Single<T>) {

        return Single.fromCallable {
            when (input) {
                Subscription::class -> {
                    client.pauseSubscription(id, body as SubscriptionPause)
                }
                else -> null
            }
        }
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread()) as Single<T>
    }

}
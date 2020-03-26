package com.radko.recurlylib.views

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.LinearLayout
import com.recurly.v3.resources.Subscription
import com.radko.recurlylib.R
import kotlinx.android.synthetic.main.subscription_details_layout.view.*
import org.joda.time.DateTime
import java.text.SimpleDateFormat
import java.util.*

const val DATE_FORMAT = "dd-MM-yyyy"
class SubscriptionDetailsView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : LinearLayout(context, attrs, defStyleAttr) {


    init {
        LayoutInflater.from(context).inflate(R.layout.subscription_details_layout, this, true)
    }

    fun bind(subscription: Subscription) {
        title.text = subscription.plan.name
        startDate.text = formatTime(subscription.currentPeriodStartedAt)
        endDate.text = formatTime(subscription.currentPeriodEndsAt)
        status.text = subscription.state
    }

    private fun formatTime(time: DateTime?): String? {
        return SimpleDateFormat(
            DATE_FORMAT,
            Locale.getDefault()
        ).format(time!!.toCalendar(Locale.getDefault()).time)
    }
}
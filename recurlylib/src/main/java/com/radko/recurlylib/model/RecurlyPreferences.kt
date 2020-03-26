package com.radko.recurlylib.model

import android.content.Context

const val NAME: String = "RecurlyPreferences"

class RecurlyPreferences(private val context: Context) : Preferences(context, NAME) {
    var apiKey by stringPref()
    var accountCode by stringPref()
}
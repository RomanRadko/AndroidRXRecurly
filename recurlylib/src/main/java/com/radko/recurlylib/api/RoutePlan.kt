package com.radko.recurlylib.api

enum class RoutePlan(val code: String) {
    PLAN_MONTH("mobile-unlim-month"),
    PLAN_YEAR("mobile-unlim-annual"),
    PLAN_VOICE_YEAR("voice-guided-annual"),
    PLAN_VOICE_MONTH("voice-guided-month"),
    PLAN_MOBILE_FREE("mobile-free")
}
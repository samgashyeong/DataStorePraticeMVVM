package com.example.datastorepratice

import androidx.datastore.preferences.core.booleanPreferencesKey

object DataStoreKey {
    val IS_SWITCH_ON = booleanPreferencesKey("is_notification_on")
}
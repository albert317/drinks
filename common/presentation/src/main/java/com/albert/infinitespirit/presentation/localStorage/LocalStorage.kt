package com.albert.infinitespirit.presentation.localStorage

import android.content.SharedPreferences

class LocalStorage(private val prefs: SharedPreferences) {

    companion object {
        const val PREF_NAME = "PREFERENCES_INFINITY_SPIRIT"
        const val ACCOUNT_NAME = "account_name"
        const val ACCOUNT_EMAIL = "account_email"
        const val ACCOUNT_PHOTO = "account_photo"
    }

    fun putString(key: String, value: String?) {
        prefs.edit().apply { putString(key, value) }.apply()
    }

    fun getString(key: String): String? {
        return prefs.getString(key, null)
    }

    fun putBoolean(key: String, value: Boolean) {
        prefs.edit().apply { putBoolean(key, value) }.apply()
    }

    fun getBoolean(key: String): Boolean {
        return prefs.getBoolean(key, false)
    }

    fun putInt(key: String, value: Int) {
        prefs.edit().apply { putInt(key, value) }.apply()
    }

    fun getInt(key: String): Int {
        return prefs.getInt(key, 0)
    }

    fun putLong(key: String, value: Long) {
        prefs.edit().apply { putLong(key, value) }.apply()
    }

    fun getLong(key: String): Long {
        return prefs.getLong(key, 0)
    }

    fun putFloat(key: String, value: Float) {
        prefs.edit().apply { putFloat(key, value) }.apply()
    }

    fun getFloat(key: String): Float {
        return prefs.getFloat(key, 0F)
    }

}

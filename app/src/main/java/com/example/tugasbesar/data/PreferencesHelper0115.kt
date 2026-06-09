package com.example.tugasbesar.data

import android.content.Context

object PreferencesHelper0115 {
    private const val PREFS_NAME0115 = "planmate_prefs0115"
    private const val KEY_USERNAME0115 = "username0115"

    fun saveUsername0115(context0115: Context, username0115: String) {
        context0115.getSharedPreferences(PREFS_NAME0115, Context.MODE_PRIVATE)
            .edit()
            .putString(KEY_USERNAME0115, username0115)
            .apply()
    }

    fun getUsername0115(context0115: Context): String? {
        return context0115.getSharedPreferences(PREFS_NAME0115, Context.MODE_PRIVATE)
            .getString(KEY_USERNAME0115, null)
    }

    fun clearUsername0115(context0115: Context) {
        context0115.getSharedPreferences(PREFS_NAME0115, Context.MODE_PRIVATE)
            .edit()
            .remove(KEY_USERNAME0115)
            .apply()
    }
}

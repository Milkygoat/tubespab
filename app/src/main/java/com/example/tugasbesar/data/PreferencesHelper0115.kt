package com.example.tugasbesar.data

import android.content.Context

object PreferencesHelper0115 {
    private const val PREFS_NAME0115 = "planmate_prefs0115"
    private const val KEY_USERNAME0115 = "username0115"
    private const val KEY_PROFILES0115 = "profiles0115"

    fun saveUsername0115(context0115: Context, username0115: String) {
        context0115.getSharedPreferences(PREFS_NAME0115, Context.MODE_PRIVATE)
            .edit()
            .putString(KEY_USERNAME0115, username0115)
            .apply()
        // Also add to profile list
        addProfile0115(context0115, username0115)
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

    // ── Multi-Profile Management ──

    fun addProfile0115(context0115: Context, profileName0115: String) {
        val profiles0115 = getAllProfiles0115(context0115).toMutableSet()
        profiles0115.add(profileName0115)
        context0115.getSharedPreferences(PREFS_NAME0115, Context.MODE_PRIVATE)
            .edit()
            .putStringSet(KEY_PROFILES0115, profiles0115)
            .apply()
    }

    fun getAllProfiles0115(context0115: Context): List<String> {
        val set0115 = context0115.getSharedPreferences(PREFS_NAME0115, Context.MODE_PRIVATE)
            .getStringSet(KEY_PROFILES0115, emptySet()) ?: emptySet()
        return set0115.sorted()
    }

    fun removeProfile0115(context0115: Context, profileName0115: String) {
        val profiles0115 = getAllProfiles0115(context0115).toMutableSet()
        profiles0115.remove(profileName0115)
        context0115.getSharedPreferences(PREFS_NAME0115, Context.MODE_PRIVATE)
            .edit()
            .putStringSet(KEY_PROFILES0115, profiles0115)
            .apply()
        // If the removed profile is the active one, clear it
        if (getUsername0115(context0115) == profileName0115) {
            clearUsername0115(context0115)
        }
    }

    fun switchToProfile0115(context0115: Context, profileName0115: String) {
        context0115.getSharedPreferences(PREFS_NAME0115, Context.MODE_PRIVATE)
            .edit()
            .putString(KEY_USERNAME0115, profileName0115)
            .apply()
    }
}

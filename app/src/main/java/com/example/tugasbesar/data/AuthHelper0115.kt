package com.example.tugasbesar.data

import android.content.Context

object AuthHelper0115 {
    fun getUserId(context0115: Context): String? {
        val prefs0115 = context0115.getSharedPreferences("planmate_prefs0115", Context.MODE_PRIVATE)
        return prefs0115.getString("user_id0115", null)
    }
}


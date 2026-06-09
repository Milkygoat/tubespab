package com.example.tugasbesar.data.repositories

import android.content.Context
import android.content.SharedPreferences
import com.example.tugasbesar.data.models.AuthResponse0115
import com.example.tugasbesar.data.network.SupabaseApi0115
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class AuthRepository0115(private val api0115: SupabaseApi0115, context0115: Context) {
    private val prefs0115: SharedPreferences = context0115.getSharedPreferences("planmate_prefs0115", Context.MODE_PRIVATE)
    suspend fun signUp0115(email0115: String, password0115: String): Result<AuthResponse0115?> = withContext(Dispatchers.IO) {
        try {
            val body0115 = mapOf("email" to email0115, "password" to password0115)
            val resp0115 = api0115.signUp0115(body0115)
            if (resp0115.isSuccessful) {
                val auth0115 = resp0115.body()
                auth0115?.access_token0115?.let { prefs0115.edit().putString("access_token0115", it).apply() }
                auth0115?.refresh_token0115?.let { prefs0115.edit().putString("refresh_token0115", it).apply() }
                val userId0115 = auth0115?.user0115?.get("id")?.toString()
                userId0115?.let { prefs0115.edit().putString("user_id0115", it).apply() }
                Result.success(auth0115)
            } else {
                val errBody0115 = resp0115.errorBody()?.string()
                val msg0115 = errBody0115?.takeIf { it.isNotBlank() } ?: "signup_failed0115"
                Result.failure(Exception(msg0115))
            }
        } catch (e0115: Exception) {
            Result.failure(e0115)
        }
    }
    suspend fun signIn0115(email0115: String, password0115: String): Result<AuthResponse0115?> = withContext(Dispatchers.IO) {
        try {
            val resp0115 = api0115.signIn0115("password", email0115, password0115)
            if (resp0115.isSuccessful) {
                val auth0115 = resp0115.body()
                auth0115?.access_token0115?.let { prefs0115.edit().putString("access_token0115", it).apply() }
                auth0115?.refresh_token0115?.let { prefs0115.edit().putString("refresh_token0115", it).apply() }
                val userId0115 = auth0115?.user0115?.get("id")?.toString()
                userId0115?.let { prefs0115.edit().putString("user_id0115", it).apply() }
                Result.success(auth0115)
            } else {
                val errBody0115 = resp0115.errorBody()?.string()
                val msg0115 = errBody0115?.takeIf { it.isNotBlank() } ?: "signin_failed0115"
                Result.failure(Exception(msg0115))
            }
        } catch (e0115: Exception) {
            Result.failure(e0115)
        }
    }
    fun signOut0115() {
        prefs0115.edit().remove("access_token0115").remove("refresh_token0115").remove("user_id0115").apply()
    }
    fun getAccessToken0115(): String? = prefs0115.getString("access_token0115", null)
    fun getUserId0115(): String? = prefs0115.getString("user_id0115", null)
    fun getRefreshToken0115(): String? = prefs0115.getString("refresh_token0115", null)
}

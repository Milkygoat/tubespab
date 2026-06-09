package com.example.tugasbesar.data.network

import android.content.Context
import com.squareup.moshi.Moshi
import okhttp3.Authenticator
import okhttp3.FormBody
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.Route
import org.json.JSONObject
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

object SupabaseClient0115 {
    private val moshi0115 = Moshi.Builder().build()
    fun createApi0115(context0115: Context, supabaseUrl0115: String, supabaseKey0115: String): SupabaseApi0115 {
        val authInterceptor0115 = Interceptor { chain0115 ->
            val prefs0115 = context0115.getSharedPreferences("planmate_prefs0115", Context.MODE_PRIVATE)
            val token0115 = prefs0115.getString("access_token0115", null)
            val original0115: Request = chain0115.request()
            val builder0115 = original0115.newBuilder()
                .addHeader("apikey", supabaseKey0115)
                .addHeader("Content-Type", "application/json")
            if (token0115 != null) {
                builder0115.addHeader("Authorization", "Bearer $token0115")
            } else {
                builder0115.addHeader("Authorization", "Bearer $supabaseKey0115")
            }
            if (original0115.method == "POST" || original0115.method == "PATCH") {
                builder0115.addHeader("Prefer", "return=representation")
            }
            val request0115 = builder0115.build()
            chain0115.proceed(request0115)
        }
        val refreshAuth0115 = object : Authenticator {
            override fun authenticate(route: Route?, response: okhttp3.Response): Request? {
                val prefs0115 = context0115.getSharedPreferences("planmate_prefs0115", Context.MODE_PRIVATE)
                val refresh0115 = prefs0115.getString("refresh_token0115", null) ?: return null
                synchronized(this) {
                    val currentToken0115 = prefs0115.getString("access_token0115", null)
                    val reqAuth0115 = response.request.header("Authorization")
                    if (reqAuth0115 != null && currentToken0115 != null && reqAuth0115.contains(currentToken0115)) {
                        try {
                            val form0115 = FormBody.Builder().add("grant_type", "refresh_token").add("refresh_token", refresh0115).build()
                            val req0115 = Request.Builder().url(supabaseUrl0115.trimEnd('/') + "/auth/v1/token").post(form0115).addHeader("apikey", supabaseKey0115).addHeader("Content-Type", "application/x-www-form-urlencoded").build()
                            val tmpClient0115 = OkHttpClient()
                            val resp0115 = tmpClient0115.newCall(req0115).execute()
                            if (resp0115.isSuccessful) {
                                val bodyStr0115 = resp0115.body?.string() ?: return null
                                val jo0115 = JSONObject(bodyStr0115)
                                val newAccess0115 = jo0115.optString("access_token")
                                val newRefresh0115 = jo0115.optString("refresh_token")
                                if (newAccess0115.isNotEmpty()) {
                                    prefs0115.edit().putString("access_token0115", newAccess0115).apply()
                                    if (newRefresh0115.isNotEmpty()) prefs0115.edit().putString("refresh_token0115", newRefresh0115).apply()
                                    return response.request.newBuilder().header("Authorization", "Bearer $newAccess0115").build()
                                }
                            }
                        } catch (e0115: Exception) {
                            return null
                        }
                    }
                }
                return null
            }
        }
        val client0115 = OkHttpClient.Builder().addInterceptor(authInterceptor0115).authenticator(refreshAuth0115).build()
        val retrofit0115 = Retrofit.Builder()
            .baseUrl(supabaseUrl0115)
            .addConverterFactory(MoshiConverterFactory.create(moshi0115))
            .client(client0115)
            .build()
        return retrofit0115.create(SupabaseApi0115::class.java)
    }
}


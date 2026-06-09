package com.example.tugasbesar.data.network

import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

object SupabaseClient0115 {
    private val moshi0115 = Moshi.Builder()
        .add(KotlinJsonAdapterFactory())
        .build()

    fun createApi0115(supabaseUrl0115: String, supabaseKey0115: String): SupabaseApi0115 {
        val interceptor0115 = Interceptor { chain0115 ->
            val original0115 = chain0115.request()
            val builder0115 = original0115.newBuilder()
                .addHeader("apikey", supabaseKey0115)
                .addHeader("Authorization", "Bearer $supabaseKey0115")
                .addHeader("Content-Type", "application/json")
            if (original0115.method == "POST" || original0115.method == "PATCH") {
                builder0115.addHeader("Prefer", "return=representation")
            }
            chain0115.proceed(builder0115.build())
        }
        val client0115 = OkHttpClient.Builder()
            .addInterceptor(interceptor0115)
            .build()
        val retrofit0115 = Retrofit.Builder()
            .baseUrl(supabaseUrl0115)
            .addConverterFactory(MoshiConverterFactory.create(moshi0115))
            .client(client0115)
            .build()
        return retrofit0115.create(SupabaseApi0115::class.java)
    }
}

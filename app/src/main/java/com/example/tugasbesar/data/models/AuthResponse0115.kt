package com.example.tugasbesar.data.models

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class AuthResponse0115(
    @Json(name = "access_token") val access_token0115: String? = null,
    @Json(name = "refresh_token") val refresh_token0115: String? = null,
    @Json(name = "token_type") val token_type0115: String? = null,
    @Json(name = "expires_in") val expires_in0115: Int? = null,
    @Json(name = "user") val user0115: Map<String, Any>? = null
)

package com.example.tugasbesar.data.models

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class AuthResponse0115(
    val access_token0115: String? = null,
    val refresh_token0115: String? = null,
    val token_type0115: String? = null,
    val expires_in0115: Int? = null,
    val user0115: Map<String, Any>? = null
)


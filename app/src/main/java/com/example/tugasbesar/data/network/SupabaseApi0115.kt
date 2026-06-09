package com.example.tugasbesar.data.network

import com.example.tugasbesar.data.models.AuthResponse0115
import com.example.tugasbesar.data.models.Event0115
import com.example.tugasbesar.data.models.Task0115
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.PATCH
import retrofit2.http.POST
import retrofit2.http.Path
import retrofit2.http.Query
import retrofit2.http.Url

interface SupabaseApi0115 {
    @POST("/auth/v1/signup")
    suspend fun signUp0115(@Body body0115: Map<String, String>): Response<AuthResponse0115>

    @FormUrlEncoded
    @POST("/auth/v1/token")
    suspend fun signIn0115(@Field("grant_type") grant0115: String, @Field("username") username0115: String, @Field("password") password0115: String): Response<AuthResponse0115>

    @FormUrlEncoded
    @POST("/auth/v1/token")
    suspend fun refreshToken0115(@Field("grant_type") grant0115: String, @Field("refresh_token") refresh0115: String): Response<AuthResponse0115>

    @GET
    suspend fun getTasks0115(@Url url0115: String): Response<List<Task0115>>

    @POST
    suspend fun createTask0115(@Url url0115: String, @Body task0115: Task0115): Response<List<Task0115>>

    @PATCH
    suspend fun updateTask0115(@Url url0115: String, @Body task0115: Map<String, Any>): Response<List<Task0115>>

    @DELETE
    suspend fun deleteTask0115(@Url url0115: String): Response<Unit>

    @GET
    suspend fun getEvents0115(@Url url0115: String): Response<List<Event0115>>

    @POST
    suspend fun createEvent0115(@Url url0115: String, @Body event0115: Event0115): Response<List<Event0115>>

    @PATCH
    suspend fun updateEvent0115(@Url url0115: String, @Body event0115: Map<String, Any>): Response<List<Event0115>>

    @DELETE
    suspend fun deleteEvent0115(@Url url0115: String): Response<Unit>
}


package com.example.tugasbesar.data.network

import com.example.tugasbesar.data.models.Event0115
import com.example.tugasbesar.data.models.Task0115
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.PATCH
import retrofit2.http.POST
import retrofit2.http.Url

interface SupabaseApi0115 {
    @GET
    suspend fun getTasks0115(@Url url0115: String): Response<List<Task0115>>

    @POST
    suspend fun createTask0115(@Url url0115: String, @Body body0115: Map<String, @JvmSuppressWildcards Any>): Response<List<Task0115>>

    @PATCH
    suspend fun updateTask0115(@Url url0115: String, @Body body0115: Map<String, @JvmSuppressWildcards Any>): Response<List<Task0115>>

    @DELETE
    suspend fun deleteTask0115(@Url url0115: String): Response<Unit>

    @GET
    suspend fun getEvents0115(@Url url0115: String): Response<List<Event0115>>

    @POST
    suspend fun createEvent0115(@Url url0115: String, @Body body0115: Map<String, @JvmSuppressWildcards Any>): Response<List<Event0115>>

    @PATCH
    suspend fun updateEvent0115(@Url url0115: String, @Body body0115: Map<String, @JvmSuppressWildcards Any>): Response<List<Event0115>>

    @DELETE
    suspend fun deleteEvent0115(@Url url0115: String): Response<Unit>
}

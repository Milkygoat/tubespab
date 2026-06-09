package com.example.tugasbesar.data.repositories

import com.example.tugasbesar.data.models.Event0115
import com.example.tugasbesar.data.network.SupabaseApi0115
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class EventRepository0115(private val api0115: SupabaseApi0115, private val supabaseUrl0115: String) {
    suspend fun getEventsForUser0115(userId0115: String): Result<List<Event0115>> = withContext(Dispatchers.IO) {
        try {
            val url0115 = "$supabaseUrl0115/rest/v1/events?user_id=eq.$userId0115&select=*"
            val resp0115 = api0115.getEvents0115(url0115)
            if (resp0115.isSuccessful) {
                Result.success(resp0115.body() ?: emptyList())
            } else {
                Result.failure(Exception("get_events_failed0115"))
            }
        } catch (e0115: Exception) {
            Result.failure(e0115)
        }
    }
    suspend fun createEvent0115(event0115: Event0115): Result<Event0115?> = withContext(Dispatchers.IO) {
        try {
            val url0115 = "$supabaseUrl0115/rest/v1/events"
            val resp0115 = api0115.createEvent0115(url0115, event0115)
            if (resp0115.isSuccessful) {
                Result.success(resp0115.body()?.firstOrNull())
            } else {
                Result.failure(Exception("create_event_failed0115"))
            }
        } catch (e0115: Exception) {
            Result.failure(e0115)
        }
    }
    suspend fun updateEvent0115(id0115: String, updates0115: Map<String, Any>): Result<Event0115?> = withContext(Dispatchers.IO) {
        try {
            val url0115 = "$supabaseUrl0115/rest/v1/events?id=eq.$id0115"
            val resp0115 = api0115.updateEvent0115(url0115, updates0115)
            if (resp0115.isSuccessful) {
                Result.success(resp0115.body()?.firstOrNull())
            } else {
                Result.failure(Exception("update_event_failed0115"))
            }
        } catch (e0115: Exception) {
            Result.failure(e0115)
        }
    }
    suspend fun deleteEvent0115(id0115: String): Result<Boolean> = withContext(Dispatchers.IO) {
        try {
            val url0115 = "$supabaseUrl0115/rest/v1/events?id=eq.$id0115"
            val resp0115 = api0115.deleteEvent0115(url0115)
            if (resp0115.isSuccessful) {
                Result.success(true)
            } else {
                Result.failure(Exception("delete_event_failed0115"))
            }
        } catch (e0115: Exception) {
            Result.failure(e0115)
        }
    }
}


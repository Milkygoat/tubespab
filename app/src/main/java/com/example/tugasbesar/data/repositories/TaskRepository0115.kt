package com.example.tugasbesar.data.repositories

import com.example.tugasbesar.data.models.Task0115
import com.example.tugasbesar.data.network.SupabaseApi0115
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class TaskRepository0115(private val api0115: SupabaseApi0115, private val supabaseUrl0115: String) {
    suspend fun getTasksForUser0115(userId0115: String): Result<List<Task0115>> = withContext(Dispatchers.IO) {
        try {
            val url0115 = "$supabaseUrl0115/rest/v1/tasks?user_id=eq.$userId0115&select=*"
            val resp0115 = api0115.getTasks0115(url0115)
            if (resp0115.isSuccessful) {
                Result.success(resp0115.body() ?: emptyList())
            } else {
                Result.failure(Exception("get_tasks_failed0115"))
            }
        } catch (e0115: Exception) {
            Result.failure(e0115)
        }
    }
    suspend fun createTask0115(task0115: Task0115): Result<Task0115?> = withContext(Dispatchers.IO) {
        try {
            val url0115 = "$supabaseUrl0115/rest/v1/tasks"
            val resp0115 = api0115.createTask0115(url0115, task0115)
            if (resp0115.isSuccessful) {
                val list0115 = resp0115.body()
                Result.success(list0115?.firstOrNull())
            } else {
                Result.failure(Exception("create_task_failed0115"))
            }
        } catch (e0115: Exception) {
            Result.failure(e0115)
        }
    }
    suspend fun updateTask0115(id0115: String, updates0115: Map<String, Any>): Result<Task0115?> = withContext(Dispatchers.IO) {
        try {
            val url0115 = "$supabaseUrl0115/rest/v1/tasks?id=eq.$id0115"
            val resp0115 = api0115.updateTask0115(url0115, updates0115)
            if (resp0115.isSuccessful) {
                Result.success(resp0115.body()?.firstOrNull())
            } else {
                Result.failure(Exception("update_task_failed0115"))
            }
        } catch (e0115: Exception) {
            Result.failure(e0115)
        }
    }
    suspend fun deleteTask0115(id0115: String): Result<Boolean> = withContext(Dispatchers.IO) {
        try {
            val url0115 = "$supabaseUrl0115/rest/v1/tasks?id=eq.$id0115"
            val resp0115 = api0115.deleteTask0115(url0115)
            if (resp0115.isSuccessful) {
                Result.success(true)
            } else {
                Result.failure(Exception("delete_task_failed0115"))
            }
        } catch (e0115: Exception) {
            Result.failure(e0115)
        }
    }
}


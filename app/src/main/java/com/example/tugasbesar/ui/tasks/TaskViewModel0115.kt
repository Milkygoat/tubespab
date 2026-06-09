package com.example.tugasbesar.ui.tasks

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.tugasbesar.data.models.Task0115
import com.example.tugasbesar.data.repositories.TaskRepository0115
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class TaskViewModel0115(
    private val taskRepository0115: TaskRepository0115,
    private val username0115: String
) : ViewModel() {

    private val _taskList0115 = MutableStateFlow<List<Task0115>>(emptyList())
    val taskList0115: StateFlow<List<Task0115>> = _taskList0115.asStateFlow()

    private val _isLoading0115 = MutableStateFlow(false)
    val isLoading0115: StateFlow<Boolean> = _isLoading0115.asStateFlow()

    private val _errorMessage0115 = MutableStateFlow<String?>(null)
    val errorMessage0115: StateFlow<String?> = _errorMessage0115.asStateFlow()

    fun loadTasks0115() {
        viewModelScope.launch {
            _isLoading0115.value = true
            taskRepository0115.getTasksForUser0115(username0115)
                .onSuccess { _taskList0115.value = it }
                .onFailure { _errorMessage0115.value = it.message }
            _isLoading0115.value = false
        }
    }

    suspend fun createTask0115(task0115: Task0115) {
        _isLoading0115.value = true
        taskRepository0115.createTask0115(task0115)
            .onFailure { _errorMessage0115.value = it.message }
        loadTasks0115()
    }

    suspend fun updateTask0115(id0115: String, updates0115: Map<String, Any>) {
        _isLoading0115.value = true
        taskRepository0115.updateTask0115(id0115, updates0115)
            .onFailure { _errorMessage0115.value = it.message }
        loadTasks0115()
    }

    suspend fun deleteTask0115(id0115: String) {
        _isLoading0115.value = true
        taskRepository0115.deleteTask0115(id0115)
            .onFailure { _errorMessage0115.value = it.message }
        loadTasks0115()
    }

    suspend fun toggleTaskStatus0115(task0115: Task0115) {
        val id0115 = task0115.id0115 ?: return
        val newStatus0115 = !(task0115.status0115 ?: false)
        updateTask0115(id0115, mapOf("status" to newStatus0115))
    }
}

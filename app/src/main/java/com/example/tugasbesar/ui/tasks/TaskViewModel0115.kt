package com.example.tugasbesar.ui.tasks

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.tugasbesar.data.models.Task0115
import com.example.tugasbesar.data.repositories.TaskRepository0115
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class TaskViewModel0115(private val repo0115: TaskRepository0115) : ViewModel() {
    private val _taskList0115 = MutableStateFlow<List<Task0115>>(emptyList())
    val taskList0115: StateFlow<List<Task0115>> = _taskList0115
    private val _error0115 = MutableStateFlow<String?>(null)
    val error0115: StateFlow<String?> = _error0115
    fun loadTasks0115(userId0115: String) {
        viewModelScope.launch {
            val res0115 = repo0115.getTasksForUser0115(userId0115)
            if (res0115.isSuccess) {
                _taskList0115.value = res0115.getOrNull() ?: emptyList()
            } else {
                _error0115.value = res0115.exceptionOrNull()?.message
            }
        }
    }
    fun createTask0115(task0115: Task0115, onComplete0115: (Task0115?) -> Unit) {
        viewModelScope.launch {
            val res0115 = repo0115.createTask0115(task0115)
            if (res0115.isSuccess) {
                onComplete0115(res0115.getOrNull())
            } else {
                _error0115.value = res0115.exceptionOrNull()?.message
                onComplete0115(null)
            }
        }
    }
    fun updateTask0115(id0115: String, updates0115: Map<String, Any>, onComplete0115: (Task0115?) -> Unit) {
        viewModelScope.launch {
            val res0115 = repo0115.updateTask0115(id0115, updates0115)
            if (res0115.isSuccess) {
                onComplete0115(res0115.getOrNull())
            } else {
                _error0115.value = res0115.exceptionOrNull()?.message
                onComplete0115(null)
            }
        }
    }
    fun deleteTask0115(id0115: String, onComplete0115: (Boolean) -> Unit) {
        viewModelScope.launch {
            val res0115 = repo0115.deleteTask0115(id0115)
            if (res0115.isSuccess) {
                onComplete0115(true)
            } else {
                _error0115.value = res0115.exceptionOrNull()?.message
                onComplete0115(false)
            }
        }
    }
}


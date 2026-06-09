package com.example.tugasbesar.ui.tasks

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.tugasbesar.data.repositories.TaskRepository0115

class TaskViewModelFactory0115(
    private val taskRepository0115: TaskRepository0115,
    private val username0115: String
) : ViewModelProvider.Factory {
    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass0115: Class<T>): T {
        return TaskViewModel0115(taskRepository0115, username0115) as T
    }
}

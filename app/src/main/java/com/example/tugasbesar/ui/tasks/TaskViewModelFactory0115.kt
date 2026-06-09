package com.example.tugasbesar.ui.tasks

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.tugasbesar.data.repositories.TaskRepository0115

class TaskViewModelFactory0115(private val repo0115: TaskRepository0115) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass0115: Class<T>): T {
        if (modelClass0115.isAssignableFrom(TaskViewModel0115::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return TaskViewModel0115(repo0115) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class0115")
    }
}


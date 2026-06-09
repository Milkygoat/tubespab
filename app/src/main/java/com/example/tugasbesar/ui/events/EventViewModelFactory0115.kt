package com.example.tugasbesar.ui.events

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.tugasbesar.data.repositories.EventRepository0115

class EventViewModelFactory0115(private val repo0115: EventRepository0115) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass0115: Class<T>): T {
        if (modelClass0115.isAssignableFrom(EventViewModel0115::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return EventViewModel0115(repo0115) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class0115")
    }
}


package com.example.tugasbesar.ui.events

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.tugasbesar.data.repositories.EventRepository0115

class EventViewModelFactory0115(
    private val eventRepository0115: EventRepository0115,
    private val username0115: String
) : ViewModelProvider.Factory {
    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass0115: Class<T>): T {
        return EventViewModel0115(eventRepository0115, username0115) as T
    }
}

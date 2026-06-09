package com.example.tugasbesar.ui.calendar

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.tugasbesar.data.repositories.EventRepository0115
import com.example.tugasbesar.data.repositories.TaskRepository0115

class CalendarViewModelFactory0115(
    private val taskRepository0115: TaskRepository0115,
    private val eventRepository0115: EventRepository0115,
    private val username0115: String
) : ViewModelProvider.Factory {
    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass0115: Class<T>): T {
        return CalendarViewModel0115(taskRepository0115, eventRepository0115, username0115) as T
    }
}

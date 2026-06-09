package com.example.tugasbesar.ui.dashboard

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.tugasbesar.data.models.Event0115
import com.example.tugasbesar.data.models.Task0115
import com.example.tugasbesar.data.repositories.EventRepository0115
import com.example.tugasbesar.data.repositories.TaskRepository0115
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class DashboardViewModel0115(
    private val taskRepository0115: TaskRepository0115,
    private val eventRepository0115: EventRepository0115,
    private val username0115: String
) : ViewModel() {

    private val _activeTaskCount0115 = MutableStateFlow(0)
    val activeTaskCount0115: StateFlow<Int> = _activeTaskCount0115.asStateFlow()

    private val _upcomingEventCount0115 = MutableStateFlow(0)
    val upcomingEventCount0115: StateFlow<Int> = _upcomingEventCount0115.asStateFlow()

    private val _nearestTask0115 = MutableStateFlow<Task0115?>(null)
    val nearestTask0115: StateFlow<Task0115?> = _nearestTask0115.asStateFlow()

    private val _nearestEvent0115 = MutableStateFlow<Event0115?>(null)
    val nearestEvent0115: StateFlow<Event0115?> = _nearestEvent0115.asStateFlow()

    fun loadDashboard0115() {
        viewModelScope.launch {
            taskRepository0115.getTasksForUser0115(username0115).onSuccess { tasks0115 ->
                val activeTasks0115 = tasks0115.filter { !(it.status0115 ?: false) }
                _activeTaskCount0115.value = activeTasks0115.size
                _nearestTask0115.value = activeTasks0115
                    .filter { !it.deadline0115.isNullOrEmpty() }
                    .sortedBy { it.deadline0115 }
                    .firstOrNull()
            }
            eventRepository0115.getEventsForUser0115(username0115).onSuccess { events0115 ->
                _upcomingEventCount0115.value = events0115.size
                _nearestEvent0115.value = events0115
                    .filter { !it.eventDate0115.isNullOrEmpty() }
                    .sortedBy { it.eventDate0115 }
                    .firstOrNull()
            }
        }
    }
}

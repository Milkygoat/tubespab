package com.example.tugasbesar.ui.events

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.tugasbesar.data.models.Event0115
import com.example.tugasbesar.data.repositories.EventRepository0115
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class EventViewModel0115(
    private val eventRepository0115: EventRepository0115,
    private val username0115: String
) : ViewModel() {

    private val _eventList0115 = MutableStateFlow<List<Event0115>>(emptyList())
    val eventList0115: StateFlow<List<Event0115>> = _eventList0115.asStateFlow()

    private val _isLoading0115 = MutableStateFlow(false)
    val isLoading0115: StateFlow<Boolean> = _isLoading0115.asStateFlow()

    private val _errorMessage0115 = MutableStateFlow<String?>(null)
    val errorMessage0115: StateFlow<String?> = _errorMessage0115.asStateFlow()

    fun loadEvents0115() {
        viewModelScope.launch {
            _isLoading0115.value = true
            eventRepository0115.getEventsForUser0115(username0115)
                .onSuccess { _eventList0115.value = it }
                .onFailure { _errorMessage0115.value = it.message }
            _isLoading0115.value = false
        }
    }

    suspend fun createEvent0115(event0115: Event0115) {
        _isLoading0115.value = true
        eventRepository0115.createEvent0115(event0115)
            .onFailure { _errorMessage0115.value = it.message }
        loadEvents0115()
    }

    suspend fun updateEvent0115(id0115: String, updates0115: Map<String, Any>) {
        _isLoading0115.value = true
        eventRepository0115.updateEvent0115(id0115, updates0115)
            .onFailure { _errorMessage0115.value = it.message }
        loadEvents0115()
    }

    suspend fun deleteEvent0115(id0115: String) {
        _isLoading0115.value = true
        eventRepository0115.deleteEvent0115(id0115)
            .onFailure { _errorMessage0115.value = it.message }
        loadEvents0115()
    }
}

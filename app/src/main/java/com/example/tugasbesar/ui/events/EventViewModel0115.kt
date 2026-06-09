package com.example.tugasbesar.ui.events

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.tugasbesar.data.models.Event0115
import com.example.tugasbesar.data.repositories.EventRepository0115
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class EventViewModel0115(private val repo0115: EventRepository0115) : ViewModel() {
    private val _eventList0115 = MutableStateFlow<List<Event0115>>(emptyList())
    val eventList0115: StateFlow<List<Event0115>> = _eventList0115
    private val _error0115 = MutableStateFlow<String?>(null)
    val error0115: StateFlow<String?> = _error0115
    fun loadEvents0115(userId0115: String) {
        viewModelScope.launch {
            val res0115 = repo0115.getEventsForUser0115(userId0115)
            if (res0115.isSuccess) {
                _eventList0115.value = res0115.getOrNull() ?: emptyList()
            } else {
                _error0115.value = res0115.exceptionOrNull()?.message
            }
        }
    }
    fun createEvent0115(event0115: Event0115, onComplete0115: (Event0115?) -> Unit) {
        viewModelScope.launch {
            val res0115 = repo0115.createEvent0115(event0115)
            if (res0115.isSuccess) {
                onComplete0115(res0115.getOrNull())
            } else {
                _error0115.value = res0115.exceptionOrNull()?.message
                onComplete0115(null)
            }
        }
    }
    fun updateEvent0115(id0115: String, updates0115: Map<String, Any>, onComplete0115: (Event0115?) -> Unit) {
        viewModelScope.launch {
            val res0115 = repo0115.updateEvent0115(id0115, updates0115)
            if (res0115.isSuccess) {
                onComplete0115(res0115.getOrNull())
            } else {
                _error0115.value = res0115.exceptionOrNull()?.message
                onComplete0115(null)
            }
        }
    }
    fun deleteEvent0115(id0115: String, onComplete0115: (Boolean) -> Unit) {
        viewModelScope.launch {
            val res0115 = repo0115.deleteEvent0115(id0115)
            if (res0115.isSuccess) {
                onComplete0115(true)
            } else {
                _error0115.value = res0115.exceptionOrNull()?.message
                onComplete0115(false)
            }
        }
    }
}


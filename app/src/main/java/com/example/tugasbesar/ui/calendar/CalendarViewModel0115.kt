package com.example.tugasbesar.ui.calendar

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.tugasbesar.data.models.CalendarEntry0115
import com.example.tugasbesar.data.models.Event0115
import com.example.tugasbesar.data.models.Task0115
import com.example.tugasbesar.data.repositories.EventRepository0115
import com.example.tugasbesar.data.repositories.TaskRepository0115
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class CalendarViewModel0115(
    private val taskRepository0115: TaskRepository0115,
    private val eventRepository0115: EventRepository0115,
    private val username0115: String
) : ViewModel() {

    private val _allTasks0115 = MutableStateFlow<List<Task0115>>(emptyList())
    private val _allEvents0115 = MutableStateFlow<List<Event0115>>(emptyList())

    private val _entriesForDate0115 = MutableStateFlow<List<CalendarEntry0115>>(emptyList())
    val entriesForDate0115: StateFlow<List<CalendarEntry0115>> = _entriesForDate0115.asStateFlow()

    fun loadAll0115() {
        viewModelScope.launch {
            taskRepository0115.getTasksForUser0115(username0115).onSuccess { _allTasks0115.value = it }
            eventRepository0115.getEventsForUser0115(username0115).onSuccess { _allEvents0115.value = it }
        }
    }

    fun filterByDate0115(dateString0115: String) {
        val entries0115 = mutableListOf<CalendarEntry0115>()
        _allTasks0115.value.filter { it.deadline0115?.startsWith(dateString0115) == true }.forEach { task0115 ->
            entries0115.add(
                CalendarEntry0115(
                    type0115 = "Task",
                    title0115 = task0115.title0115 ?: "",
                    subtitle0115 = task0115.description0115 ?: "",
                    dateTime0115 = task0115.deadline0115 ?: ""
                )
            )
        }
        _allEvents0115.value.filter { it.eventDate0115?.startsWith(dateString0115) == true }.forEach { event0115 ->
            entries0115.add(
                CalendarEntry0115(
                    type0115 = "Event",
                    title0115 = event0115.title0115 ?: "",
                    subtitle0115 = event0115.location0115 ?: "",
                    dateTime0115 = event0115.eventDate0115 ?: ""
                )
            )
        }
        _entriesForDate0115.value = entries0115
    }
}

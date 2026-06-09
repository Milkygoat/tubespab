package com.example.tugasbesar.ui.calendar

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CalendarView
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.tugasbesar.BuildConfig
import com.example.tugasbesar.data.AuthHelper0115
import com.example.tugasbesar.data.models.Event0115
import com.example.tugasbesar.data.models.Task0115
import com.example.tugasbesar.data.network.SupabaseApi0115
import com.example.tugasbesar.data.network.SupabaseClient0115
import com.example.tugasbesar.data.repositories.EventRepository0115
import com.example.tugasbesar.data.repositories.TaskRepository0115
import com.example.tugasbesar.R
import kotlinx.coroutines.launch

class CalendarFragment0115 : Fragment() {
    private lateinit var calendarView0115: CalendarView
    private lateinit var recyclerView0115: RecyclerView
    private lateinit var adapter0115: CalendarListAdapter0115
    private lateinit var taskRepo0115: TaskRepository0115
    private lateinit var eventRepo0115: EventRepository0115
    override fun onCreateView(inflater0115: LayoutInflater, container0115: ViewGroup?, savedInstanceState0115: Bundle?): View? {
        val view0115 = inflater0115.inflate(R.layout.fragment_calendar, container0115, false)
        calendarView0115 = view0115.findViewById(R.id.calendarView)
        recyclerView0115 = view0115.findViewById(R.id.calendarRecyclerView)
        recyclerView0115.layoutManager = LinearLayoutManager(requireContext())
        adapter0115 = CalendarListAdapter0115()
        recyclerView0115.adapter = adapter0115
        val api0115: SupabaseApi0115 = SupabaseClient0115.createApi0115(requireContext(), BuildConfig.SUPABASE_URL0115, BuildConfig.SUPABASE_KEY0115)
        taskRepo0115 = TaskRepository0115(api0115, BuildConfig.SUPABASE_URL0115)
        eventRepo0115 = EventRepository0115(api0115, BuildConfig.SUPABASE_URL0115)
        val userId0115 = AuthHelper0115.getUserId(requireContext())
        calendarView0115.setOnDateChangeListener { _, year0115, month0115, day0115 ->
            val monthAdj0115 = month0115 + 1
            val date0115 = String.format("%04d-%02d-%02d", year0115, monthAdj0115, day0115)
            userId0115?.let {
                viewLifecycleOwner.lifecycleScope.launch {
                    val tasks0115 = taskRepo0115.getTasksForUser0115(it).getOrNull() ?: emptyList()
                    val events0115 = eventRepo0115.getEventsForUser0115(it).getOrNull() ?: emptyList()
                    val combined0115 = mutableListOf<Any>()
                    combined0115.addAll(tasks0115.filter { t -> t.deadline0115?.startsWith(date0115) == true })
                    combined0115.addAll(events0115.filter { e -> e.event_date0115?.startsWith(date0115) == true })
                    adapter0115.submitList0115(combined0115)
                }
            }
        }
        return view0115
    }
}


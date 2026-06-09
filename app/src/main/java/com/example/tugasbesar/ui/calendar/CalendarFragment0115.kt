package com.example.tugasbesar.ui.calendar

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.tugasbesar.BuildConfig
import com.example.tugasbesar.data.PreferencesHelper0115
import com.example.tugasbesar.data.network.SupabaseClient0115
import com.example.tugasbesar.data.repositories.EventRepository0115
import com.example.tugasbesar.data.repositories.TaskRepository0115
import com.example.tugasbesar.databinding.FragmentCalendarBinding
import kotlinx.coroutines.launch
import java.util.Calendar

class CalendarFragment0115 : Fragment() {
    private var binding0115: FragmentCalendarBinding? = null
    private lateinit var viewModel0115: CalendarViewModel0115
    private lateinit var adapter0115: CalendarListAdapter0115

    override fun onCreateView(inflater0115: LayoutInflater, container0115: ViewGroup?, savedInstanceState0115: Bundle?): View {
        binding0115 = FragmentCalendarBinding.inflate(inflater0115, container0115, false)
        return binding0115!!.root
    }

    override fun onViewCreated(view0115: View, savedInstanceState0115: Bundle?) {
        super.onViewCreated(view0115, savedInstanceState0115)
        val username0115 = PreferencesHelper0115.getUsername0115(requireContext()) ?: ""
        val api0115 = SupabaseClient0115.createApi0115(BuildConfig.SUPABASE_URL0115, BuildConfig.SUPABASE_KEY0115)
        val taskRepo0115 = TaskRepository0115(api0115, BuildConfig.SUPABASE_URL0115)
        val eventRepo0115 = EventRepository0115(api0115, BuildConfig.SUPABASE_URL0115)
        val factory0115 = CalendarViewModelFactory0115(taskRepo0115, eventRepo0115, username0115)
        val vm0115: CalendarViewModel0115 by viewModels { factory0115 }
        viewModel0115 = vm0115
        adapter0115 = CalendarListAdapter0115()
        binding0115?.recyclerCalendarEntries0115?.layoutManager = LinearLayoutManager(requireContext())
        binding0115?.recyclerCalendarEntries0115?.adapter = adapter0115
        binding0115?.calendarView0115?.setOnDateChangeListener { _0115, year0115, month0115, dayOfMonth0115 ->
            val dateString0115 = String.format("%04d-%02d-%02d", year0115, month0115 + 1, dayOfMonth0115)
            viewModel0115.filterByDate0115(dateString0115)
        }
        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel0115.entriesForDate0115.collect { entries0115 ->
                    adapter0115.submitList0115(entries0115)
                    if (entries0115.isEmpty()) {
                        binding0115?.layoutEmptyCalendar0115?.visibility = View.VISIBLE
                        binding0115?.recyclerCalendarEntries0115?.visibility = View.GONE
                    } else {
                        binding0115?.layoutEmptyCalendar0115?.visibility = View.GONE
                        binding0115?.recyclerCalendarEntries0115?.visibility = View.VISIBLE
                    }
                }
            }
        }
        viewModel0115.loadAll0115()
        val today0115 = Calendar.getInstance()
        val todayString0115 = String.format("%04d-%02d-%02d", today0115.get(Calendar.YEAR), today0115.get(Calendar.MONTH) + 1, today0115.get(Calendar.DAY_OF_MONTH))
        viewModel0115.filterByDate0115(todayString0115)
    }

    override fun onResume() {
        super.onResume()
        if (::viewModel0115.isInitialized) {
            viewModel0115.loadAll0115()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding0115 = null
    }
}

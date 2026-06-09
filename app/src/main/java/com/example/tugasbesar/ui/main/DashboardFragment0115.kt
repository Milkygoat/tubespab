package com.example.tugasbesar.ui.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.example.tugasbesar.BuildConfig
import com.example.tugasbesar.R
import com.example.tugasbesar.data.PreferencesHelper0115
import com.example.tugasbesar.data.network.SupabaseClient0115
import com.example.tugasbesar.data.repositories.EventRepository0115
import com.example.tugasbesar.data.repositories.TaskRepository0115
import com.example.tugasbesar.databinding.FragmentDashboardBinding
import com.example.tugasbesar.ui.dashboard.DashboardViewModel0115
import com.example.tugasbesar.ui.dashboard.DashboardViewModelFactory0115
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.Locale

class DashboardFragment0115 : Fragment() {
    private var binding0115: FragmentDashboardBinding? = null
    private lateinit var viewModel0115: DashboardViewModel0115

    override fun onCreateView(inflater0115: LayoutInflater, container0115: ViewGroup?, savedInstanceState0115: Bundle?): View {
        binding0115 = FragmentDashboardBinding.inflate(inflater0115, container0115, false)
        return binding0115!!.root
    }

    override fun onViewCreated(view0115: View, savedInstanceState0115: Bundle?) {
        super.onViewCreated(view0115, savedInstanceState0115)
        val username0115 = PreferencesHelper0115.getUsername0115(requireContext()) ?: ""
        val api0115 = SupabaseClient0115.createApi0115(BuildConfig.SUPABASE_URL0115, BuildConfig.SUPABASE_KEY0115)
        val taskRepo0115 = TaskRepository0115(api0115, BuildConfig.SUPABASE_URL0115)
        val eventRepo0115 = EventRepository0115(api0115, BuildConfig.SUPABASE_URL0115)
        val factory0115 = DashboardViewModelFactory0115(taskRepo0115, eventRepo0115, username0115)
        val vm0115: DashboardViewModel0115 by viewModels { factory0115 }
        viewModel0115 = vm0115
        binding0115?.textGreeting0115?.text = getString(R.string.hello_greeting, username0115)
        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                launch {
                    viewModel0115.activeTaskCount0115.collect { count0115 ->
                        binding0115?.textTaskCount0115?.text = count0115.toString()
                    }
                }
                launch {
                    viewModel0115.upcomingEventCount0115.collect { count0115 ->
                        binding0115?.textEventCount0115?.text = count0115.toString()
                    }
                }
                launch {
                    viewModel0115.nearestTask0115.collect { task0115 ->
                        if (task0115 != null) {
                            binding0115?.textNearestTaskTitle0115?.text = task0115.title0115 ?: ""
                            binding0115?.textNearestTaskDeadline0115?.text = formatDateTime0115(task0115.deadline0115)
                        } else {
                            binding0115?.textNearestTaskTitle0115?.text = getString(R.string.no_upcoming_tasks)
                            binding0115?.textNearestTaskDeadline0115?.text = ""
                        }
                    }
                }
                launch {
                    viewModel0115.nearestEvent0115.collect { event0115 ->
                        if (event0115 != null) {
                            binding0115?.textNearestEventTitle0115?.text = event0115.title0115 ?: ""
                            binding0115?.textNearestEventDate0115?.text = formatDateTime0115(event0115.eventDate0115)
                            binding0115?.textNearestEventLocation0115?.text = event0115.location0115 ?: ""
                        } else {
                            binding0115?.textNearestEventTitle0115?.text = getString(R.string.no_upcoming_events)
                            binding0115?.textNearestEventDate0115?.text = ""
                            binding0115?.textNearestEventLocation0115?.text = ""
                        }
                    }
                }
            }
        }
        viewModel0115.loadDashboard0115()
    }

    private fun formatDateTime0115(dateStr0115: String?): String {
        if (dateStr0115.isNullOrEmpty()) return ""
        return try {
            val inputFormat0115 = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss", Locale.getDefault())
            val outputFormat0115 = SimpleDateFormat("MMM dd, yyyy HH:mm", Locale.getDefault())
            val date0115 = inputFormat0115.parse(dateStr0115)
            if (date0115 != null) outputFormat0115.format(date0115) else dateStr0115
        } catch (e0115: Exception) {
            dateStr0115 ?: ""
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding0115 = null
    }
}

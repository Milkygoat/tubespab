package com.example.tugasbesar.ui.events

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.launch
import com.example.tugasbesar.BuildConfig
import com.example.tugasbesar.R
import com.example.tugasbesar.data.PreferencesHelper0115
import com.example.tugasbesar.data.models.Event0115
import com.example.tugasbesar.data.network.SupabaseClient0115
import com.example.tugasbesar.data.repositories.EventRepository0115
import com.example.tugasbesar.databinding.FragmentEventDetailBinding
import com.example.tugasbesar.notifications.AlarmHelper0115
import com.google.android.material.datepicker.MaterialDatePicker
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.google.android.material.timepicker.MaterialTimePicker
import com.google.android.material.timepicker.TimeFormat
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale
import java.util.TimeZone

class EventDetailFragment0115 : Fragment() {
    private var binding0115: FragmentEventDetailBinding? = null
    private lateinit var viewModel0115: EventViewModel0115
    private var selectedEventDate0115: String = ""
    private var eventId0115: String = ""
    private var isEditMode0115 = false

    override fun onCreateView(inflater0115: LayoutInflater, container0115: ViewGroup?, savedInstanceState0115: Bundle?): View {
        binding0115 = FragmentEventDetailBinding.inflate(inflater0115, container0115, false)
        return binding0115!!.root
    }

    override fun onViewCreated(view0115: View, savedInstanceState0115: Bundle?) {
        super.onViewCreated(view0115, savedInstanceState0115)
        val username0115 = PreferencesHelper0115.getUsername0115(requireContext()) ?: ""
        val api0115 = SupabaseClient0115.createApi0115(BuildConfig.SUPABASE_URL0115, BuildConfig.SUPABASE_KEY0115)
        val repo0115 = EventRepository0115(api0115, BuildConfig.SUPABASE_URL0115)
        val factory0115 = EventViewModelFactory0115(repo0115, username0115)
        val vm0115: EventViewModel0115 by viewModels { factory0115 }
        viewModel0115 = vm0115
        eventId0115 = arguments?.getString("eventId0115") ?: ""
        isEditMode0115 = eventId0115.isNotEmpty()
        if (isEditMode0115) {
            binding0115?.toolbarEventDetail0115?.title = getString(R.string.edit_event)
            binding0115?.editEventTitle0115?.setText(arguments?.getString("eventTitle0115") ?: "")
            binding0115?.editEventDescription0115?.setText(arguments?.getString("eventDescription0115") ?: "")
            binding0115?.editEventLocation0115?.setText(arguments?.getString("eventLocation0115") ?: "")
            selectedEventDate0115 = arguments?.getString("eventDate0115") ?: ""
            binding0115?.textSelectedEventDate0115?.text = formatDateTime0115(selectedEventDate0115)
            binding0115?.btnDeleteEvent0115?.visibility = View.VISIBLE
        } else {
            binding0115?.toolbarEventDetail0115?.title = getString(R.string.add_event)
        }
        binding0115?.toolbarEventDetail0115?.setNavigationOnClickListener {
            findNavController().popBackStack()
        }
        binding0115?.btnSelectEventDate0115?.setOnClickListener { showDatePicker0115() }
        binding0115?.btnSaveEvent0115?.setOnClickListener { saveEvent0115(username0115) }
        binding0115?.btnDeleteEvent0115?.setOnClickListener { confirmDelete0115() }
    }

    private fun showDatePicker0115() {
        val datePicker0115 = MaterialDatePicker.Builder.datePicker()
            .setTitleText(getString(R.string.select_date))
            .build()
        datePicker0115.addOnPositiveButtonClickListener { millis0115 ->
            val timePicker0115 = MaterialTimePicker.Builder()
                .setTimeFormat(TimeFormat.CLOCK_24H)
                .setTitleText(getString(R.string.select_time))
                .build()
            timePicker0115.addOnPositiveButtonClickListener {
                val cal0115 = Calendar.getInstance(TimeZone.getTimeZone("UTC"))
                cal0115.timeInMillis = millis0115
                val localCal0115 = Calendar.getInstance()
                localCal0115.set(cal0115.get(Calendar.YEAR), cal0115.get(Calendar.MONTH), cal0115.get(Calendar.DAY_OF_MONTH), timePicker0115.hour, timePicker0115.minute, 0)
                val sdf0115 = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss", Locale.getDefault())
                selectedEventDate0115 = sdf0115.format(localCal0115.time)
                binding0115?.textSelectedEventDate0115?.text = formatDateTime0115(selectedEventDate0115)
            }
            timePicker0115.show(childFragmentManager, "time_picker_0115")
        }
        datePicker0115.show(childFragmentManager, "date_picker_0115")
    }

    private fun saveEvent0115(username0115: String) {
        val title0115 = binding0115?.editEventTitle0115?.text.toString().trim()
        if (title0115.isEmpty()) {
            binding0115?.inputEventTitle0115?.error = getString(R.string.title_required)
            return
        }
        binding0115?.inputEventTitle0115?.error = null
        val description0115 = binding0115?.editEventDescription0115?.text.toString().trim()
        val location0115 = binding0115?.editEventLocation0115?.text.toString().trim()
        viewLifecycleOwner.lifecycleScope.launch {
            binding0115?.btnSaveEvent0115?.isEnabled = false
            if (isEditMode0115) {
                val updates0115 = mutableMapOf<String, Any>(
                    "title" to title0115,
                    "description" to description0115,
                    "location" to location0115
                )
                if (selectedEventDate0115.isNotEmpty()) {
                    updates0115["event_date"] = selectedEventDate0115
                }
                viewModel0115.updateEvent0115(eventId0115, updates0115)
            } else {
                val event0115 = Event0115(
                    username0115 = username0115,
                    title0115 = title0115,
                    description0115 = description0115,
                    location0115 = location0115,
                    eventDate0115 = selectedEventDate0115.ifEmpty { null }
                )
                viewModel0115.createEvent0115(event0115)
            }
            scheduleReminder0115(title0115)
            findNavController().popBackStack()
        }
    }

    private fun scheduleReminder0115(title0115: String) {
        if (selectedEventDate0115.isEmpty()) return
        try {
            val sdf0115 = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss", Locale.getDefault())
            val eventDate0115 = sdf0115.parse(selectedEventDate0115) ?: return
            val reminderTime0115 = eventDate0115.time - 30 * 60 * 1000
            if (reminderTime0115 > System.currentTimeMillis()) {
                AlarmHelper0115.scheduleReminder0115(
                    requireContext(),
                    selectedEventDate0115.hashCode(),
                    reminderTime0115,
                    "Event Reminder",
                    "Event \"$title0115\" starts in 30 minutes"
                )
            }
        } catch (e0115: Exception) {
            e0115.printStackTrace()
        }
    }

    private fun confirmDelete0115() {
        MaterialAlertDialogBuilder(requireContext())
            .setTitle(getString(R.string.delete_confirm_title))
            .setMessage(getString(R.string.delete_event_confirm))
            .setPositiveButton(getString(R.string.delete)) { _0115, _1_0115 ->
                viewLifecycleOwner.lifecycleScope.launch {
                    viewModel0115.deleteEvent0115(eventId0115)
                    findNavController().popBackStack()
                }
            }
            .setNegativeButton(getString(R.string.cancel), null)
            .show()
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

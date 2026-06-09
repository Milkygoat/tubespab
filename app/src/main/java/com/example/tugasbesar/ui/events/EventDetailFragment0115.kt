package com.example.tugasbesar.ui.events

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.example.tugasbesar.data.AuthHelper0115
import com.example.tugasbesar.BuildConfig
import com.example.tugasbesar.data.models.Event0115
import com.example.tugasbesar.data.network.SupabaseApi0115
import com.example.tugasbesar.data.network.SupabaseClient0115
import com.example.tugasbesar.data.repositories.EventRepository0115
import com.example.tugasbesar.R
import kotlinx.coroutines.launch
import com.google.android.material.snackbar.Snackbar
import android.app.AlertDialog

class EventDetailFragment0115 : Fragment() {
    private lateinit var titleEditText0115: EditText
    private lateinit var descEditText0115: EditText
    private lateinit var locationEditText0115: EditText
    private lateinit var dateEditText0115: EditText
    private lateinit var saveButton0115: Button
    private lateinit var viewModel0115: EventViewModel0115
    private var eventId0115: String? = null
    override fun onCreateView(inflater0115: LayoutInflater, container0115: ViewGroup?, savedInstanceState0115: Bundle?): View? {
        val view0115 = inflater0115.inflate(R.layout.fragment_event_detail, container0115, false)
        titleEditText0115 = view0115.findViewById(R.id.titleEditText)
        descEditText0115 = view0115.findViewById(R.id.descriptionEditText)
        locationEditText0115 = view0115.findViewById(R.id.locationEditText)
        dateEditText0115 = view0115.findViewById(R.id.dateEditText)
        saveButton0115 = view0115.findViewById(R.id.saveButton)
        val api0115: SupabaseApi0115 = SupabaseClient0115.createApi0115(requireContext(), BuildConfig.SUPABASE_URL0115, BuildConfig.SUPABASE_KEY0115)
        val repo0115 = EventRepository0115(api0115, BuildConfig.SUPABASE_URL0115)
        viewModel0115 = ViewModelProvider(this, EventViewModelFactory0115(repo0115)).get(EventViewModel0115::class.java)
        eventId0115 = arguments?.getString("eventId0115")
        saveButton0115.setOnClickListener {
            val title0115 = titleEditText0115.text.toString()
            val desc0115 = descEditText0115.text.toString()
            val location0115 = locationEditText0115.text.toString()
            val date0115 = dateEditText0115.text.toString()
            if (title0115.isBlank()) {
                Snackbar.make(view0115, "Title required", Snackbar.LENGTH_SHORT).show()
                return@setOnClickListener
            }
            val userId0115 = AuthHelper0115.getUserId(requireContext())
            val dialog0115 = AlertDialog.Builder(requireContext()).setView(android.widget.ProgressBar(requireContext())).setCancelable(false).create()
            dialog0115.show()
            if (eventId0115 == null) {
                val event0115 = Event0115(title0115 = title0115, description0115 = desc0115, location0115 = location0115, event_date0115 = date0115, user_id0115 = userId0115)
                viewLifecycleOwner.lifecycleScope.launch {
                    viewModel0115.createEvent0115(event0115) { created0115 ->
                        dialog0115.dismiss()
                        if (created0115 != null) {
                            findNavController().navigateUp()
                        } else {
                            Snackbar.make(view0115, "Failed to create event", Snackbar.LENGTH_LONG).show()
                        }
                    }
                }
            } else {
                val updates0115 = mapOf("title" to title0115, "description" to desc0115, "location" to location0115, "event_date" to date0115)
                viewLifecycleOwner.lifecycleScope.launch {
                    viewModel0115.updateEvent0115(eventId0115!!, updates0115) { updated0115 ->
                        dialog0115.dismiss()
                        if (updated0115 != null) {
                            findNavController().navigateUp()
                        } else {
                            Snackbar.make(view0115, "Failed to update event", Snackbar.LENGTH_LONG).show()
                        }
                    }
                }
            }
        }
        return view0115
    }
}


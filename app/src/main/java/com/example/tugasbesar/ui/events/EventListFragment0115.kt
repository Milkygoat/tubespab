package com.example.tugasbesar.ui.events

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.tugasbesar.BuildConfig
import com.example.tugasbesar.R
import com.example.tugasbesar.data.PreferencesHelper0115
import com.example.tugasbesar.data.network.SupabaseClient0115
import com.example.tugasbesar.data.repositories.EventRepository0115
import com.example.tugasbesar.databinding.FragmentEventListBinding
import kotlinx.coroutines.launch

class EventListFragment0115 : Fragment() {
    private var binding0115: FragmentEventListBinding? = null
    private lateinit var adapter0115: EventAdapter0115
    private lateinit var viewModel0115: EventViewModel0115

    override fun onCreateView(inflater0115: LayoutInflater, container0115: ViewGroup?, savedInstanceState0115: Bundle?): View {
        binding0115 = FragmentEventListBinding.inflate(inflater0115, container0115, false)
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
        adapter0115 = EventAdapter0115 { event0115 ->
            val bundle0115 = bundleOf(
                "eventId0115" to (event0115.id0115 ?: ""),
                "eventTitle0115" to (event0115.title0115 ?: ""),
                "eventDescription0115" to (event0115.description0115 ?: ""),
                "eventLocation0115" to (event0115.location0115 ?: ""),
                "eventDate0115" to (event0115.eventDate0115 ?: "")
            )
            findNavController().navigate(R.id.action_eventList_to_eventDetail0115, bundle0115)
        }
        binding0115?.recyclerEvents0115?.layoutManager = LinearLayoutManager(requireContext())
        binding0115?.recyclerEvents0115?.adapter = adapter0115
        binding0115?.fabAddEvent0115?.setOnClickListener {
            findNavController().navigate(R.id.action_eventList_to_eventDetail0115)
        }
        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel0115.eventList0115.collect { events0115 ->
                    adapter0115.submitList0115(events0115)
                    if (events0115.isEmpty()) {
                        binding0115?.layoutEmptyEvents0115?.visibility = View.VISIBLE
                        binding0115?.recyclerEvents0115?.visibility = View.GONE
                    } else {
                        binding0115?.layoutEmptyEvents0115?.visibility = View.GONE
                        binding0115?.recyclerEvents0115?.visibility = View.VISIBLE
                    }
                }
            }
        }
        viewModel0115.loadEvents0115()
    }

    override fun onResume() {
        super.onResume()
        if (::viewModel0115.isInitialized) {
            viewModel0115.loadEvents0115()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding0115 = null
    }
}

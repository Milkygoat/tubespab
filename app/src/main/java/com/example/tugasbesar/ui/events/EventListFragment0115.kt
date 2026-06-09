package com.example.tugasbesar.ui.events

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.tugasbesar.data.AuthHelper0115
import com.example.tugasbesar.BuildConfig
import com.example.tugasbesar.data.network.SupabaseApi0115
import com.example.tugasbesar.data.network.SupabaseClient0115
import com.example.tugasbesar.data.repositories.EventRepository0115
import com.example.tugasbesar.R
import com.google.android.material.floatingactionbutton.FloatingActionButton
import kotlinx.coroutines.launch
import androidx.navigation.fragment.findNavController

class EventListFragment0115 : Fragment() {
    private lateinit var recyclerView0115: RecyclerView
    private lateinit var fab0115: FloatingActionButton
    private lateinit var adapter0115: EventAdapter0115
    private lateinit var viewModel0115: EventViewModel0115
    override fun onCreateView(inflater0115: LayoutInflater, container0115: ViewGroup?, savedInstanceState0115: Bundle?): View? {
        val view0115 = inflater0115.inflate(R.layout.fragment_event_list, container0115, false)
        recyclerView0115 = view0115.findViewById(R.id.eventRecyclerView)
        fab0115 = view0115.findViewById(R.id.addEventFab)
        adapter0115 = EventAdapter0115 { event0115 ->
            val bundle0115 = Bundle().apply { putString("eventId0115", event0115.id0115) }
            findNavController().navigate(R.id.action_events_to_eventDetail, bundle0115)
        }
        recyclerView0115.layoutManager = LinearLayoutManager(requireContext())
        recyclerView0115.adapter = adapter0115
        val api0115: SupabaseApi0115 = SupabaseClient0115.createApi0115(requireContext(), BuildConfig.SUPABASE_URL0115, BuildConfig.SUPABASE_KEY0115)
        val repo0115 = EventRepository0115(api0115, BuildConfig.SUPABASE_URL0115)
        viewModel0115 = ViewModelProvider(this, EventViewModelFactory0115(repo0115)).get(EventViewModel0115::class.java)
        fab0115.setOnClickListener {
            findNavController().navigate(R.id.action_events_to_eventDetail)
        }
        val userId0115 = AuthHelper0115.getUserId(requireContext())
        userId0115?.let {
            viewLifecycleOwner.lifecycleScope.launch {
                viewModel0115.loadEvents0115(it)
            }
        }
        viewLifecycleOwner.lifecycleScope.launch {
            viewModel0115.eventList0115.collect { list0115 ->
                adapter0115.submitList(list0115)
            }
        }
        return view0115
    }
}


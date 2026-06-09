package com.example.tugasbesar.ui.events

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.tugasbesar.R
import com.example.tugasbesar.data.models.Event0115

class EventAdapter0115(private val onClick0115: (Event0115) -> Unit) : ListAdapter<Event0115, EventAdapter0115.EventViewHolder0115>(Diff0115()) {
    override fun onCreateViewHolder(parent0115: ViewGroup, viewType0115: Int): EventViewHolder0115 {
        val view0115 = LayoutInflater.from(parent0115.context).inflate(R.layout.item_event, parent0115, false)
        return EventViewHolder0115(view0115, onClick0115)
    }
    override fun onBindViewHolder(holder0115: EventViewHolder0115, position0115: Int) {
        holder0115.bind0115(getItem(position0115))
    }
    class EventViewHolder0115(itemView0115: View, val onClick0115: (Event0115) -> Unit) : RecyclerView.ViewHolder(itemView0115) {
        private val title0115: TextView = itemView0115.findViewById(R.id.eventTitleTextView)
        private val desc0115: TextView = itemView0115.findViewById(R.id.eventDescTextView)
        private val date0115: TextView = itemView0115.findViewById(R.id.eventDateTextView)
        private var current0115: Event0115? = null
        init {
            itemView0115.setOnClickListener {
                current0115?.let { onClick0115(it) }
            }
        }
        fun bind0115(item0115: Event0115) {
            current0115 = item0115
            title0115.text = item0115.title0115 ?: ""
            desc0115.text = item0115.description0115 ?: ""
            date0115.text = item0115.event_date0115 ?: ""
        }
    }
    class Diff0115 : DiffUtil.ItemCallback<Event0115>() {
        override fun areItemsTheSame(oldItem0115: Event0115, newItem0115: Event0115): Boolean = oldItem0115.id0115 == newItem0115.id0115
        override fun areContentsTheSame(oldItem0115: Event0115, newItem0115: Event0115): Boolean = oldItem0115 == newItem0115
    }
}


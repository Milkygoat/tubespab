package com.example.tugasbesar.ui.events

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.tugasbesar.data.models.Event0115
import com.example.tugasbesar.databinding.ItemEventBinding
import java.text.SimpleDateFormat
import java.util.Locale

class EventAdapter0115(
    private val onItemClick0115: (Event0115) -> Unit
) : RecyclerView.Adapter<EventAdapter0115.EventViewHolder0115>() {

    private var eventList0115: List<Event0115> = emptyList()

    fun submitList0115(list0115: List<Event0115>) {
        eventList0115 = list0115
        notifyDataSetChanged()
    }

    inner class EventViewHolder0115(private val binding0115: ItemEventBinding) : RecyclerView.ViewHolder(binding0115.root) {
        fun bind0115(event0115: Event0115) {
            binding0115.textEventTitle0115.text = event0115.title0115 ?: ""
            binding0115.textEventDescription0115.text = event0115.description0115 ?: ""
            binding0115.textEventDate0115.text = formatDate0115(event0115.eventDate0115)
            binding0115.textEventLocation0115.text = event0115.location0115 ?: ""
            binding0115.root.setOnClickListener { onItemClick0115(event0115) }
        }
    }

    override fun onCreateViewHolder(parent0115: ViewGroup, viewType0115: Int): EventViewHolder0115 {
        val binding0115 = ItemEventBinding.inflate(LayoutInflater.from(parent0115.context), parent0115, false)
        return EventViewHolder0115(binding0115)
    }

    override fun onBindViewHolder(holder0115: EventViewHolder0115, position0115: Int) {
        holder0115.bind0115(eventList0115[position0115])
    }

    override fun getItemCount(): Int = eventList0115.size

    private fun formatDate0115(dateStr0115: String?): String {
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
}

package com.example.tugasbesar.ui.calendar

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.tugasbesar.R
import com.example.tugasbesar.data.models.Event0115
import com.example.tugasbesar.data.models.Task0115

class CalendarListAdapter0115 : RecyclerView.Adapter<CalendarListAdapter0115.VH0115>() {
    private var items0115: List<Any> = emptyList()
    fun submitList0115(list0115: List<Any>) {
        items0115 = list0115
        notifyDataSetChanged()
    }
    override fun onCreateViewHolder(parent0115: ViewGroup, viewType0115: Int): VH0115 {
        val v0115 = LayoutInflater.from(parent0115.context).inflate(R.layout.item_calendar_entry, parent0115, false)
        return VH0115(v0115)
    }
    override fun onBindViewHolder(holder0115: VH0115, position0115: Int) {
        val obj0115 = items0115[position0115]
        if (obj0115 is Task0115) {
            holder0115.title0115.text = obj0115.title0115 ?: ""
            holder0115.subtitle0115.text = obj0115.deadline0115 ?: ""
        } else if (obj0115 is Event0115) {
            holder0115.title0115.text = obj0115.title0115 ?: ""
            holder0115.subtitle0115.text = obj0115.event_date0115 ?: ""
        }
    }
    override fun getItemCount(): Int = items0115.size
    class VH0115(itemView0115: View) : RecyclerView.ViewHolder(itemView0115) {
        val title0115: TextView = itemView0115.findViewById(R.id.calendarItemTitle)
        val subtitle0115: TextView = itemView0115.findViewById(R.id.calendarItemSubtitle)
    }
}


package com.example.tugasbesar.ui.calendar

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.tugasbesar.R
import com.example.tugasbesar.data.models.CalendarEntry0115
import com.example.tugasbesar.databinding.ItemCalendarEntryBinding

class CalendarListAdapter0115 : RecyclerView.Adapter<CalendarListAdapter0115.EntryViewHolder0115>() {

    private var entries0115: List<CalendarEntry0115> = emptyList()

    fun submitList0115(list0115: List<CalendarEntry0115>) {
        entries0115 = list0115
        notifyDataSetChanged()
    }

    inner class EntryViewHolder0115(private val binding0115: ItemCalendarEntryBinding) : RecyclerView.ViewHolder(binding0115.root) {
        fun bind0115(entry0115: CalendarEntry0115) {
            binding0115.textEntryTitle0115.text = entry0115.title0115
            binding0115.textEntrySubtitle0115.text = entry0115.subtitle0115
            binding0115.chipType0115.text = entry0115.type0115
            if (entry0115.type0115 == "Task") {
                binding0115.chipType0115.setChipBackgroundColorResource(R.color.md_theme_light_primaryContainer)
            } else {
                binding0115.chipType0115.setChipBackgroundColorResource(R.color.md_theme_light_tertiaryContainer)
            }
        }
    }

    override fun onCreateViewHolder(parent0115: ViewGroup, viewType0115: Int): EntryViewHolder0115 {
        val binding0115 = ItemCalendarEntryBinding.inflate(LayoutInflater.from(parent0115.context), parent0115, false)
        return EntryViewHolder0115(binding0115)
    }

    override fun onBindViewHolder(holder0115: EntryViewHolder0115, position0115: Int) {
        holder0115.bind0115(entries0115[position0115])
    }

    override fun getItemCount(): Int = entries0115.size
}

package com.example.tugasbesar.ui.tasks

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.tugasbesar.data.models.Task0115
import com.example.tugasbesar.R

class TaskAdapter0115(private val onClick0115: (Task0115) -> Unit) : ListAdapter<Task0115, TaskAdapter0115.TaskViewHolder0115>(Diff0115()) {
    override fun onCreateViewHolder(parent0115: ViewGroup, viewType0115: Int): TaskViewHolder0115 {
        val view0115 = LayoutInflater.from(parent0115.context).inflate(R.layout.item_task, parent0115, false)
        return TaskViewHolder0115(view0115, onClick0115)
    }
    override fun onBindViewHolder(holder0115: TaskViewHolder0115, position0115: Int) {
        holder0115.bind0115(getItem(position0115))
    }
    class TaskViewHolder0115(itemView0115: View, val onClick0115: (Task0115) -> Unit) : RecyclerView.ViewHolder(itemView0115) {
        private val title0115: TextView = itemView0115.findViewById(R.id.titleTextView)
        private val desc0115: TextView = itemView0115.findViewById(R.id.descTextView)
        private val deadline0115: TextView = itemView0115.findViewById(R.id.deadlineTextView)
        private var current0115: Task0115? = null
        init {
            itemView0115.setOnClickListener {
                current0115?.let { onClick0115(it) }
            }
        }
        fun bind0115(item0115: Task0115) {
            current0115 = item0115
            title0115.text = item0115.title0115 ?: ""
            desc0115.text = item0115.description0115 ?: ""
            deadline0115.text = item0115.deadline0115 ?: ""
        }
    }
    class Diff0115 : DiffUtil.ItemCallback<Task0115>() {
        override fun areItemsTheSame(oldItem0115: Task0115, newItem0115: Task0115): Boolean = oldItem0115.id0115 == newItem0115.id0115
        override fun areContentsTheSame(oldItem0115: Task0115, newItem0115: Task0115): Boolean = oldItem0115 == newItem0115
    }
}


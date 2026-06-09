package com.example.tugasbesar.ui.tasks

import android.view.LayoutInflater
import android.view.ViewGroup
import android.graphics.Paint
import androidx.recyclerview.widget.RecyclerView
import com.example.tugasbesar.data.models.Task0115
import com.example.tugasbesar.databinding.ItemTaskBinding
import java.text.SimpleDateFormat
import java.util.Locale

class TaskAdapter0115(
    private val onItemClick0115: (Task0115) -> Unit,
    private val onCheckChange0115: (Task0115) -> Unit
) : RecyclerView.Adapter<TaskAdapter0115.TaskViewHolder0115>() {

    private var taskList0115: List<Task0115> = emptyList()

    fun submitList0115(list0115: List<Task0115>) {
        taskList0115 = list0115
        notifyDataSetChanged()
    }

    inner class TaskViewHolder0115(private val binding0115: ItemTaskBinding) : RecyclerView.ViewHolder(binding0115.root) {
        fun bind0115(task0115: Task0115) {
            binding0115.textTaskTitle0115.text = task0115.title0115 ?: ""
            binding0115.textTaskDescription0115.text = task0115.description0115 ?: ""
            binding0115.textTaskDeadline0115.text = formatDate0115(task0115.deadline0115)
            val isCompleted0115 = task0115.status0115 ?: false
            binding0115.checkboxStatus0115.isChecked = isCompleted0115
            if (isCompleted0115) {
                binding0115.textTaskTitle0115.paintFlags = binding0115.textTaskTitle0115.paintFlags or Paint.STRIKE_THRU_TEXT_FLAG
            } else {
                binding0115.textTaskTitle0115.paintFlags = binding0115.textTaskTitle0115.paintFlags and Paint.STRIKE_THRU_TEXT_FLAG.inv()
            }
            binding0115.checkboxStatus0115.setOnClickListener { onCheckChange0115(task0115) }
            binding0115.root.setOnClickListener { onItemClick0115(task0115) }
        }
    }

    override fun onCreateViewHolder(parent0115: ViewGroup, viewType0115: Int): TaskViewHolder0115 {
        val binding0115 = ItemTaskBinding.inflate(LayoutInflater.from(parent0115.context), parent0115, false)
        return TaskViewHolder0115(binding0115)
    }

    override fun onBindViewHolder(holder0115: TaskViewHolder0115, position0115: Int) {
        holder0115.bind0115(taskList0115[position0115])
    }

    override fun getItemCount(): Int = taskList0115.size

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

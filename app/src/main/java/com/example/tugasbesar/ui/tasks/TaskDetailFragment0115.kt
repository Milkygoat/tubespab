package com.example.tugasbesar.ui.tasks

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
import com.example.tugasbesar.BuildConfig
import com.example.tugasbesar.data.AuthHelper0115
import com.example.tugasbesar.data.models.Task0115
import com.example.tugasbesar.data.network.SupabaseApi0115
import com.example.tugasbesar.data.network.SupabaseClient0115
import com.example.tugasbesar.data.repositories.TaskRepository0115
import com.example.tugasbesar.R
import kotlinx.coroutines.launch
import com.google.android.material.snackbar.Snackbar
import android.app.AlertDialog

class TaskDetailFragment0115 : Fragment() {
    private lateinit var titleEditText0115: EditText
    private lateinit var descEditText0115: EditText
    private lateinit var deadlineEditText0115: EditText
    private lateinit var saveButton0115: Button
    private lateinit var viewModel0115: TaskViewModel0115
    private var taskId0115: String? = null
    override fun onCreateView(inflater0115: LayoutInflater, container0115: ViewGroup?, savedInstanceState0115: Bundle?): View? {
        val view0115 = inflater0115.inflate(R.layout.fragment_task_detail, container0115, false)
        titleEditText0115 = view0115.findViewById(R.id.titleEditText)
        descEditText0115 = view0115.findViewById(R.id.descriptionEditText)
        deadlineEditText0115 = view0115.findViewById(R.id.deadlineEditText)
        saveButton0115 = view0115.findViewById(R.id.saveButton)
        val api0115: SupabaseApi0115 = SupabaseClient0115.createApi0115(requireContext(), BuildConfig.SUPABASE_URL0115, BuildConfig.SUPABASE_KEY0115)
        val repo0115 = TaskRepository0115(api0115, BuildConfig.SUPABASE_URL0115)
        viewModel0115 = ViewModelProvider(this, TaskViewModelFactory0115(repo0115)).get(TaskViewModel0115::class.java)
        taskId0115 = arguments?.getString("taskId0115")
        saveButton0115.setOnClickListener {
            val title0115 = titleEditText0115.text.toString()
            val desc0115 = descEditText0115.text.toString()
            val deadline0115 = deadlineEditText0115.text.toString()
            if (title0115.isBlank()) {
                Snackbar.make(view0115, "Title required", Snackbar.LENGTH_SHORT).show()
                return@setOnClickListener
            }
            val userId0115 = AuthHelper0115.getUserId(requireContext())
            val dialog0115 = AlertDialog.Builder(requireContext()).setView(android.widget.ProgressBar(requireContext())).setCancelable(false).create()
            dialog0115.show()
            if (taskId0115 == null) {
                val task0115 = Task0115(title0115 = title0115, description0115 = desc0115, deadline0115 = deadline0115, user_id0115 = userId0115, status0115 = false)
                viewLifecycleOwner.lifecycleScope.launch {
                    viewModel0115.createTask0115(task0115) { created0115 ->
                        dialog0115.dismiss()
                        if (created0115 != null) {
                            findNavController().navigateUp()
                        } else {
                            Snackbar.make(view0115, "Failed to create task", Snackbar.LENGTH_LONG).show()
                        }
                    }
                }
            } else {
                val updates0115 = mapOf("title" to title0115, "description" to desc0115, "deadline" to deadline0115)
                viewLifecycleOwner.lifecycleScope.launch {
                    viewModel0115.updateTask0115(taskId0115!!, updates0115) { updated0115 ->
                        dialog0115.dismiss()
                        if (updated0115 != null) {
                            findNavController().navigateUp()
                        } else {
                            Snackbar.make(view0115, "Failed to update task", Snackbar.LENGTH_LONG).show()
                        }
                    }
                }
            }
        }
        return view0115
    }
}


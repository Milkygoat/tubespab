package com.example.tugasbesar.ui.auth

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import com.google.android.material.snackbar.Snackbar
import androidx.fragment.app.Fragment
import android.content.Context
import android.content.SharedPreferences
import androidx.navigation.fragment.findNavController
import com.example.tugasbesar.R

class LoginFragment0115 : Fragment() {
    private lateinit var emailEditText0115: EditText
    private lateinit var passwordEditText0115: EditText
    private lateinit var loginButton0115: Button
    private lateinit var prefs0115: SharedPreferences
    override fun onCreateView(inflater0115: LayoutInflater, container0115: ViewGroup?, savedInstanceState0115: Bundle?): View? {
        val view0115 = inflater0115.inflate(R.layout.fragment_login, container0115, false)
        emailEditText0115 = view0115.findViewById(R.id.emailEditText)
        passwordEditText0115 = view0115.findViewById(R.id.passwordEditText)
        loginButton0115 = view0115.findViewById(R.id.loginButton)
        prefs0115 = requireContext().getSharedPreferences("planmate_prefs0115", Context.MODE_PRIVATE)

        // Simplified flow: use the first input (emailEditText) as a name and bypass remote auth
        loginButton0115.setOnClickListener {
            val name0115 = emailEditText0115.text.toString().trim()
            if (name0115.isBlank()) {
                Snackbar.make(view0115, "Please enter your name", Snackbar.LENGTH_SHORT).show()
                return@setOnClickListener
            }
            // Save name locally as user id/display name
            prefs0115.edit().putString("user_id0115", name0115).putString("display_name0115", name0115).apply()
            findNavController().navigate(R.id.action_login_to_tasks)
        }

        view0115.findViewById<View>(R.id.registerLink).setOnClickListener {
            findNavController().navigate(R.id.action_login_to_register)
        }

        return view0115
    }
}

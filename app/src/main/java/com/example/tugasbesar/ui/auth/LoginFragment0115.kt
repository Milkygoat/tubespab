package com.example.tugasbesar.ui.auth

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import com.google.android.material.snackbar.Snackbar
import android.app.AlertDialog
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.example.tugasbesar.BuildConfig
import com.example.tugasbesar.data.network.SupabaseApi0115
import com.example.tugasbesar.data.network.SupabaseClient0115
import com.example.tugasbesar.data.repositories.AuthRepository0115
import com.example.tugasbesar.R
import kotlinx.coroutines.launch

class LoginFragment0115 : Fragment() {
    private lateinit var emailEditText0115: EditText
    private lateinit var passwordEditText0115: EditText
    private lateinit var loginButton0115: Button
    private lateinit var viewModel0115: AuthViewModel0115
    override fun onCreateView(inflater0115: LayoutInflater, container0115: ViewGroup?, savedInstanceState0115: Bundle?): View? {
        val view0115 = inflater0115.inflate(R.layout.fragment_login, container0115, false)
        emailEditText0115 = view0115.findViewById(R.id.emailEditText)
        passwordEditText0115 = view0115.findViewById(R.id.passwordEditText)
        loginButton0115 = view0115.findViewById(R.id.loginButton)
        val api0115: SupabaseApi0115 = SupabaseClient0115.createApi0115(requireContext(), BuildConfig.SUPABASE_URL0115, BuildConfig.SUPABASE_KEY0115)
        val repo0115 = AuthRepository0115(api0115, requireContext())
        viewModel0115 = ViewModelProvider(this, AuthViewModelFactory0115(repo0115)).get(AuthViewModel0115::class.java)
        loginButton0115.setOnClickListener {
            val email0115 = emailEditText0115.text.toString()
            val pass0115 = passwordEditText0115.text.toString()
            if (email0115.isBlank() || pass0115.length < 6) {
                Snackbar.make(view0115, "Invalid email or password (min 6)", Snackbar.LENGTH_SHORT).show()
                return@setOnClickListener
            }
            val dialog0115 = AlertDialog.Builder(requireContext()).setView(android.widget.ProgressBar(requireContext())).setCancelable(false).create()
            dialog0115.show()
            viewModel0115.signIn0115(email0115, pass0115)
            viewLifecycleOwner.lifecycleScope.launch {
                viewModel0115.authState0115.collect { auth0115 ->
                    if (auth0115 != null) {
                        dialog0115.dismiss()
                        findNavController().navigate(R.id.action_login_to_tasks)
                    }
                }
            }
            viewLifecycleOwner.lifecycleScope.launch {
                viewModel0115.errorState0115.collect { err0115 ->
                    if (err0115 != null) {
                        dialog0115.dismiss()
                        Snackbar.make(view0115, err0115, Snackbar.LENGTH_LONG).show()
                    }
                }
            }
        }
        view0115.findViewById<View>(R.id.registerLink).setOnClickListener {
            findNavController().navigate(R.id.action_login_to_register)
        }
        return view0115
    }
}


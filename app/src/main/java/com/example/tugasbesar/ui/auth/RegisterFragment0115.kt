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

class RegisterFragment0115 : Fragment() {
    private lateinit var emailEditText0115: EditText
    private lateinit var passwordEditText0115: EditText
    private lateinit var registerButton0115: Button
    private lateinit var viewModel0115: AuthViewModel0115
    private var progressDialog0115: AlertDialog? = null
    override fun onCreateView(inflater0115: LayoutInflater, container0115: ViewGroup?, savedInstanceState0115: Bundle?): View? {
        val view0115 = inflater0115.inflate(R.layout.fragment_register, container0115, false)
        emailEditText0115 = view0115.findViewById(R.id.emailEditText)
        passwordEditText0115 = view0115.findViewById(R.id.passwordEditText)
        registerButton0115 = view0115.findViewById(R.id.registerButton)
        val api0115: SupabaseApi0115 = SupabaseClient0115.createApi0115(requireContext(), BuildConfig.SUPABASE_URL0115, BuildConfig.SUPABASE_KEY0115)
        val repo0115 = AuthRepository0115(api0115, requireContext())
        viewModel0115 = ViewModelProvider(this, AuthViewModelFactory0115(repo0115)).get(AuthViewModel0115::class.java)
        registerButton0115.setOnClickListener {
            val email0115 = emailEditText0115.text.toString()
            val pass0115 = passwordEditText0115.text.toString()
            if (email0115.isBlank() || pass0115.length < 6) {
                Snackbar.make(view0115, "Invalid email or password (min 6)", Snackbar.LENGTH_SHORT).show()
                return@setOnClickListener
            }
            progressDialog0115 = AlertDialog.Builder(requireContext()).setView(android.widget.ProgressBar(requireContext())).setCancelable(false).create()
            progressDialog0115?.show()
            viewModel0115.signUp0115(email0115, pass0115)
        }
        // Observe once
        viewLifecycleOwner.lifecycleScope.launchWhenStarted {
            viewModel0115.authState0115.collect { auth0115 ->
                if (auth0115 != null) {
                    progressDialog0115?.dismiss()
                    findNavController().navigate(R.id.action_register_to_tasks)
                }
            }
        }
        viewLifecycleOwner.lifecycleScope.launchWhenStarted {
            viewModel0115.errorState0115.collect { err0115 ->
                if (err0115 != null) {
                    progressDialog0115?.dismiss()
                    Snackbar.make(view0115, err0115, Snackbar.LENGTH_LONG).show()
                }
            }
        }

        return view0115
    }
}


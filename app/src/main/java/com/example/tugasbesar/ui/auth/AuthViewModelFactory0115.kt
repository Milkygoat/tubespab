package com.example.tugasbesar.ui.auth

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.tugasbesar.data.repositories.AuthRepository0115

class AuthViewModelFactory0115(private val repo0115: AuthRepository0115) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass0115: Class<T>): T {
        if (modelClass0115.isAssignableFrom(AuthViewModel0115::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return AuthViewModel0115(repo0115) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class0115")
    }
}


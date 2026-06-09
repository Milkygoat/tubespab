package com.example.tugasbesar.ui.auth

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.tugasbesar.data.models.AuthResponse0115
import com.example.tugasbesar.data.repositories.AuthRepository0115
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class AuthViewModel0115(private val repo0115: AuthRepository0115) : ViewModel() {
    private val _authState0115 = MutableStateFlow<AuthResponse0115?>(null)
    val authState0115: StateFlow<AuthResponse0115?> = _authState0115
    private val _errorState0115 = MutableStateFlow<String?>(null)
    val errorState0115: StateFlow<String?> = _errorState0115
    fun signUp0115(email0115: String, password0115: String) {
        viewModelScope.launch {
            val res0115 = repo0115.signUp0115(email0115, password0115)
            if (res0115.isSuccess) {
                _authState0115.value = res0115.getOrNull()
            } else {
                _errorState0115.value = res0115.exceptionOrNull()?.message
            }
        }
    }
    fun signIn0115(email0115: String, password0115: String) {
        viewModelScope.launch {
            val res0115 = repo0115.signIn0115(email0115, password0115)
            if (res0115.isSuccess) {
                _authState0115.value = res0115.getOrNull()
            } else {
                _errorState0115.value = res0115.exceptionOrNull()?.message
            }
        }
    }
    fun signOut0115() {
        repo0115.signOut0115()
        _authState0115.value = null
    }
    fun getUserId0115(): String? = repo0115.getUserId0115()
}


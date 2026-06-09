package com.example.tugasbesar.ui.welcome

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.tugasbesar.data.PreferencesHelper0115
import com.example.tugasbesar.databinding.ActivityWelcomeBinding
import com.example.tugasbesar.ui.main.MainActivity0115

class WelcomeActivity0115 : AppCompatActivity() {
    private lateinit var binding0115: ActivityWelcomeBinding

    override fun onCreate(savedInstanceState0115: Bundle?) {
        super.onCreate(savedInstanceState0115)
        val existingName0115 = PreferencesHelper0115.getUsername0115(this)
        if (!existingName0115.isNullOrBlank()) {
            startMainActivity0115()
            return
        }
        binding0115 = ActivityWelcomeBinding.inflate(layoutInflater)
        setContentView(binding0115.root)
        binding0115.btnGetStarted0115.setOnClickListener {
            val name0115 = binding0115.editName0115.text.toString().trim()
            if (name0115.isEmpty()) {
                binding0115.inputLayoutName0115.error = getString(com.example.tugasbesar.R.string.name_required)
                return@setOnClickListener
            }
            binding0115.inputLayoutName0115.error = null
            PreferencesHelper0115.saveUsername0115(this, name0115)
            startMainActivity0115()
        }
    }

    private fun startMainActivity0115() {
        val intent0115 = Intent(this, MainActivity0115::class.java)
        startActivity(intent0115)
        finish()
    }
}

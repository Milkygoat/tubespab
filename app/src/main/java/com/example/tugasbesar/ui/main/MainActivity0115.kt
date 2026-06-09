package com.example.tugasbesar.ui.main

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import com.example.tugasbesar.R
import com.example.tugasbesar.databinding.ActivityMainBinding
import com.example.tugasbesar.notifications.NotificationHelper0115

class MainActivity0115 : AppCompatActivity() {
    private lateinit var binding0115: ActivityMainBinding

    override fun onCreate(savedInstanceState0115: Bundle?) {
        super.onCreate(savedInstanceState0115)
        binding0115 = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding0115.root)
        NotificationHelper0115.createChannel0115(this)
        val navHostFragment0115 = supportFragmentManager.findFragmentById(R.id.nav_host_fragment_0115) as NavHostFragment
        val navController0115 = navHostFragment0115.navController
        binding0115.bottomNav0115.setupWithNavController(navController0115)
    }
}

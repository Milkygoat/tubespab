package com.example.tugasbesar.ui.main

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import androidx.navigation.fragment.NavHostFragment
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.example.tugasbesar.R

class MainActivity0115 : AppCompatActivity() {
    override fun onCreate(savedInstanceState0115: Bundle?) {
        super.onCreate(savedInstanceState0115)
        setContentView(R.layout.activity_main)
        // Try to get the NavController from the NavHostFragment first (more reliable at startup)
        val navHostFragment = supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as? NavHostFragment
        val navController0115 = navHostFragment?.navController ?: run {
            // Fallback — this may throw if nothing is attached yet, so wrap defensively
            try {
                findNavController(R.id.nav_host_fragment)
            } catch (e: IllegalStateException) {
                // No NavController available yet. Return and avoid crash — activity can set it up later.
                e.printStackTrace()
                return
            }
        }

        setupActionBarWithNavController(navController0115)
        val bottomNav0115: BottomNavigationView? = findViewById(R.id.bottom_nav)
        bottomNav0115?.setupWithNavController(navController0115)
    }
    override fun onSupportNavigateUp(): Boolean {
        val navController0115 = findNavController(R.id.nav_host_fragment)
        return navController0115.navigateUp() || super.onSupportNavigateUp()
    }
}


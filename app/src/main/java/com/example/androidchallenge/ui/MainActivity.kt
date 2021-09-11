package com.example.androidchallenge.ui

import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.NavigationUI
import com.example.androidchallenge.R

import androidx.navigation.fragment.NavHostFragment




class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(findViewById(com.example.androidchallenge.R.id.toolbar))

        val navHostFragment =
            supportFragmentManager.findFragmentById(com.example.androidchallenge.R.id.main_content) as NavHostFragment?
        navHostFragment?.let { navHost ->
            val navController = navHost.navController
            val appBarConfiguration = AppBarConfiguration(navController.graph)
            NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration)
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == android.R.id.home) {
            onBackPressed()
            return true
        }
        return true
    }
}
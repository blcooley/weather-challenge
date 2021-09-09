package com.example.androidchallenge.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.androidchallenge.R

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(findViewById(R.id.toolbar))

        if (savedInstanceState == null) {
            val fragment = CurrentWeatherFragment()
            fragment.arguments = intent.extras
            supportFragmentManager
                .beginTransaction()
                .add(R.id.main_content, fragment)
                .commit()
        }
    }
}
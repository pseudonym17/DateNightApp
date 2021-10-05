package com.example.datenightapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Hides the title bar at top
        supportActionBar?.hide()

        Handler().postDelayed({
            val intent = Intent(this@MainActivity, Login::class.java)
            startActivity(intent)
        }, 3000)
    }
}
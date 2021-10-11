package com.example.datenightapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class MatchesPage : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_matches_page)

        supportActionBar?.hide()

        val button = findViewById<Button>(R.id.homeButton)

        button.setOnClickListener{
            val intent = Intent(this, HomePage::class.java)
            startActivity(intent)
        }
    }
}
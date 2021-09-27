package com.example.datenightapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class HomePage : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home_page)

        supportActionBar?.hide()

        val button1 = findViewById<Button>(R.id.home_to_swipe_button)
        val button2 = findViewById<Button>(R.id.home_to_saved)

        button1.setOnClickListener{
            val intent = Intent(this, SwipePage::class.java)
            startActivity(intent)
        }

        button2.setOnClickListener{
            val intent = Intent(this, SavedActivitiesPage::class.java)
            startActivity(intent)
        }
    }
}
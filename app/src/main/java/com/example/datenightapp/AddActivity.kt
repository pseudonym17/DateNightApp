package com.example.datenightapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText

class AddActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add)

        supportActionBar?.hide()

        val addBtn = findViewById<Button>(R.id.addBtn)
        addBtn.setOnClickListener{
            val title = findViewById<EditText>(R.id.title)
            val description = findViewById<EditText>(R.id.description)
            val address = findViewById<EditText>(R.id.address)
            val price = findViewById<EditText>(R.id.price)
            // Here add an activity to the database
        }

        val homeBtn = findViewById<Button>(R.id.homeBtn)
        homeBtn.setOnClickListener {
            val intent = Intent(this, HomePage::class.java)
            startActivity(intent)
        }
    }
}
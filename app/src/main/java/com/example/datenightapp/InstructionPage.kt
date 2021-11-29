package com.example.datenightapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class InstructionPage : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_instruction_page)

        supportActionBar?.hide()

        val btn = findViewById<Button>(R.id.backBtn)
        btn.setOnClickListener{
            val intent = Intent(this, SwipePage::class.java)
            startActivity(intent)
        }
    }
}
package com.example.datenightapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class SignUpPage : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_up_page)

        supportActionBar?.hide()

        val button = findViewById<Button>(R.id.signupbtn)

        button.setOnClickListener{
            val intent = Intent(this, HomePage:: class.java)
            startActivity(intent)
        }
    }
}
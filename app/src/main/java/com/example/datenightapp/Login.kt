package com.example.datenightapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class Login : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        supportActionBar?.hide()

        val button1 = findViewById<Button>(R.id.loginbtn)
        val button2 = findViewById<Button>(R.id.signupbtn)

        button1.setOnClickListener{
            val intent = Intent(this, loginPage::class.java)
            startActivity(intent)
        }

        button2.setOnClickListener{
            val intent = Intent(this, SignUpPage::class.java)
            startActivity(intent)
        }
    }
}
package com.example.datenightapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView

class LoginSignupPage : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        supportActionBar?.hide()

        val logo = findViewById<ImageView>(R.id.logo)
        val button1 = findViewById<Button>(R.id.loginbtn)
        val button2 = findViewById<Button>(R.id.signupbtn)
        //val logo = findViewById<ImageView>(R.id.)

        logo.setOnClickListener{
            val intent = Intent(this, HomePage::class.java)
            startActivity(intent)
        }

        button1.setOnClickListener{
            val intent = Intent(this, LoginPage::class.java)
            startActivity(intent)
        }

        button2.setOnClickListener{
            val intent = Intent(this, SignUpPage::class.java)
            startActivity(intent)
        }
    }
}
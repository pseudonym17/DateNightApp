package com.example.datenightapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText

class loginPage : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login_page)

        supportActionBar?.hide()

        val user = findViewById<EditText>(R.id.username)

        Singleton.username = user.toString()

        val button = findViewById<Button>(R.id.loginbtn)

        button.setOnClickListener {
//            write the firebase login function here
            val intent = Intent(this, HomePage::class.java)
            startActivity(intent)
        }
    }
}
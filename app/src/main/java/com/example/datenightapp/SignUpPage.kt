package com.example.datenightapp

import android.content.ContentValues.TAG
import android.content.Intent
import android.media.MediaDataSource
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.firestore.FirebaseFirestore


class SignUpPage : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_up_page)

        supportActionBar?.hide()

        val button = findViewById<Button>(R.id.signupbtn)

        button.setOnClickListener {
//            write create user firebase function here
            val first_name = findViewById<EditText>(R.id.firstname).getText().toString()
            val last_name = findViewById<EditText>(R.id.lastname).getText().toString()
            val user_name = findViewById<EditText>(R.id.username).getText().toString()
            val password = findViewById<EditText>(R.id.password).getText().toString()

            // Write a message to the database
            val db = FirebaseFirestore.getInstance()
            // Create a new user with a first and last name
            val user: MutableMap<String, Any> = HashMap()
            user["first"] = last_name
            user["last"] = first_name
            user["username"] = user_name
            user["password"] = password

            db.collection("users").document(user_name)
                .set(user)
                .addOnSuccessListener {Toast.makeText(this, "test", Toast.LENGTH_SHORT).show() }
                .addOnFailureListener {Toast.makeText(this, "test", Toast.LENGTH_SHORT).show() }
            val intent = Intent(this, HomePage:: class.java)
            startActivity(intent)
            val username = findViewById<EditText>(R.id.username)
            Singleton.username = username.toString()
            }

        }
    }

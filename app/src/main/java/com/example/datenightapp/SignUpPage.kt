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

            // This is checking to see if username and password are correct
            var isValidUser : Boolean = false
            db.collection("users")
                .get()
                .addOnSuccessListener { result ->
                    for (document in result) {
                        val dbUserName = document.id
                        if (user_name == dbUserName) {
                            Toast.makeText(this, "Username already exists", Toast.LENGTH_SHORT)
                                .show()
                            break
                        } else {
                            isValidUser = true
                        }
                    }
                }
                .addOnFailureListener { exception ->
                    Log.w(TAG, "Error getting documents.", exception)
                }

            if (isValidUser){
                db.collection("users").document(user_name)
                    .set(user)
                    .addOnSuccessListener {}
                    .addOnFailureListener {Toast.makeText(this, "Connection Error", Toast.LENGTH_SHORT).show() }
                val intent = Intent(this, HomePage:: class.java)
                startActivity(intent)
            }

            val usern = findViewById<EditText>(R.id.username).text.toString()
            Singleton.username = usern

            }
        }
    }

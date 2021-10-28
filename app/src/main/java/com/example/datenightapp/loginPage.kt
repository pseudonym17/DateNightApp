package com.example.datenightapp

import android.content.ContentValues.TAG
import android.content.Intent
import android.media.MediaDataSource
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.google.firebase.firestore.FirebaseFirestore

class loginPage : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login_page)

        supportActionBar?.hide()

        val button = findViewById<Button>(R.id.loginbtn)
        val db = FirebaseFirestore.getInstance()

        button.setOnClickListener{
//            write the firebase login function here
            val user_name = findViewById<EditText>(R.id.username)
            val password = findViewById<EditText>(R.id.password)

            db.collection("users")
                .get()
                .addOnSuccessListener { result ->
                    for (document in result) {
                        Toast.makeText(this, "${document.id} => ${document.data["password"]}", Toast.LENGTH_SHORT).show()
                    }
                }
                .addOnFailureListener { exception ->
                    Log.w(TAG, "Error getting documents.", exception)
                }
        }
    }
}
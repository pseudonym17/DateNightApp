package com.example.datenightapp

import android.content.ContentValues.TAG
import android.content.Intent
import android.media.MediaDataSource
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
<<<<<<< HEAD
import android.widget.Toast
import com.google.firebase.firestore.FirebaseFirestore
=======
>>>>>>> 88d6153e23be29d3175d5a94d34565e6ca60e129

class loginPage : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login_page)

        supportActionBar?.hide()

        val user = findViewById<EditText>(R.id.username)

        Singleton.username = user.toString()

        val button = findViewById<Button>(R.id.loginbtn)
        val db = FirebaseFirestore.getInstance()

        button.setOnClickListener {
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
            val intent = Intent(this, HomePage::class.java)
            startActivity(intent)
        }
    }
}
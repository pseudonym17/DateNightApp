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

class LoginPage : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login_page)

        supportActionBar?.hide()

        val button = findViewById<Button>(R.id.loginbtn)
        val db = FirebaseFirestore.getInstance()

        button.setOnClickListener {
//            write the firebase login function here

            var user_name = findViewById<EditText>(R.id.username).getText().toString()
            var password = findViewById<EditText>(R.id.password).getText().toString()

            // Makes whitespace not effect authentication
            user_name = user_name.replace("\\s".toRegex(),"");
            password = password.replace("\\s".toRegex(),"");

            // This is checking to see if username and password are correct
            var isValidUser : Boolean = false
            db.collection("users")
                .get()
                .addOnSuccessListener { result ->
                    for (document in result) {
                        val dbUserName = document.id
                        val dbPassword = document.data["password"].toString()

                        //If the password and username match, lets them in
                        if (user_name == dbUserName && password == dbPassword ) {
                            Singleton.username = user_name
                            isValidUser = true
                            val intent = Intent(this, HomePage::class.java)
                            startActivity(intent)
                            break
                        }
                    }
                    // If not, clears text fields and gives error message
                    if (!isValidUser) {
                        findViewById<EditText>(R.id.username).text.clear();
                        findViewById<EditText>(R.id.password).text.clear();
                        Toast.makeText(this, "Username or Password incorrect", Toast.LENGTH_SHORT)
                            .show()
                    }
                }
                .addOnFailureListener { exception ->
                    Log.w(TAG, "Error getting documents.", exception)
                }

        }
    }
}
package com.example.datenightapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.google.firebase.firestore.FirebaseFirestore


class AddActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add)

        supportActionBar?.hide()

        val addBtn = findViewById<Button>(R.id.addBtn)
        addBtn.setOnClickListener{
            val title = findViewById<EditText>(R.id.title).getText().toString()
            val description = findViewById<EditText>(R.id.description).getText().toString()
            val address = findViewById<EditText>(R.id.address).getText().toString()
            val price = findViewById<EditText>(R.id.price).getText().toString()
            // Here add an activity to the database
            val db = FirebaseFirestore.getInstance()
            val activity: MutableMap<String, Any> = HashMap()
            activity["title"] = title
            activity["description"] = description
            activity["address"] = address
            activity["price"] = price

            var highest = 0
            db.collection("activities").get()
                .addOnSuccessListener { result ->

                    for (document in result){
//                        println(document.id)
                        if (document.id.toInt() > highest){
                             highest = document.id.toInt()
                        }
                    }
//                    println("The highest is")
//                    println(highest)
                    highest += 1
                }

            db.collection("activities").document(highest.toString())
                .set(activity)
                .addOnSuccessListener {}
                .addOnFailureListener {Toast.makeText(this, "Connection Error", Toast.LENGTH_SHORT).show() }
        }

        val homeBtn = findViewById<Button>(R.id.homeBtn)
        homeBtn.setOnClickListener {
            val intent = Intent(this, HomePage::class.java)
            startActivity(intent)
        }
    }
}
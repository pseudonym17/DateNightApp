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

        val db = FirebaseFirestore.getInstance()
        val addBtn = findViewById<Button>(R.id.addBtn)
        addBtn.setOnClickListener{
            val title = findViewById<EditText>(R.id.title)
            val description = findViewById<EditText>(R.id.description)
            val address = findViewById<EditText>(R.id.address)
            val price = findViewById<EditText>(R.id.price)
            val url = findViewById<EditText>(R.id.url)

            // Here add an activity to the database
            val activity: MutableMap<String, Any> = HashMap()
            activity["name"] = title
            activity["description"] = description
            activity["address"] = address
            activity["price"] = price
            activity["image_url"] = url

            // Needs document number
            db.collection("activities").document()
                .set(activity)
                .addOnSuccessListener { Toast.makeText(this, "Successfully Added Activity", Toast.LENGTH_SHORT).show() }
                .addOnFailureListener { Toast.makeText(this, "Connection Error", Toast.LENGTH_SHORT).show() }
        }

        val homeBtn = findViewById<Button>(R.id.homeBtn)
        homeBtn.setOnClickListener {
            val intent = Intent(this, HomePage::class.java)
            startActivity(intent)
        }
    }
}
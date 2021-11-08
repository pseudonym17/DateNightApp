package com.example.datenightapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import com.google.firebase.firestore.FirebaseFirestore

class SavedActivitiesPage : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_saved_activities_page)

        supportActionBar?.hide()

        val button = findViewById<Button>(R.id.homebtn)
        val logo = findViewById<ImageView>(R.id.logo)
        button.setOnClickListener{
            val intent = Intent(this, HomePage::class.java)
            startActivity(intent)
        }
        logo.setOnClickListener{
            val intent = Intent(this, HomePage::class.java)
            startActivity(intent)
        }
        val database = FirebaseFirestore.getInstance()
        val user = Singleton.username
        val title = findViewById<TextView>(R.id.titleText)
        var savedids : MutableList<String?> = ArrayList()
        database.collection("users").document("$user").collection("saved_activities")
            .get()
            .addOnSuccessListener { documents ->
                for (document in documents) {
                    val docId = document.data["id"].toString()
                    savedids.add(docId)
                }
                displayActivities(savedids)
            }
            .addOnFailureListener { exception ->
                println("Error getting documents: ")
            }

    }
    private fun displayActivities(savedids: MutableList<String?>) {

    }
}
package com.example.datenightapp

import android.app.ActionBar
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.ViewGroup
import android.widget.*
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.firestore.FieldValue
import com.google.firebase.firestore.FirebaseFirestore
import com.squareup.picasso.Picasso

class SavedActivitiesPage : AppCompatActivity() {
    private lateinit var adapter: ActivityAdapter
    private lateinit var activityRecyclerView: RecyclerView
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

        var savedIDs : MutableList<String?> = ArrayList()
        database.collection("users").document("$user").collection("saved_activities")
            .get()
            .addOnSuccessListener { documents ->
                for (document in documents) {
                    val docId = document.data["id"].toString()
                    savedIDs.add(docId)
                }
                createActivityList(savedIDs)
            }
            .addOnFailureListener { exception ->
                println("Error getting documents: ")
            }

        // What happens on activity swiping
        val db = database.collection("users").document("$user")
        val clear = findViewById<Button>(R.id.clearbtn)
        clear.setOnClickListener{
            db.collection("saved_activities")
                .get().addOnSuccessListener { documents ->
                    for (doc in documents) {
                        val docVal = doc.data["id"].toString()
                        val docId = doc.id
                        savedIDs.remove(docVal)
                        db.collection("saved_activities")
                            .document(docId).delete()
                    }
                    createActivityList(savedIDs)
                }
        }

    }
    private fun createActivityList(savedIDs: MutableList<String?>) {
        val database = FirebaseFirestore.getInstance()

        var activityList : ArrayList<Activity> = ArrayList()

        database.collection("activities")
            .get()
            .addOnSuccessListener { documents ->
                for (document in documents) {
                    val docId = document.id
                    // If docId is in savedids
                    for (id in savedIDs) {
                        if (id == docId) {
                            val name = document.data["name"].toString()
                            val description = document.data["description"].toString()
                            val address = document.data["address"].toString()
                            val price = document.data["price"].toString()
                            val imageUrl = document.data["image_url"].toString()
                            val activity =
                                Activity(name, description, address, price, imageUrl, docId)
                            activityList.add(activity)
                            println("We found id: $docId")
                        }
                    }
                }
                displayActivities(activityList)
            }
            .addOnFailureListener { exception ->
                println("Error getting documents: ")
            }
    }
    private fun displayActivities(activityList: ArrayList<Activity>) {
        adapter = ActivityAdapter(this, activityList)
        activityRecyclerView = findViewById(R.id.activityRecyclerView)
        activityRecyclerView.layoutManager = LinearLayoutManager(this)
        activityRecyclerView.adapter = adapter
    }
}
package com.example.datenightapp

import android.app.ActionBar
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import com.google.firebase.firestore.FirebaseFirestore
import com.squareup.picasso.Picasso

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

    }
    private fun createActivityList(savedIDs: MutableList<String?>) {
        val database = FirebaseFirestore.getInstance()

        var activityList : MutableList<Activity> = ArrayList()

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
    private fun displayActivities(activity: MutableList<Activity>) {
        if (activity.size > 0) {
            for (av in activity) {
                val layout = findViewById<LinearLayout>(R.id.layout)

                // Add Title
                val title = TextView(this)
                title.layoutParams = LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT,
                    ViewGroup.LayoutParams.WRAP_CONTENT)
                val color = resources.getColor(R.color.white)
                title.setTextColor(color)
                title.text = av.name
                layout.addView(title)

                // Add Image
                val picasso = Picasso.get()
                val image = ImageView(this)
                image.layoutParams = LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT,
                    ViewGroup.LayoutParams.WRAP_CONTENT, )
                image.layoutParams.height = 200
                image.layoutParams.width = 200

                picasso.load(av.image_url).into(image)
                layout.addView(image)

                //description.setText(av.description)
                //location.setText(av.address)
                //price.setText(av.price)
            }
        }
    }
}
package com.example.datenightapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.ViewGroup
import android.widget.*
import com.google.firebase.firestore.FirebaseFirestore
import com.squareup.picasso.Picasso

class MatchesPage : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_matches_page)

        supportActionBar?.hide()

        val homeButton = findViewById<Button>(R.id.homeButton)
        val logo = findViewById<ImageView>(R.id.logo)
        val matchButton = findViewById<Button>(R.id.matchbtn)
        val database = FirebaseFirestore.getInstance()
        val user = Singleton.username

        var myIDs : MutableList<String?> = ArrayList()
        var otherIDs : MutableList<String?> = ArrayList()

//      Match button starts getting the matches from the database
        matchButton.setOnClickListener{
            val other = findViewById<EditText>(R.id.editText).getText().toString()
//          First the id's of my liked activites are saved into a list
            println(other)
            database.collection("users").document("$user").collection("saved_activities")
                .get()
                .addOnSuccessListener { documents ->
                    for (document in documents) {
                        val docId = document.data["id"].toString()
                        println(document)
                        myIDs.add(docId)
                    }
                }
                .addOnFailureListener { exception ->
                    println("Error getting documents: ")
                }
//          Second the id's of the liked activities of the other user are saved into a seperate list
            database.collection("users").document("$other").collection("saved_activities")
                .get()
                .addOnSuccessListener { documents ->
                    for (document in documents) {
                        val docId = document.data["id"].toString()
                        otherIDs.add(docId)
                    }
                    println("test")
                    match_ids(otherIDs, myIDs)
                }
                .addOnFailureListener { exception ->
                    println("Error getting documents: ")
                }
        }

//      Home button sends you to homescreen
        homeButton.setOnClickListener{
            val intent = Intent(this, HomePage::class.java)
            startActivity(intent)
        }
//      Logo button also sends you to homescreen
        logo.setOnClickListener{
            val intent = Intent(this, HomePage::class.java)
            startActivity(intent)
        }
    }

//    Function that matches ids to another username
    private fun match_ids(otherIDs: MutableList<String?>, myIDs: MutableList<String?>){
        var savedIDs : MutableList<String?> = ArrayList()
        for (id in myIDs){
            for (otherid in otherIDs){
                if (id == otherid){
                    savedIDs.add(id)
                }
            }
        }
        println(savedIDs)
        val distinct = savedIDs.toSet().toMutableList()
        println(distinct)
        createActivityList(distinct)
}

//  Function that creates the list of activites based on the id's that are passed into it
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

//  Function that displays the activities that are passed into it
    private fun displayActivities(activity: MutableList<Activity>) {
    val layout = findViewById<LinearLayout>(R.id.layout)
    layout.removeAllViews();
    if (activity.size > 0) {
            for (av in activity) {

                // Add Title
                val title = TextView(this)
                title.layoutParams = LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.WRAP_CONTENT,
                    ViewGroup.LayoutParams.WRAP_CONTENT)
                val color = resources.getColor(R.color.white)
                title.setTextColor(color)
                title.text = av.name
                layout.addView(title)

                // Add Image
                val picasso = Picasso.get()
                val image = ImageView(this)
                image.layoutParams = LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.WRAP_CONTENT,
                    ViewGroup.LayoutParams.WRAP_CONTENT, )
                image.layoutParams.height = 200
                image.layoutParams.width = 200

                picasso.load(av.image_url).into(image)
                layout.addView(image)
            }
        }
    }
}


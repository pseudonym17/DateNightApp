package com.example.datenightapp

import android.content.Intent
import android.media.Image
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.*
import com.google.firebase.firestore.FirebaseFirestore
import com.squareup.picasso.Picasso

class SwipePage : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_swipe_page)

        supportActionBar?.hide()

        val swipeImg = findViewById<ImageView>(R.id.swipeImg)
        val database = FirebaseFirestore.getInstance()

        var activitylist : MutableList<Activity> = ArrayList()
        var indexCap = 0
        var index = 0

        var user = Singleton.username
        var savedIDs : MutableList<String?> = ArrayList()
        database.collection("users").document("$user").collection("saved_activities")
            .get()
            .addOnSuccessListener { documents ->
                for (document in documents) {
                    val docId = document.data["id"].toString()
                    savedIDs.add(docId)
                }
                database.collection("activities")
                    .get()
                    .addOnSuccessListener { documents ->
                        for (document in documents) {
                            val docId = document.id
                            // Check to see if user has already saved activity
                            var alreadySaved = false
                            for (id in savedIDs) {
                                if (id == docId) {
                                    alreadySaved = true
                                    break
                                }
                            }
                            // If it isn't saved by user already, add to list
                            if (!alreadySaved) {
                                val name = document.data["name"].toString()
                                val description = document.data["description"].toString()
                                val address = document.data["address"].toString()
                                val price = document.data["price"].toString()
                                val image_url = document.data["image_url"].toString()
                                val activity = Activity(name, description, address, price, image_url, docId)
                                activitylist.add(activity)
                            }
                        }
                        indexCap = activitylist.size - 2
                        // Shuffle ActivityList
                        activitylist.shuffle()
                        nextActivity(activitylist, 0)
                    }
                    .addOnFailureListener { exception ->
                        println("Error getting documents: ")
                    }
            }
            .addOnFailureListener { exception ->
                println("Error getting saved activities")
            }

        // What happens on activity swiping
        swipeImg.setOnTouchListener(object : OnSwipeTouchListener(this@SwipePage) {
            // On swiping right, save activity, then go to next one
            override fun onSwipeRight() {
                //Add activity to saved list
                addSavedActivity(activitylist, index)
                // Increase index unless at end, then go back to 0
                if (index < indexCap)
                    index++
                else
                    index = 0

                nextActivity(activitylist, index)
            }

            // On swipeleft increase the index, and go to next activity
            override fun onSwipeLeft() {
                if (index < indexCap)
                    index++
                else
                    index = 0
                nextActivity(activitylist, index)
            }
        })

        val button = findViewById<Button>(R.id.homeButton)
        val logo = findViewById<ImageView>(R.id.logo)
//        val infoBtn = findViewById<Button>(R.id.get_help)
//
//        infoBtn.setOnClickListener{
//            val intent = Intent(this, HomePage::class.java)
//            startActivity(intent)
//        }

        button.setOnClickListener{
            val intent = Intent(this, HomePage::class.java)
            startActivity(intent)
        }
        logo.setOnClickListener{
            val intent = Intent(this, HomePage::class.java)
            startActivity(intent)
        }
    }

    private fun nextActivity(activitylist: MutableList<Activity>, index: Int) {
        val picasso = Picasso.get()
        val title = findViewById<TextView>(R.id.activityTitle)
        val description = findViewById<TextView>(R.id.activityDescription)
        val location = findViewById<TextView>(R.id.location)
        val price = findViewById<TextView>(R.id.price)
        val img = findViewById<ImageView>(R.id.swipeImg)

        title.setText(activitylist[index].name)
        description.setText(activitylist[index].description)
        location.setText(activitylist[index].address)
        price.setText(activitylist[index].price)
        picasso.load(activitylist[index].image_url).into(img)
    }

    private fun addSavedActivity(activitylist: MutableList<Activity>,index: Int) {
        val user = Singleton.username
        val db = FirebaseFirestore.getInstance()
        val id = activitylist[index].docId
        val data = hashMapOf("id" to "$id")

        db.collection("users").document("$user").collection("saved_activities").add(data)
        //Toast.makeText(this, "ID Saved: $id", Toast.LENGTH_SHORT).show()
    }

}
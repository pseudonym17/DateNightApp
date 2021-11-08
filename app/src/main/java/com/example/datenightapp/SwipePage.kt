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
        database.collection("activities")
            .get()
            .addOnSuccessListener { documents ->
                for (document in documents) {
//                    println("${document.id} => ${document.data["name"]}")
                    //val id = document.id
                    //Toast.makeText(this, "ID: $id", Toast.LENGTH_LONG).show()
                    val name = document.data["name"].toString()
                    val description = document.data["description"].toString()
                    val address = document.data["address"].toString()
                    val price = document.data["price"].toString()
                    val image_url = document.data["image_url"].toString()
                    val docId = document.id
                    val activity = Activity(name, description, address, price, image_url, docId)
                    activitylist.add(activity)
                    nextActivity(activitylist, 0)
                }
                indexCap = activitylist.size - 2
            }
            .addOnFailureListener { exception ->
                println("Error getting documents: ")
            }


        swipeImg.setOnTouchListener(object : OnSwipeTouchListener(this@SwipePage) {
            override fun onSwipeRight() {
                // Store the row at the current index into a table
                //Add activity to saved list
                addSavedActivity(activitylist, index)
                if (index < indexCap)
                    index++
                else
                    index = 0

                nextActivity(activitylist, index)
            }

            override fun onSwipeLeft() {
                Toast.makeText(this@SwipePage, "Next Activity", Toast.LENGTH_SHORT)
                    .show();
                if (index < indexCap)
                    index++
                else
                    index = 0

                nextActivity(activitylist, index)
            }
        })

        val button = findViewById<Button>(R.id.homeButton)
        val logo = findViewById<ImageView>(R.id.logo)
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
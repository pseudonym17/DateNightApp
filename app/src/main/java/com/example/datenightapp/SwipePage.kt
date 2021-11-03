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

        val title = findViewById<TextView>(R.id.activityTitle)
        val description = findViewById<TextView>(R.id.activityDescription)
        val location = findViewById<TextView>(R.id.location)
        val price = findViewById<TextView>(R.id.price)

        val picasso = Picasso.get()
        val database = FirebaseFirestore.getInstance()

        //val activity : Activity(title, description, location, price)
        var activitylist : MutableList<Activity> = ArrayList()
        var indexCap = 0
        var index = 0
        database.collection("activities")
            .get()
            .addOnSuccessListener { documents ->
                println("Starting to retrieve docs")
                for (document in documents) {
//                    println("${document.id} => ${document.data["name"]}")
                    val name = document.data["name"].toString()
                    val description = document.data["description"].toString()
                    val address = document.data["address"].toString()
                    val price = document.data["price"].toString()
                    val image_url = document.data["image_url"].toString()
                    val activity = Activity(name, description, address, price, image_url)
                    activitylist.add(activity)
                    showFirstActivity(activitylist)
                }
                indexCap = activitylist.size - 2
            }
            .addOnFailureListener { exception ->
                println("Error getting documents: ")
            }


        val pictureFiles = arrayOf(
            R.drawable.img,
            R.drawable.rockclimbing,
            R.drawable.ropes_course,
            R.drawable.little_caesars,
            R.drawable.hart_pool,
            R.drawable.disc_golf,
            R.drawable.rmountain,
            R.drawable.apple_orchard,
            R.drawable.sledding,
            R.drawable.hickory,
            R.drawable.rigby_lake
        )

//        swipeImg.setBackgroundResource(pictureFiles[index])

        swipeImg.setOnTouchListener(object : OnSwipeTouchListener(this@SwipePage) {
            override fun onSwipeRight() {
                // Store the row at the current index into a table
                Toast.makeText(this@SwipePage, "Saved Activity", Toast.LENGTH_SHORT)
                    .show();
                if (index < indexCap)
                    index++
                else
                    index = 0
//                swipeImg.setBackgroundResource(pictureFiles[index])
                nextActivity(activitylist, index)
            }

            override fun onSwipeLeft() {
                Toast.makeText(this@SwipePage, "Next Activity", Toast.LENGTH_SHORT)
                    .show();
                if (index < indexCap)
                    index++
                else
                    index = 0
//                swipeImg.setBackgroundResource(pictureFiles[index])
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
    private fun showFirstActivity(activitylist : MutableList<Activity>) {
        val picasso = Picasso.get()
        val title = findViewById<TextView>(R.id.activityTitle)
        val description = findViewById<TextView>(R.id.activityDescription)
        val location = findViewById<TextView>(R.id.location)
        val price = findViewById<TextView>(R.id.price)
        val img = findViewById<ImageView>(R.id.swipeImg)

        title.setText(activitylist[0].name)
        description.setText(activitylist[0].description)
        location.setText(activitylist[0].address)
        price.setText(activitylist[0].price)
        picasso.load(activitylist[0].image_url).into(img)

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
        picasso.load(activitylist[0].image_url).into(img)

    }
}
package com.example.datenightapp

import android.content.Intent
import android.media.Image
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.*

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
        var index = 0
        // On create, query the db fill array of the activities.
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
        val activitiesArr = arrayOf(
            arrayOf("img", "Games at the MC", "Board and table games including: pool, foosball, airhockey, and more", "WHERE: Manwarring Center at BYU-I", "PRICE: Free"),
            arrayOf("img", "Rock Climbing", "Rock climbing and bouldering", "WHERE: Address", "PRICE: ?"),
            arrayOf("img", "Ropes Course", "Climb through the obstacle course of ropes and zipline through the air", "WHERE: South BYU-I", "PRICE: $6?"),
            arrayOf("img", "Little Caesars", "Pizza, breadsticks, and drinks for a low price", "WHERE: Address", "PRICE: $5-$10"),
            arrayOf("img", "Swimming", "Swimming pool at the Hart building on campus", "WHERE: Hart Building BYU-I", "PRICE: Free"),
            arrayOf("img", "Disc Golf", "Disc Golf course at the Nature Park", "WHERE: Address", "PRICE: $0-$25"),
            arrayOf("img", "R Mountain", "Hike and see the views from R Mountain", "WHERE: R Mountain", "PRICE: Free"),
            arrayOf("img", "Apple Picking", "Pick apples at the orchards on campus", "WHERE: BYU-I", "PRICE: $1-$5"),
            arrayOf("img", "Sledding", "Sled all over the hills at the dunes", "WHERE: St. Anthony Dunes", "PRICE: Cost of a sled"),
            arrayOf("img", "Hickory", "Dine at Rexburg's most popular steak restaurant", "WHERE: Address", "PRICE: $20-$50"),
            arrayOf("img", "Rigby Lake", "Swim, sunbath, camp, kayak, and more", "WHERE: Jefferson County Lake", "PRICE: $6"),
        )

        var indexCap = activitiesArr.size - 2
        
        swipeImg.setBackgroundResource(pictureFiles[index])
        title.setText(activitiesArr[index][1])
        description.setText(activitiesArr[index][2])
        location.setText(activitiesArr[index][3])
        price.setText(activitiesArr[index][4])


        swipeImg.setOnTouchListener(object : OnSwipeTouchListener(this@SwipePage) {
            override fun onSwipeRight() {
                // Store the row at the current index into a table
                Toast.makeText(this@SwipePage, "Saved Activity", Toast.LENGTH_SHORT)
                    .show();
                if (index < indexCap)
                    index++
                else
                    index = 0
                swipeImg.setBackgroundResource(pictureFiles[index])
                title.setText(activitiesArr[index][1])
                description.setText(activitiesArr[index][2])
                location.setText(activitiesArr[index][3])
                price.setText(activitiesArr[index][4])
            }

            override fun onSwipeLeft() {
                Toast.makeText(this@SwipePage, "Next Activity", Toast.LENGTH_SHORT)
                    .show();
                if (index < indexCap)
                    index++
                else
                    index = 0
                swipeImg.setBackgroundResource(pictureFiles[index])
                title.setText(activitiesArr[index][1])
                description.setText(activitiesArr[index][2])
                location.setText(activitiesArr[index][3])
                price.setText(activitiesArr[index][4])

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
}
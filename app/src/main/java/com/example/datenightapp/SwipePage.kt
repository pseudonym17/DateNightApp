package com.example.datenightapp

import android.content.Intent
import android.media.Image
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.RelativeLayout
import android.widget.Toast

class SwipePage : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_swipe_page)

        supportActionBar?.hide()

        val swiper = findViewById<ImageView>(R.id.swipeImg)


        swiper.setOnTouchListener(object : OnSwipeTouchListener(this@SwipePage) {
            override fun onSwipeRight() {

                Toast.makeText(this@SwipePage, "Swipe Right gesture detected", Toast.LENGTH_SHORT)
                    .show();

            }

            override fun onSwipeLeft() {
                Toast.makeText(this@SwipePage, "Swipe Left gesture detected", Toast.LENGTH_SHORT)
                    .show();

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
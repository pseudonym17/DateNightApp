package com.example.datenightapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import com.google.firebase.firestore.FirebaseFirestore

class HomePage : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home_page)

        supportActionBar?.hide()

        val button1 = findViewById<Button>(R.id.home_to_swipe_button)
        val button2 = findViewById<Button>(R.id.home_to_saved)
        val button3 = findViewById<Button>(R.id.matchbtn)
        val button4 = findViewById<Button>(R.id.add_act)

        val user = Singleton.username
        val welcome = findViewById<TextView>(R.id.Welcome)
        welcome.text = ("${welcome.text}\n${user}")

        button1.setOnClickListener{
            val intent = Intent(this, SwipePage::class.java)
            startActivity(intent)
        }

        button2.setOnClickListener{
            val intent = Intent(this, SavedActivitiesPage::class.java)
            startActivity(intent)
        }

        button3.setOnClickListener{
            val intent = Intent(this, MatchesPage::class.java)
            startActivity(intent)
        }

        button4.setOnClickListener{
            val intent = Intent(this, AddActivity::class.java)
            startActivity(intent)
        }
    }
}
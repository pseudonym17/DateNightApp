package com.example.datenightapp

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso

class ActivityAdapter (val context: Context, val activityList: ArrayList<Activity>):
    RecyclerView.Adapter<ActivityAdapter.ActivityViewHolder>() {

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ActivityViewHolder {
            val view: View = LayoutInflater.from(context).inflate(R.layout.activity_layout, parent, false)
            return ActivityViewHolder(view)
        }

        override fun onBindViewHolder(holder: ActivityViewHolder, position: Int) {
            val currentActivity = activityList[position]
            holder.textName.text = currentActivity.name
            holder.price.text = currentActivity.price
            val picasso = Picasso.get()
            picasso.load(currentActivity.image_url).into(holder.image)
        }

        override fun getItemCount(): Int {
            return activityList.size
        }

        class ActivityViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
            val textName = itemView.findViewById<TextView>(R.id.text_name)
            val image = itemView.findViewById<ImageView>(R.id.image)
            val price = itemView.findViewById<TextView>(R.id.price)
        }
}
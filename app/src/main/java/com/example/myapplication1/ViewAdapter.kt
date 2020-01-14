package com.example.myapplication1

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView


class ViewAdapter:RecyclerView.Adapter<ViewAdapter.ViewHolder> () {

    private val images =
        intArrayOf(R.drawable.plantingtrees, R.drawable.no_plastic)
    private val desc = arrayOf(
        "Plant a Tree and \nGet Free Oxygen",
        "Skip the straw,\nSave a sea turtles life")

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var itemImage: ImageView=itemView.findViewById(R.id.act_img)
        var itemDesc: TextView=itemView.findViewById(R.id.txt_act)



    }

    override fun onCreateViewHolder(viewGroup: ViewGroup, i: Int): ViewHolder {
        val v = LayoutInflater.from(viewGroup.context)
            .inflate(R.layout.activity_list_display, viewGroup, false)



        return ViewHolder(v)

    }

    override fun onBindViewHolder(viewHolder: ViewHolder, i: Int) {

        viewHolder.itemImage.setImageResource(images[i])
        viewHolder.itemDesc.text = desc[i]
        viewHolder.itemView.setOnClickListener {
            if(i == 0){
            val context = viewHolder.itemView.context
            val intent = Intent(context, ActivityTreeMain::class.java)
            context.startActivity(intent)
            }
        }
    }

    override fun getItemCount(): Int {
        return desc.size
    }
}

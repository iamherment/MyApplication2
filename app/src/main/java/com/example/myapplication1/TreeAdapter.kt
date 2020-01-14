package com.example.myapplication1

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.core.content.ContextCompat.startActivity
import androidx.recyclerview.widget.RecyclerView


class TreeAdapter:RecyclerView.Adapter<TreeAdapter.ViewHolder>(){
    //not the code below
    private val images =
        intArrayOf(R.drawable.caliper_tree, R.drawable.pine_tree, R.drawable.linden_tree,R.drawable.cedar_tree,R.drawable.sequoia_tree)
    private val desc = arrayOf(
        "Caliper Tree",
        "Pine Tree",
        "Linden Tree",
        "Cedar Tree ",
        "Sequoia Tree ")
    private val points= intArrayOf(9500,9000,9000,8500,10000)
    private val locations = arrayOf(
        "Raverland, Taman ,Somoland",
        "Raja Musa Forest Reserve,Selangor ",
        "FRIM,Selangor",
        "Malaysia Nature Society Urban Community Forest,Kuala Lumpur ",
        "Kinabatang,Sabah ")

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var itemImage: ImageView =itemView.findViewById(R.id.imgViewTree)
        var itemDesc: TextView =itemView.findViewById(R.id.txtViewTree)
        var itemPoints: Button = itemView.findViewById(R.id.buttonPoints)



    }

    override fun onCreateViewHolder(viewGroup: ViewGroup, i: Int): ViewHolder {
        val v = LayoutInflater.from(viewGroup.context)
            .inflate(R.layout.activity_tree_display, viewGroup, false)
        return ViewHolder(v)
    }

    override fun onBindViewHolder(viewHolder: ViewHolder, i: Int) {

        viewHolder.itemImage.setImageResource(images[i])
        viewHolder.itemDesc.text = desc[i]
        viewHolder.itemPoints.setText(points[i].toString())

        viewHolder.itemPoints.setOnClickListener {
            //viewHolder.itemDesc.text="asd"
            val context = viewHolder.itemView.context
            val intent = Intent(context, DetailsTreePlanted::class.java)/// location activity
            intent.putExtra("tree",desc[i])
            intent.putExtra("points",points[i])
            intent.putExtra("location",locations[i])
            intent.putExtra("image",images[i])
            context.startActivity(intent)

        }
    }

    override fun getItemCount(): Int {
        return desc.size
    }
}
//////TO DO : minus points
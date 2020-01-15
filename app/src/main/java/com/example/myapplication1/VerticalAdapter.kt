package com.example.myapplication1

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class VerticalAdapter:RecyclerView.Adapter<VerticalAdapter.ViewHolder> () {

    private val images =
        intArrayOf(R.drawable.plantingtrees, R.drawable.no_plastic, R.drawable.no_straw)
    private val desc = arrayOf(
        "Plant a Tree and\nGet Free Oxygen",
        "Skip the straw,\nSave a sea turtles life",
        "Dont laminate the earth")

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
            else if(i==1){
                val content =viewHolder.itemView.context
                val intent=Intent(content,ActivityContent::class.java)
                intent.putExtra("EXTRA_MSG",i)
                content.startActivity(intent)
            }
            else if(i==2){
                val content =viewHolder.itemView.context
                val intent=Intent(content,ActivityContent::class.java)
                intent.putExtra("EXTRA_MSG",i)
                content.startActivity(intent)


            }
        }
    }

    override fun getItemCount(): Int {
        return desc.size
    }
    companion object{
        const val EXTRA_MSG=".com.example.myapplication1.EXTRA_MSG"
    }
}

package com.example.myapplication1

import android.app.AlertDialog
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.core.content.ContextCompat.startActivity
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.*
import kotlinx.android.synthetic.main.navigationstyle.*


class TreeAdapter:RecyclerView.Adapter<TreeAdapter.ViewHolder>(){
    private lateinit var database: DatabaseReference


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
        val context = viewHolder.itemView.context

        viewHolder.itemPoints.setOnClickListener {
                        database = FirebaseDatabase.getInstance().reference
            val uid= FirebaseAuth.getInstance().uid
            val ref= FirebaseDatabase.getInstance().getReference("users")
            val context = viewHolder.itemView.context

            ref.addListenerForSingleValueEvent(object : ValueEventListener {
                override fun onDataChange(dataSnapshot: DataSnapshot){

                    var point = Integer.valueOf(dataSnapshot.child("$uid").child("total_point_left").getValue().toString())

                    if(point >= points[i]) {

                        val builder = AlertDialog.Builder(context)
                        builder.setTitle("Transaction Confirmation")
                        builder.setMessage("Are you sure to plant the tree?")

                        builder.setPositiveButton("YES") { dialog, which ->

                            val context = viewHolder.itemView.context
                            val intent =
                                Intent(context, DetailsTreePlanted::class.java)/// location activity
                            intent.putExtra("tree", desc[i])
                            intent.putExtra("points", points[i])
                            intent.putExtra("location", locations[i])
                            intent.putExtra("image", images[i])
                            val pointLeft = point - points[i]
                            database.child("users").child("$uid").child("total_point_left")
                                .setValue(pointLeft)
                            context.startActivity(intent)

                        }
                        builder.setNegativeButton("No") { dialog, which ->}

                            builder.setNeutralButton("Cancel") { _, _ -> }

                            val dialog: AlertDialog = builder.create()
                            dialog.show()


                        }
                    
                    else{
                        Toast.makeText(context, "Insufficient points!", Toast.LENGTH_LONG).show()
                    }

                }
                override  fun onCancelled(error: DatabaseError){

                }
            })


        }
    }

    override fun getItemCount(): Int {
        return desc.size
    }
}
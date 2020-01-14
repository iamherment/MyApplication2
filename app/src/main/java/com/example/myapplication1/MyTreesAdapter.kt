package com.example.myapplication1

import android.content.Intent
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_tree_main.*

class MyTreesAdapter: RecyclerView.Adapter<MyTreesAdapter.ViewHolder> () {

    private val treeId : ArrayList<String> = ArrayList()
    private val treeName : ArrayList<String> = ArrayList()
    private val treeLocation : ArrayList<String> = ArrayList()


    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var txttreeId: TextView =itemView.findViewById(R.id.textViewTreeId)
        var txttreeName: TextView =itemView.findViewById(R.id.textViewTreeName)
        var txttreeLocation: TextView = itemView.findViewById(R.id.textViewLocation)

    }

    override fun onCreateViewHolder(viewGroup: ViewGroup, i: Int): ViewHolder {
        val v = LayoutInflater.from(viewGroup.context)
            .inflate(R.layout.my_tree_layout, viewGroup, false)

        val uid= FirebaseAuth.getInstance().uid
        val ref= FirebaseDatabase.getInstance().getReference("tree_user")


        ref.addListenerForSingleValueEvent(object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                for (ds in dataSnapshot.children) {
                    treeId.add(ds.child("$uid").child("tree_id").getValue().toString())
                    treeName.add(ds.child("$uid").child("tree_name").getValue().toString())
                    treeLocation.add(ds.child("$uid").child("location").getValue().toString())
                }
            }
            override  fun onCancelled(error: DatabaseError){

            }
        })




        return ViewHolder(v)

    }
    override fun onBindViewHolder(viewHolder: ViewHolder, i: Int) {
        viewHolder.txttreeId.text = treeId[i]
        viewHolder.txttreeName.text = treeName[i]
        viewHolder.txttreeLocation.text = treeLocation[i]
    }

    override fun getItemCount(): Int {
        return treeId.size
    }
}

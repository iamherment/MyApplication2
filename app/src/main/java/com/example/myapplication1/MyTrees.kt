package com.example.myapplication1

import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_my_trees.*

class MyTrees : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_my_trees)


//        textViewId.text="i"
//        textViewType.text="mat 7"
//        textViewTreeLoc.text="location"


        val uid= FirebaseAuth.getInstance().uid
        val ref= FirebaseDatabase.getInstance().getReference("tree_user")


        ref.addListenerForSingleValueEvent(object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot){

                textViewId.text=dataSnapshot.child("$uid").child("tree_id").getValue().toString()
                textViewType.text=dataSnapshot.child("$uid").child("tree_name").getValue().toString()
                textViewTreeLoc.text=dataSnapshot.child("$uid").child("location").getValue().toString()
                var url:String =dataSnapshot.child("uid").child("img").getValue().toString()

                if(!url.isEmpty()){

                    Picasso.get()
                        .load(url).into(imageView2)
                }

            }
            override  fun onCancelled(error: DatabaseError){

            }
        })
    }
}

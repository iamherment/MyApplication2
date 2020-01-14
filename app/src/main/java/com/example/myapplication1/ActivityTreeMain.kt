package com.example.myapplication1

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import kotlinx.android.synthetic.main.activity_tree_main.*

class ActivityTreeMain : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_tree_main)
        val uid= FirebaseAuth.getInstance().uid
        val ref= FirebaseDatabase.getInstance().getReference("users")


        ref.addListenerForSingleValueEvent(object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot){
                Log.e("try,","dislya")
                textViewBalance.text=dataSnapshot.child("$uid").child("total_point_left").getValue().toString()


                Log.e("try,","dislya")
            }
            override  fun onCancelled(error: DatabaseError){

            }
        })

        buttonPlant.setOnClickListener {
            val intent = Intent(this, Tree::class.java)
            startActivity(intent)
        }

        buttonMyTrees.setOnClickListener {
            val intent = Intent(this, MyTrees::class.java)
            startActivity(intent)
        }


    }

    override fun onBackPressed() {
        super.onBackPressed()
        
    }
}
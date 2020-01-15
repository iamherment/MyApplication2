package com.example.myapplication1

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import kotlinx.android.synthetic.main.activity_content.*
import kotlinx.android.synthetic.main.activity_details_tree_planted.*

class ActivityContent : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_content)

        var action=intent.getIntExtra("EXTRA_MSG",0)
        val act=FirebaseDatabase.getInstance().getReference("activities")
        act.addListenerForSingleValueEvent(object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot){

               if(action==1){
                textViewTitle.setText(dataSnapshot.child("Skip the straw! Save a sea turtle!").child("act_name").getValue().toString())
                textViewCon.setText(dataSnapshot.child("Skip the straw! Save a sea turtle!").child("act_describe").getValue().toString())
                textViewDate.setText(dataSnapshot.child("Skip the straw! Save a sea turtle!").child("act_uploadDate").getValue().toString())}

                else if(action==2){

                   textViewTitle.setText(dataSnapshot.child("Don't laminate the earth!").child("act_name").getValue().toString())
                   textViewCon.setText(dataSnapshot.child("Don't laminate the earth!").child("act_describe").getValue().toString())
                   textViewDate.setText(dataSnapshot.child("Don't laminate the earth!").child("act_uploadDate").getValue().toString())
               }




            }
            override  fun onCancelled(error: DatabaseError){

            }
        })

    }
}

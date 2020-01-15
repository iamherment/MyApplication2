package com.example.myapplication1

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import kotlinx.android.synthetic.main.activity_top_up.*

class TopUp : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_top_up)

        button_top_up.setOnClickListener {
            val uid = FirebaseAuth.getInstance().uid
            val ref = FirebaseDatabase.getInstance().getReference("users")

            ref.addListenerForSingleValueEvent(object : ValueEventListener {
                override fun onDataChange(dataSnapshot: DataSnapshot) {
                    var amount =
                        Integer.valueOf(dataSnapshot.child(uid.toString()).child("user_wallet_balance").getValue().toString())
                    var top_amount = editTextValue.text.toString().toInt() + amount

                    ref.child(uid.toString()).child("user_wallet_balance").setValue(top_amount)



                }

                override fun onCancelled(error: DatabaseError) {

                }


            })
            finish()
            return@setOnClickListener

        }
        }
    }


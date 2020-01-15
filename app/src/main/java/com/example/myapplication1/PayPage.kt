package com.example.myapplication1

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.myapplication1.data.model.Transaction
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import kotlinx.android.synthetic.main.activity_pay_page.*
import kotlinx.android.synthetic.main.activity_pay_page.editTextValue
import kotlinx.android.synthetic.main.activity_top_up.*

class PayPage : AppCompatActivity() {
    private val db = FirebaseDatabase.getInstance()
    private val uid= FirebaseAuth.getInstance().uid



    private val transactions = db.getReference().child("users").child(uid.toString())

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pay_page)

        buttonPay.setOnClickListener {
            pay()
        }
    }

    private fun pay() {
        val uid = FirebaseAuth.getInstance().uid
        val ref = FirebaseDatabase.getInstance().getReference("users")
        val recipientID = editTextRecipientId.text.toString()
        val amount = editTextValue.text.toString().toFloat()
        val transaction = Transaction (recipientID =  recipientID ,  amount = amount, createdAt = System.currentTimeMillis())
        transactions.child("transaction").push().setValue(transaction)

        Toast.makeText(this, "Successful transaction!", Toast.LENGTH_SHORT).show()



        ref.addListenerForSingleValueEvent(object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                var amount =
                    Integer.valueOf(dataSnapshot.child(uid.toString()).child("user_wallet_balance").getValue().toString())
                var minus_amount = amount-editTextValue.text.toString().toInt()

                ref.child(uid.toString()).child("user_wallet_balance").setValue(minus_amount)



            }

            override fun onCancelled(error: DatabaseError) {

            }


        })

        finish()

    }





}

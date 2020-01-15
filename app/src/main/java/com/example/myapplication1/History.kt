package com.example.myapplication1

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication1.data.model.Transaction
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import kotlinx.android.synthetic.main.activity_history.*

class History : AppCompatActivity() {
    private val db = FirebaseDatabase.getInstance()
    private val transactions = db.getReference("transaction")
    lateinit var listtransaction: ArrayList<Transaction>
    lateinit var recyclerView: RecyclerView
    lateinit var adapter: HistoryAdapter


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_history)

        listtransaction = ArrayList<Transaction>()
        recyclerView = findViewById<RecyclerView>(R.id.recyclerviewHistory)
        recyclerView.setHasFixedSize(true)
        recyclerView.layoutManager = LinearLayoutManager(this)

        transactions.addValueEventListener(object : ValueEventListener {
            override fun onCancelled(p0: DatabaseError) {
                TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
            }

            override fun onDataChange(dataSnapshot1: DataSnapshot) {

                listtransaction.clear()

                for (dataSnapshot in dataSnapshot1.getChildren()) {
                    val transaction: Transaction =
                        dataSnapshot.getValue(Transaction::class.java)!!
                    listtransaction.add(transaction)
                }

                adapter = HistoryAdapter(this@History, listtransaction)

                recyclerView.adapter = adapter
            }
        })
    }
}


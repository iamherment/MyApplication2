package com.example.myapplication1


import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication1.data.model.Transaction
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.*
import kotlinx.android.synthetic.main.activity_history.*

/**
 * A simple [Fragment] subclass.
 */
class HistoryFragment : Fragment() {

    /**
     * val userid: String = "EWNeiVHkI2dAtPDUXlSVG28RZs32"
     * private val transactions = db.getReference().child("users").child(userid).child("transaction")
     */

    private val db = FirebaseDatabase.getInstance()
    private val uid=FirebaseAuth.getInstance().uid
    private val transactions = db.getReference().child("users").child(uid.toString()).child("transaction")
    lateinit var listtransaction: ArrayList<Transaction>
    lateinit var recyclerView: RecyclerView
    lateinit var adapter: HistoryAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val v: View = inflater.inflate(R.layout.activity_history,
            container, false)

        listtransaction = ArrayList<Transaction>()
        recyclerView = v.findViewById<RecyclerView>(R.id.recyclerviewHistory)
        recyclerView.setHasFixedSize(true)
        recyclerView.layoutManager = LinearLayoutManager(this.context)

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

                adapter = HistoryAdapter(this@HistoryFragment.context!!, listtransaction)

                recyclerView.adapter = adapter
            }
        })


        return v
    }
}

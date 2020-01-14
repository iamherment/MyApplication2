package com.example.myapplication1

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication1.data.model.TreeUser
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import kotlinx.android.synthetic.main.activity_tree.*


class MyTrees : AppCompatActivity() {
    private val database = FirebaseDatabase.getInstance()
    val uid= FirebaseAuth.getInstance().uid
    private val tree_user = database.getReference("tree_user").child("$uid")
    lateinit var treeUserList: ArrayList<TreeUser>
    lateinit var recyclerView: RecyclerView
    lateinit var adapter: MyTreesAdapter


    @SuppressLint("WrongConstant")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_my_trees)

        treeUserList = ArrayList<TreeUser>()
        recyclerView = findViewById<RecyclerView>(R.id.recyclerviewTree)
        recyclerView.setHasFixedSize(true)
        recyclerView.layoutManager = LinearLayoutManager(this)


        tree_user.addValueEventListener(object : ValueEventListener {
            override fun onCancelled(p0: DatabaseError) {
                TODO("not implemented")
            }

            override fun onDataChange(dataSnapshot1: DataSnapshot) {

                treeUserList.clear()

                for (dataSnapshot in dataSnapshot1.getChildren()) {
                    val transaction: TreeUser =
                        dataSnapshot.getValue(TreeUser::class.java)!!
                    treeUserList.add(transaction)
                }

                adapter = MyTreesAdapter(this@MyTrees, treeUserList)

                recyclerView.adapter = adapter
            }
        })
















        recyclerviewTree.layoutManager = LinearLayoutManager(this)
        recyclerviewTree.adapter = TreeAdapter()


    }
}
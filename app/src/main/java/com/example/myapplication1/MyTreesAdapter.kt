package com.example.myapplication1

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication1.data.model.TreeUser


class MyTreesAdapter(c: Context, treeUser: ArrayList<TreeUser>) :
    RecyclerView.Adapter<TreeUserViewModel>(){
    var c: Context
    var treeUser: ArrayList<TreeUser>

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TreeUserViewModel{
        val v: View = LayoutInflater.from(c).inflate(R.layout.my_tree_layout, parent, false)
        return TreeUserViewModel(v)
    }

    override fun onBindViewHolder(viewholder: TreeUserViewModel, position: Int) {
        viewholder.textViewTreeId.setText(treeUser[position].tree_id)
        viewholder.textViewTreeName.setText(treeUser[position].tree_name)
        viewholder.textViewLocation.setText(treeUser[position].location)
    }

    override fun getItemCount(): Int {
        return treeUser.size
    }

    init {
        this.c = c
        this.treeUser = treeUser
    }
}
package com.example.myapplication1

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication1.data.model.TreeUser


class TreeUserViewModel (itemView: View) : RecyclerView.ViewHolder(itemView)   {
    var textViewTreeId: TextView
    var textViewTreeName: TextView
    var textViewLocation: TextView

    fun setItem(treeUser: TreeUser) {
        textViewTreeId.text = treeUser.tree_id
        textViewTreeName.text = treeUser.tree_name
        textViewLocation.text = treeUser.location
    }

    init {
        textViewTreeId = itemView.findViewById(R.id.textViewTreeId)
        textViewTreeName = itemView.findViewById(R.id.textViewTreeName)
        textViewLocation = itemView.findViewById(R.id.textViewLocation)
    }

}
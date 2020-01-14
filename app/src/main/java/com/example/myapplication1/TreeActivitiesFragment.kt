package com.example.myapplication1


import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.recyclerview.widget.OrientationHelper
import kotlinx.android.synthetic.main.activity_tree_activities.*
import androidx.recyclerview.widget.LinearLayoutManager

/**
 * A simple [Fragment] subclass.
 */
class TreeActivitiesFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view: View = inflater.inflate(R.layout.activity_tree_activities, container, false)

        return view
    }

    @SuppressLint("WrongConstant")
    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        recyclerviewMy.layoutManager = LinearLayoutManager(this.context, OrientationHelper.HORIZONTAL,false)
        recyclerviewMy.adapter = ViewAdapter()

        recyclerviewOv.layoutManager = LinearLayoutManager(this.context)
        recyclerviewOv.adapter = VerticalAdapter()
    }


}

package com.example.myapplication1

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class HistoryAdapter:RecyclerView.Adapter<HistoryAdapter.ViewHolder>() {

    private val transcTitle  = arrayOf("Cashback","LRTFU","GrabYou")
    private val transcDTime = arrayOf("11 March 13:30","19 April 10:00","20 April 15:03")
    private val transcVal = doubleArrayOf(1.00,5.00,12.00)
    private  val transcPoints =  intArrayOf(10,50,120)


    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var itemTitles : TextView =itemView.findViewById(R.id.textViewtitle)
        var itemDTime: TextView =itemView.findViewById(R.id.textViewDateTime)
        var itemVal : TextView =itemView.findViewById(R.id.textViewTransValue)
        var itemPoints : TextView =itemView.findViewById(R.id.textViewTransPoint)


    }

    override fun onCreateViewHolder(viewGroup: ViewGroup, i: Int): ViewHolder {
        val v = LayoutInflater.from(viewGroup.context)
            .inflate(R.layout.history_display, viewGroup, false)
        return ViewHolder(v)
    }

    override fun onBindViewHolder(viewHolder: ViewHolder, i: Int) {

        viewHolder.itemTitles.text = transcTitle[i]
        viewHolder.itemDTime.text = transcDTime[i]
        viewHolder.itemVal.text = transcVal[i].toString()
        viewHolder.itemPoints.text = transcPoints[i].toString()

    }

    override fun getItemCount(): Int {
        return transcTitle.size
    }
}
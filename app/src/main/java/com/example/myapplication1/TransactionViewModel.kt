package com.example.myapplication1


import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication1.data.model.Transaction
import java.text.SimpleDateFormat

class TransactionViewModel (itemView: View) : RecyclerView.ViewHolder(itemView)   {
    var textViewRecipient: TextView
    var textViewAmount: TextView
    var textViewTime: TextView

    fun setItem(transaction: Transaction) {
        textViewRecipient.text = transaction.recipientID
        textViewAmount.text = transaction.amount.toString()
        textViewTime.text = SimpleDateFormat("yyyy.MM.DD ").format(transaction.createdAt)
    }

    init {
        textViewRecipient = itemView.findViewById(R.id.textViewRecipient)
        textViewAmount = itemView.findViewById(R.id.textViewAmount)
        textViewTime = itemView.findViewById(R.id.textViewTime)
    }

}
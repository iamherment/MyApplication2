package com.example.myapplication1

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication1.data.model.Transaction
import java.text.SimpleDateFormat
import kotlin.collections.ArrayList

class HistoryAdapter(c: Context, transaction: ArrayList<Transaction>) :
    RecyclerView.Adapter<TransactionViewModel>(){
    var c: Context
    var transactions: ArrayList<Transaction>
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TransactionViewModel{
        val v: View = LayoutInflater.from(c).inflate(R.layout.listtransaction, parent, false)
        return TransactionViewModel(v)
    }

    override fun onBindViewHolder(holder: TransactionViewModel, position: Int) {
        holder.textViewRecipient.setText(transactions[position].recipientID)
        holder.textViewAmount.setText(transactions[position].amount.toString())
        holder.textViewTime.setText(SimpleDateFormat("DD/MM/yyy").format(transactions[position].createdAt))
    }

    override fun getItemCount(): Int {
        return transactions.size
    }

    init {
        this.c = c
        this.transactions = transaction
    }
}
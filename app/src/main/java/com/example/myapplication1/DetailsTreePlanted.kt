package com.example.myapplication1

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_details_tree_planted.*


class DetailsTreePlanted : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_details_tree_planted)

        val intent = intent

        textViewId.setText("f9ylshtc")
        textViewTree.setText(intent.getStringExtra("tree"))
        textViewLocation.setText(intent.getStringExtra("location"))
        imageViewTree.setImageResource(intent.getIntExtra("image",0))

        buttonHome.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)/// location activity
            startActivity(intent)
        }
    }
}

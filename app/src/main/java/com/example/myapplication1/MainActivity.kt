package com.example.myapplication1

import android.annotation.SuppressLint
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.ActionBar
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.navigationstyle.*
import com.smarteist.autoimageslider.IndicatorAnimations
import com.smarteist.autoimageslider.IndicatorView.draw.controller.DrawController
import com.smarteist.autoimageslider.SliderAnimations
import com.smarteist.autoimageslider.SliderView
import kotlinx.android.synthetic.main.activity_tree_main.*
import kotlinx.android.synthetic.main.navigationstyle.textViewBalance
import kotlinx.android.synthetic.main.navigationstyle.textViewTitle

class MainActivity : AppCompatActivity() {

    @SuppressLint("ResourceAsColor")
    override fun onCreate(savedInstanceState: Bundle?){
        super.onCreate(savedInstanceState)
//
        setContentView(R.layout.activity_main)

        val uid= FirebaseAuth.getInstance().uid
        val ref= FirebaseDatabase.getInstance().getReference("users")

        ref.addListenerForSingleValueEvent(object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot){

                textViewBalance.text="RM"+dataSnapshot.child("$uid").child("user_wallet_balance").getValue().toString()



            }
            override  fun onCancelled(error: DatabaseError){

            }
        })



        getSupportActionBar()?.setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM)
        getSupportActionBar()?.setDisplayShowCustomEnabled(true);
        getSupportActionBar()?.setCustomView(R.layout.navigationstyle);
        //var view: View = getSupportActionBar()!!.getCustomView()!!

        val fragmentManager = supportFragmentManager
        val fragmentTransaction = fragmentManager.beginTransaction()
        textViewTitle.setText(getResources().getString(R.string.home))
        imageButtonHomeIn.setBackgroundColor(R.color.colorGreen)
        val fragment = Default_mainpageFragment()
        fragmentTransaction.replace(R.id.fragmentMain, fragment)
        fragmentTransaction.commit()


        imageButtonHomeIn.setOnClickListener {
            imageButtonHomeIn.setBackgroundColor(R.color.colorGreen)
            imageButtonHistory.setBackgroundColor(Color.TRANSPARENT)
            imageButtonProfile.setBackgroundColor(Color.TRANSPARENT)
            imageButtonLeaderboard.setBackgroundColor(Color.TRANSPARENT)
            imageButtonActivity.setBackgroundColor(Color.TRANSPARENT)
            textViewTitle.setText(getResources().getString(R.string.home))
            val newFragment = Default_mainpageFragment()
            val transaction = supportFragmentManager.beginTransaction()
            transaction.replace(R.id.fragmentMain, newFragment)
            transaction.commit()

        }
        imageButtonActivity.setOnClickListener {
            imageButtonHomeIn.setBackgroundColor(Color.TRANSPARENT)
            imageButtonHistory.setBackgroundColor(Color.TRANSPARENT)
            imageButtonProfile.setBackgroundColor(Color.TRANSPARENT)
            imageButtonLeaderboard.setBackgroundColor(Color.TRANSPARENT)
            imageButtonActivity.setBackgroundColor(R.color.colorGreen)
            textViewTitle.setText(getResources().getString(R.string.activities))
            val newFragment = TreeActivitiesFragment()
            val transaction = supportFragmentManager.beginTransaction()
            transaction.replace(R.id.fragmentMain, newFragment)
            transaction.addToBackStack(null)
            transaction.commit()
        }
        imageButtonLeaderboard.setOnClickListener {
            imageButtonHomeIn.setBackgroundColor(Color.TRANSPARENT)
            imageButtonHistory.setBackgroundColor(Color.TRANSPARENT)
            imageButtonProfile.setBackgroundColor(Color.TRANSPARENT)
            imageButtonLeaderboard.setBackgroundColor(R.color.colorGreen)
            imageButtonActivity.setBackgroundColor(Color.TRANSPARENT)
            textViewTitle.setText(getResources().getString(R.string.leaderBoard))
            val newFragment = LeaderBoardFragment()
            val transaction = supportFragmentManager.beginTransaction()
            transaction.replace(R.id.fragmentMain, newFragment)
            transaction.commit()
        }
        imageButtonProfile.setOnClickListener {
            imageButtonHomeIn.setBackgroundColor(Color.TRANSPARENT)
            imageButtonHistory.setBackgroundColor(Color.TRANSPARENT)
            imageButtonProfile.setBackgroundColor(R.color.colorGreen)
            imageButtonLeaderboard.setBackgroundColor(Color.TRANSPARENT)
            imageButtonActivity.setBackgroundColor(Color.TRANSPARENT)
            textViewTitle.setText(getResources().getString(R.string.profile))
            val newFragment = ProfileFragment()
            val transaction = supportFragmentManager.beginTransaction()
            transaction.replace(R.id.fragmentMain, newFragment)
            transaction.commit()
        }
        imageButtonHistory.setOnClickListener {
            imageButtonHomeIn.setBackgroundColor(Color.TRANSPARENT)
            imageButtonHistory.setBackgroundColor(R.color.colorGreen)
            imageButtonProfile.setBackgroundColor(Color.TRANSPARENT)
            imageButtonLeaderboard.setBackgroundColor(Color.TRANSPARENT)
            imageButtonActivity.setBackgroundColor(Color.TRANSPARENT)
            textViewTitle.setText(getResources().getString(R.string.history))
            val newFragment = HistoryFragment()
            val transaction = supportFragmentManager.beginTransaction()
            transaction.replace(R.id.fragmentMain, newFragment)
            transaction.commit()
        }

    }
    }



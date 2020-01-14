package com.example.myapplication1


import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import kotlinx.android.synthetic.main.activity_profile.*

/**
 * A simple [Fragment] subclass.
 */
class ProfileFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?

    ): View? {
        val view: View = inflater.inflate(R.layout.activity_profile, container, false)
        var tv:TextView=view.findViewById(R.id.textViewUsername) as TextView
        var tvb:TextView=view.findViewById(R.id.textViewWBalance) as TextView
        var txtvb:TextView=view.findViewById(R.id.textViewbalance) as TextView
        var btn:Button=view.findViewById(R.id.buttonLogOut) as Button

        val uid= FirebaseAuth.getInstance().uid
        val ref= FirebaseDatabase.getInstance().getReference("users")


        ref.addListenerForSingleValueEvent(object : ValueEventListener{
            override fun onDataChange(dataSnapshot: DataSnapshot){
                Log.e("try,","dislya")
                tv.text=dataSnapshot.child("$uid").child("user_name").getValue().toString()
                tvb.text=dataSnapshot.child("$uid").child("total_point_left").getValue().toString()
                txtvb.text="RM"+dataSnapshot.child("$uid").child("user_wallet_balance").getValue().toString()

                Log.e("try,","dislya")
            }
            override  fun onCancelled(error:DatabaseError){

            }
        })
        btn.setOnClickListener {  FirebaseAuth.getInstance().signOut()
            val intent = Intent(this.activity, S2login::class.java)
            startActivity(intent)}


        return view


    }


}

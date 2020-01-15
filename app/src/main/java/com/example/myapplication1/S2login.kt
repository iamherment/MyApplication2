package com.example.myapplication1

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.util.Patterns
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.database.FirebaseDatabase
import kotlinx.android.synthetic.main.activity_s2e_wallet_login.*

class S2login:AppCompatActivity(){

    private lateinit var auth:FirebaseAuth




    override fun onCreate(savedInstanceState: Bundle? ) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_s2e_wallet_login)
        auth=FirebaseAuth.getInstance()



        login.setOnClickListener { login() }
        signup.setOnClickListener { val intent=Intent(this,Register::class.java)
        startActivity(intent)}

    }

    private fun login(){

        if(username.text.toString().isEmpty()){
            username.error="Please enter email "
            username.requestFocus()
            return
        }
        if(!Patterns.EMAIL_ADDRESS.matcher(username.text.toString()).matches()){
            username.error="Please enter correct format of email"
            username.requestFocus()
            return

        }
        if(password.text.toString().isEmpty()){
            password.error="Please enter password"
            password.requestFocus()
            return

        }
        auth.signInWithEmailAndPassword(username.text.toString(), password.text.toString())
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    Log.e("tyr","jajajaj")
                    val user = auth.currentUser
                    updateUI(user)
                } else {

                    Toast.makeText(baseContext, "Authentication failed.",
                        Toast.LENGTH_SHORT).show()
                    updateUI(null)
                }

                // ...
            }

    }
    public override fun onStart() {
        super.onStart()
        // Check if user is signed in (non-null) and update UI accordingly.
        val currentUser = auth.currentUser
        updateUI(currentUser)
    }

    private fun  updateUI(currentUser: FirebaseUser?){

        if(currentUser!=null){
//            saveUSerToFireDatabase()
            val intent = Intent(this,MainActivity::class.java)
            startActivity(intent)


        }else{
//            Toast.makeText(
//                baseContext,"Login failed.",
//                Toast.LENGTH_SHORT
//            ).show()

        }

    }



}
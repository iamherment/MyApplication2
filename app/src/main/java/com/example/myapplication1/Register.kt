package com.example.myapplication1

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.util.Patterns
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.database.FirebaseDatabase
import kotlinx.android.synthetic.main.activity_register.*
import kotlinx.android.synthetic.main.activity_s2e_wallet_login.*

class Register : AppCompatActivity() {
    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)
        auth=FirebaseAuth.getInstance()
        buttonConfirm.setOnClickListener { register() }
    }
    private fun register(){
        if(editTextUsername.text.toString().isEmpty()){
            editTextUsername.error="Please enter email "
            editTextUsername.requestFocus()
            return
        }
        if(editText2.text.toString().isEmpty()){
            editText2.error="Please enter email"
            editText2.requestFocus()
            return

        }
        if(!Patterns.EMAIL_ADDRESS.matcher(editText2.text.toString()).matches()){
            editText2.error="Please enter correct format of email"
            editText2.requestFocus()
            return

        }


        if(editTextPass.text.toString().isEmpty()){
            editTextPass.error="Please enter password"
            editTextPass.requestFocus()
            return

        }
        if(editText3.text.toString().isEmpty()){
            editText3.error="Please enter phone number"
            editText3.requestFocus()
            return
        }
        if(editTextPass.text.toString()!=editTextConPass.text.toString()){
            editTextConPass.error="Password does not match"
            editTextConPass.requestFocus()
            return

        }
        auth.createUserWithEmailAndPassword(editText2.text.toString(),editTextPass.text.toString())
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    // Sign in success, update UI with the signed-in user's information

                    val user = auth.currentUser
                    updateUI(user)
                } else {
                    // If sign in fails, display a message to the user.

                    Toast.makeText(baseContext, "Authentication failed.",
                        Toast.LENGTH_SHORT).show()
                    updateUI(null)
                }

                // ...
            }



    }
    private fun  updateUI(currentUser: FirebaseUser?) {

        if (currentUser != null) {
            saveUSerToFireDatabase()
            val intent = Intent(this, S2login::class.java)
            startActivity(intent)


        } else {
//            Toast.makeText(
//                baseContext,"Login failed.",
//                Toast.LENGTH_SHORT
//            ).show()

        }
    }
    private fun saveUSerToFireDatabase(){
        val uid=FirebaseAuth.getInstance().uid
        val ref= FirebaseDatabase.getInstance().getReference("/users/$uid")

        val user=User(uid,editTextUsername.text.toString(),editText2.text.toString(),editText3.text.toString(),0.00,0,0)
        ref.setValue(user)
            .addOnSuccessListener {
                Log.d("save","sucess")
            }
    }
}

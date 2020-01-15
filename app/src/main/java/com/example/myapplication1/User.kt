package com.example.myapplication1


import android.media.Image
import java.time.LocalDate
import java.time.LocalDateTime
import java.util.*

data class User(
    var user_id:String?,
    var user_name:String,
    var user_email:String,
    var user_phNum:String,
    var user_wallet_balance: Double,
    var total_point_collected: Int,
    var total_point_left: Int

)

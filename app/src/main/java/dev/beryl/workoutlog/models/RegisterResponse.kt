package dev.beryl.workoutlog.model

import android.os.Message

data class RegisterResponse(
    var message: String,
    var user: String,
    var firstName: String,
    var lastName:String,
    var phoneNumber:String
)

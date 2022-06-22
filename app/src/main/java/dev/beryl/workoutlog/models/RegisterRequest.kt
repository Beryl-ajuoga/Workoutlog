package dev.beryl.workoutlog.model

import com.google.gson.annotations.SerializedName


data class RegisterRequest(
    @SerializedName("first_Name") var firstName : String,
    @SerializedName("last_Name") var lastName : String,
//    @SerializedName("email")var email: String,
    var confirm:String,
    var email: String,

    )



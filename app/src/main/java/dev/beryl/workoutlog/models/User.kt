package dev.beryl.workoutlog.model

import com.google.gson.annotations.SerializedName

data class User(
    @SerializedName("first_Name") var firstName : String,
    @SerializedName("last_Name") var lastName : String,
    var confirm:String,
    var email: String,
    @SerializedName("user_Id") var userid:String
)
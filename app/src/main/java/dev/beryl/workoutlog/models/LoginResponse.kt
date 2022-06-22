package dev.beryl.workoutlog.models

import com.google.gson.annotations.SerializedName

data class LoginResponse(
    var message:String,
    @SerializedName("access_token")var access_token: String,
    @SerializedName("userId")var userId:String,
    @SerializedName("profile_Id")var profile_Id:String
)

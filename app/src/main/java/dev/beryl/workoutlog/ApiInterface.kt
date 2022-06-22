package dev.beryl.workoutlog
import dev.beryl.workoutlog.model.RegisterRequest
import dev.beryl.workoutlog.model.RegisterResponse
import dev.beryl.workoutlog.models.LoginResponse
import dev.beryl.workoutlog.models.loginRequest
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST

interface ApiInterface {

    @POST("/register")
    fun registerUser(@Body registerRequest: RegisterRequest): Call<RegisterResponse>
    @POST("/login")
    fun  loginUser(@Body loginRequest: loginRequest):Call<LoginResponse>

}
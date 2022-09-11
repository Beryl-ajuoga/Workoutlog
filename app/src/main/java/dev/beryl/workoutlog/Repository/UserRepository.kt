package dev.beryl.workoutlog.Repository

import dev.beryl.workoutlog.ApiClient
import dev.beryl.workoutlog.api.ApiInterface
import dev.beryl.workoutlog.model.RegisterRequest
import dev.beryl.workoutlog.model.RegisterResponse
import dev.beryl.workoutlog.models.LoginResponse
import dev.beryl.workoutlog.models.loginRequest
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.Call
import retrofit2.Response

class UserRepository {
    val apiClient = ApiClient.buildApiClient(ApiInterface::class.java)
    suspend fun loginUser(loginRequest: loginRequest): Response<LoginResponse>
        =withContext(Dispatchers.IO) {

            val response = apiClient.loginUser(loginRequest)
            return@withContext response
        }
    suspend fun registerUser(registerRequest: RegisterRequest): Response<RegisterResponse>
            =withContext(Dispatchers.IO) {

        val response = apiClient.registerUser(registerRequest)
        return@withContext response
    }


    }




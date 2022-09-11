package dev.beryl.workoutlog.viewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dev.beryl.workoutlog.Repository.UserRepository
import dev.beryl.workoutlog.model.RegisterRequest
import dev.beryl.workoutlog.model.RegisterResponse
import dev.beryl.workoutlog.models.LoginResponse
import dev.beryl.workoutlog.models.loginRequest
import kotlinx.coroutines.launch

class userViewmodel:ViewModel(){
    val userRepository=UserRepository()
    val  loginLiveData =MutableLiveData<LoginResponse>()
    val loginError =MutableLiveData<String>()
    val registerLiveData =MutableLiveData<RegisterResponse>()
    val registerError = MutableLiveData<String>()


     fun login(loginRequest: loginRequest){
        viewModelScope.launch {
            val response=userRepository.loginUser(loginRequest)
            if (response.isSuccessful){
                loginLiveData.postValue(response.body())
            }
            else{
                loginError.postValue(response.errorBody()?.string())
            }
        }
    }


    fun register(registerRequest: RegisterRequest){
        viewModelScope.launch {
            val response=userRepository.registerUser(registerRequest)
            if (response.isSuccessful){
                registerLiveData.postValue(response.body())
            }
            else{
                registerError.postValue(response.errorBody()?.string())
            }
        }
    }

}

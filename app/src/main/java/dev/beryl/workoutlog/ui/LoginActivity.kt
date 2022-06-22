package dev.beryl.workoutlog.ui


import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Patterns
import android.widget.Toast
import dev.beryl.workoutlog.ApiClient
import dev.beryl.workoutlog.ApiInterface
import dev.beryl.workoutlog.databinding.ActivityLoginBinding

import dev.beryl.workoutlog.models.LoginResponse
import dev.beryl.workoutlog.models.loginRequest
import retrofit2.Call
import retrofit2.Response


class LoginActivity : AppCompatActivity() {
    lateinit var binding:ActivityLoginBinding

    lateinit var sharedPrefs:SharedPreferences


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
       binding=ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)
        sharedPrefs=getSharedPreferences("WORKOUT_PREFS",MODE_PRIVATE)


        binding.btnlogin.setOnClickListener{validateLogin()}
        binding.btnlogin.setOnClickListener{
            val intent=Intent(this, HomeActivity::class.java)
            startActivity(intent)
        }
        binding.tvSignup.setOnClickListener {
            val intent=Intent(this, signupActivity::class.java)
            startActivity(intent)
        }
//            val intent =Intent(this ,signupActivity::class.java)
//            startActivity(intent)
//
//        }
//        binding.btnlogin.setOnClickListener{
//
//            val intent=Intent(this ,HomeActivity::class.java)
//            startActivity(intent)
//            validateLogin()
//        }

    }
    fun validateLogin(){
        var email = binding.etemail.text.toString()
        var password = binding.etpassword.text.toString()
        var error = false


        if (email.isBlank()){
            error=true
            binding.tilemail.error = "Email is required"
        }

        if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()){
            error= true
            binding.tilemail.error = "Email is invalid"
        }
        if (password.isBlank()){
            error=true
            binding.tilpassword.error = "Password is required"
        }
        if(!error){
            val loginRequest=loginRequest(email,password)
            makeLoginRequest(loginRequest)
        }

        }

    fun makeLoginRequest(loginRequest: loginRequest){
        val ApiClient=ApiClient.buildApiClient(ApiInterface::class.java)
        val request=ApiClient.loginUser(loginRequest)
        request.enqueue(object :retrofit2.Callback<LoginResponse> {

            override fun onResponse(call: Call<LoginResponse>, response: Response<LoginResponse>) {
                if (response.isSuccessful){
                    val loginResponse=response.body()
                    Toast.makeText(baseContext,loginResponse?.message,Toast.LENGTH_LONG).show()
                    persistLoginDetails(loginResponse!!)
                    startActivity(Intent(baseContext,HomeActivity::class.java))
                }
                else{
                    val error=response.errorBody()?.string()
                    Toast.makeText(baseContext,error,Toast.LENGTH_LONG).show()
                }

            }

            override fun onFailure(call: Call<LoginResponse>, t: Throwable) {
                Toast.makeText(baseContext,t.message,Toast.LENGTH_LONG).show()
            }

        })
    }

    fun persistLoginDetails(loginResponse:LoginResponse){
        val editor=sharedPrefs.edit()
        editor.putString("USER_ID" ,loginResponse.userId)
        editor.putString("ACCESS_TOKEN" ,loginResponse.access_token)
        editor.putString("PROFILE_ID" ,loginResponse.profile_Id)
        editor.apply()

    }

    }






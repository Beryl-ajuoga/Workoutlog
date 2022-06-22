package dev.beryl.workoutlog.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import dev.beryl.workoutlog.ApiClient
import dev.beryl.workoutlog.ApiInterface
import dev.beryl.workoutlog.databinding.ActivitySignupBinding
import dev.beryl.workoutlog.model.RegisterRequest
import dev.beryl.workoutlog.model.RegisterResponse
import retrofit2.Call
import retrofit2.Callback


class signupActivity : AppCompatActivity() {
    lateinit var binding: ActivitySignupBinding

//   lateinit var tillastname:TextInputLayout


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySignupBinding.inflate(layoutInflater)
        setContentView(binding.root)
        castViews()
        handleClicks()
    }

    fun castViews() {
        //handleClicks()
    }

    fun handleClicks() {
        binding.tvlogin.setOnClickListener {
            val intent = Intent(this, signupActivity::class.java)
            startActivity(intent)
        }
        binding.btnsignup.setOnClickListener {
            validateSignUp()
        }
    }

    fun validateSignUp() {
//            var Password = binding.etpassword.text.toString()
//            var confirmpassword = binding.etconfirm.text.toString()
        var firstName = binding.etfirstname.text.toString()
        var lastName = binding.etlastname.text.toString()
        var confirm = binding.etconfirm.text.toString()
        var email = binding.etemail.text.toString()

        var error=false

        if (firstName.isBlank()){
            error=true
            binding.tilfirstname.error="first name required"
        }
        if (lastName.isBlank()){
            error =true
            binding.tillastname.error="Last name required"
        }
        if (email.isBlank()){
            error=true
            binding.tilemaill.error="Email is required"
        }
        if (confirm.isBlank()) {
            error=true
            binding.etconfirm.error = "confirmation is required"

        }
        if (!error){
            binding.pvregister.visibility=View.VISIBLE
            var registerRequest=RegisterRequest(firstName,lastName,email,confirm)
            makeRegistrationRequest(registerRequest)
        }

    }


    fun makeRegistrationRequest(registerRequest: RegisterRequest) {

        var apiClient = ApiClient.buildApiClient(ApiInterface::class.java)
        var request = apiClient.registerUser(registerRequest)

        request.enqueue(object : retrofit2.Callback<RegisterResponse> {
            override fun onResponse(
                Call: Call<RegisterResponse>,
                response: retrofit2.Response<RegisterResponse>
            ) {binding.pvregister.visibility=View.GONE

                if (response.isSuccessful) {
                    var message = response.body()?.message
                    Toast.makeText(baseContext, message, Toast.LENGTH_LONG).show()
                } else {
                    val error = response.errorBody()?.string()
                    Toast.makeText(baseContext, error, Toast.LENGTH_LONG).show()

                }
            }

            override fun onFailure(call: Call<RegisterResponse>, t: Throwable) {
                Toast.makeText(baseContext, t.message, Toast.LENGTH_LONG).show()

            }



        })
    }
}



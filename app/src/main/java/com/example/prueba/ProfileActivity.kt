package com.example.prueba

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.example.prueba.databinding.ActivityLoginBinding
import com.example.prueba.databinding.ActivityProfileBinding
import com.example.prueba.models.User.DefaultResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class ProfileActivity : AppCompatActivity() {
    private lateinit var binding: ActivityProfileBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityProfileBinding.inflate(layoutInflater)
        setContentView(binding.root)
        this.supportActionBar?.hide()

        obtainUser()
        clicks()

    }

    private fun obtainUser() {
        val retrofit = Retrofit.Builder()
            .baseUrl("http://192.168.192.57:8057/api/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        var servicios: ApiService = retrofit.create(ApiService::class.java)
        servicios.getUser("1").enqueue(object : Callback<DefaultResponse> {
            override fun onResponse(
                call: Call<DefaultResponse>,
                response: Response<DefaultResponse>
            ) {
                Log.e("onResponse: ", response.message())
                Log.e("onResponse: ", response.code().toString())
                Log.e("onResponse: ", response.body().toString())

            }

            override fun onFailure(Call: Call<DefaultResponse>, t: Throwable) {
                Log.e("onFailure: ", t.toString())
            }

        }
        )
    }

    private fun clicks() {
        binding.lLBack.setOnClickListener {
            val i = Intent(applicationContext, MainActivity::class.java)
            startActivity(i)
        }
    }
}
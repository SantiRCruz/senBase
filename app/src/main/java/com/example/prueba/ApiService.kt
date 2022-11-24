package com.example.prueba

import com.example.consumokotlinsimple.models.LogIn
import com.example.consumokotlinsimple.models.ResponseLogIn
import com.example.prueba.models.User.DefaultResponse
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface ApiService {
    @POST("login")
    fun postLogin(@Body logIn: LogIn): Call<ResponseLogIn>

    @GET("viewUser/{id}")
    fun getUser(@Path("id") id : String): Call<DefaultResponse>
}
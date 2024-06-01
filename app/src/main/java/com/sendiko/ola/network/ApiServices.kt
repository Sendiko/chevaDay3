package com.sendiko.ola.network

import com.sendiko.ola.network.request.LoginRequest
import com.sendiko.ola.network.response.LoginResponse
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST

interface ApiServices {

    @POST("user/login")
    fun postLogin(
        @Body request: LoginRequest
    ): Call<LoginResponse>

}
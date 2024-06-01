package com.sendiko.ola.network

import android.content.Context
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

object NetworkConfig {

    private const val BASE_URL = "https://tix4u.sendiko-softwarestudio.web.id/api/"

    private fun provideOkhttpClient(): OkHttpClient {
        return OkHttpClient.Builder()
            .addInterceptor {
                it.proceed(it.request().newBuilder().also {
                    it.addHeader("Accept", "application/json")
                }.build())
            }.also {
                val logging = HttpLoggingInterceptor()
                logging.level = HttpLoggingInterceptor.Level.BODY
                it.addInterceptor(logging)
            }
            .connectTimeout(120, TimeUnit.SECONDS)
            .readTimeout(120, TimeUnit.SECONDS)
            .build()
    }

    fun getInstance(context: Context): ApiServices {
        val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(provideOkhttpClient())
            .build()

        return retrofit.create(ApiServices::class.java)
    }

}
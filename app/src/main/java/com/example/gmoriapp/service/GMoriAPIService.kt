package com.example.gmoriapp.service

import com.example.gmoriapp.BuildConfig
import com.example.gmoriapp.model.GMoriAPIResponse
import okhttp3.OkHttpClient

import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.http.GET
import retrofit2.converter.gson.GsonConverterFactory

interface GMoriAPIService {
    @GET("users")
    suspend fun getUserList(): Response<List<GMoriAPIResponse>>

    companion object {
        private val BASE_URL = BuildConfig.BASE_URL

        fun create(): GMoriAPIService {
            val client = OkHttpClient.Builder()
                .build()

            return Retrofit.Builder()
                .baseUrl(BASE_URL)
                .client(client)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(GMoriAPIService::class.java)
        }
    }
}

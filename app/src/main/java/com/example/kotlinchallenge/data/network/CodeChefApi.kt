package com.example.kotlinchallenge.data.network

import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET

/**
 * Created by Venkhatesh on 02-06-2020.
 */
interface CodeChefApi {

    @GET("ongoing")
    fun onGoing(): Call<ResponseBody>

    @GET("upcoming")
    fun upComing(): Call<ResponseBody>

    companion object{
        operator fun invoke() : CodeChefApi {
            return Retrofit.Builder()
                .baseUrl("http://13.235.33.181/")
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(CodeChefApi::class.java)
        }
    }
}
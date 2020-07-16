package com.example.kotlinchallenge.data.network

import com.example.kotlinchallenge.data.network.responses.profile.ProfileResponse
import okhttp3.OkHttpClient
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path
import com.example.kotlinchallenge.data.network.CodeChefProfileApi as CodeChefProfileApi1

/**
 * Created by Venkhatesh on 06-07-2020.
 */
interface CodeChefProfileApi {

    @GET("api/codechef/{userName}")
    suspend fun profile(@Path("userName") userName : String) : Response<ProfileResponse>

    companion object{

        val okHttpClient = OkHttpClient().newBuilder()
            .connectTimeout(60, java.util.concurrent.TimeUnit.SECONDS)
            .writeTimeout(60,java.util.concurrent.TimeUnit.SECONDS)
            .readTimeout(60,java.util.concurrent.TimeUnit.SECONDS)
            .build()

        operator fun invoke() : CodeChefProfileApi1 {
            return Retrofit.Builder()
                .baseUrl("https://competitive-coding-api.herokuapp.com/")
                .client(okHttpClient)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(CodeChefProfileApi1::class.java)
        }

    }
}
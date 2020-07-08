package com.example.kotlinchallenge.data.network

import com.example.kotlinchallenge.data.network.responses.profile.ProfileResponse
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
        operator fun invoke() : CodeChefProfileApi1 {
            return Retrofit.Builder()
                .baseUrl("https://competitive-coding-api.herokuapp.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(CodeChefProfileApi1::class.java)
        }

    }
}
package com.example.kotlinchallenge.data.network

import com.example.kotlinchallenge.data.network.responses.DataResponse
import com.example.kotlinchallenge.data.network.responses.quotes.QuotesResponse
import kotlinx.coroutines.Deferred
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET

/**
 * Created by Venkhatesh on 22-07-2020.
 */
interface CodeChefQuotesApi {

    @GET("quotes")
    suspend fun programmingQuotes(): Response<List<QuotesResponse>>

    companion object{
        operator fun invoke() : CodeChefQuotesApi {
            return Retrofit.Builder()
                .baseUrl("https://programming-quotes-api.herokuapp.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(CodeChefQuotesApi::class.java)
        }
    }
}
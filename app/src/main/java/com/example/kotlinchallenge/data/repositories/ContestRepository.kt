package com.example.kotlinchallenge.data.repositories

import androidx.lifecycle.MutableLiveData
import com.example.kotlinchallenge.data.network.CodeChefApi
import com.example.kotlinchallenge.data.network.responses.Data
import retrofit2.Call
import retrofit2.Response

/**
 * Created by Venkhatesh on 02-06-2020.
 */
class ContestRepository {
    val TAG:String = "ContestRepository"

   suspend fun fetchOngoingContest() : Response<Data> {
        val ApiResponse = MutableLiveData<String>()
        return CodeChefApi.invoke().onGoing()
    }

    suspend fun fetchUpcomingContest(): Response<Data> {
        val apiResponse = MutableLiveData<String>()
        return CodeChefApi.invoke().upComing()
    }
}
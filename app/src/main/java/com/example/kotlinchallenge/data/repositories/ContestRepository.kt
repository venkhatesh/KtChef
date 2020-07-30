package com.example.kotlinchallenge.data.repositories

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.kotlinchallenge.data.db.AppDatabase
import com.example.kotlinchallenge.data.network.CodeChefApi
import com.example.kotlinchallenge.data.network.CodeChefProfileApi
import com.example.kotlinchallenge.data.network.CodeChefQuotesApi
import com.example.kotlinchallenge.data.network.responses.ArrayDataResponse
import com.example.kotlinchallenge.data.network.responses.DataResponse
import com.example.kotlinchallenge.data.network.responses.profile.ProfileResponse
import com.example.kotlinchallenge.data.network.responses.profile.UserDetailsResponse
import com.example.kotlinchallenge.data.network.responses.quotes.QuotesResponse
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

/**
 * Created by Venkhatesh on 02-06-2020.
 */
class ContestRepository(private val db : AppDatabase){
    val TAG:String = "ContestRepository"

    suspend fun fetchOngoingContest(): List<ArrayDataResponse>?{
        val ongoingResponse = CodeChefApi.invoke().onGoing()
        return ongoingResponse.body()?.Data
    }

    suspend fun fetchUpcomingContest(): List<ArrayDataResponse>? {
        val result = CodeChefApi.invoke().upComing()
        val apiResponse = MutableLiveData<List<ArrayDataResponse>>()
        //apiResponse.value = result.body()?.Data
        return result.body()?.Data
    }

    suspend fun fetchPastContest(): List<ArrayDataResponse>?{
        val pastResponse = CodeChefApi.invoke().past()
        return pastResponse.body()?.Data
    }

    suspend fun fetchProfile(): ProfileResponse? {
        var profileResult = CodeChefProfileApi.invoke().profile("venky_2801")
        Log.d(TAG, "fetchProfile: " + profileResult.body()?.rating)
        return profileResult.body()
    }

    suspend fun fetchQuotes() : List<QuotesResponse>? {
        var quotesResponse = CodeChefQuotesApi.invoke().programmingQuotes().body()
        quotesResponse?.let { db.getContestDao.insertQuotes(it) }
        Log.d(TAG, "fetchQuotes: ${quotesResponse?.size}")
        return quotesResponse
    }

    fun getQuotes() : QuotesResponse{
        return db.getContestDao.getRandomQuote()
    }

//    suspend fun saveUser(user:UserDetailsResponse) = db.getContestDao().insertUser(user)
}
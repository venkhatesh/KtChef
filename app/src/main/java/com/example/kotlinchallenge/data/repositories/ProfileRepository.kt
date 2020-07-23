package com.example.kotlinchallenge.data.repositories

import android.util.Log
import androidx.lifecycle.LiveData
import com.example.kotlinchallenge.data.db.AppDatabase
import com.example.kotlinchallenge.data.network.CodeChefProfileApi
import com.example.kotlinchallenge.data.network.CodeChefQuotesApi
import com.example.kotlinchallenge.data.network.responses.profile.ProfileResponse
import com.example.kotlinchallenge.data.network.responses.profile.UserDetailsResponse
import com.example.kotlinchallenge.data.network.responses.quotes.QuotesResponse

/**
 * Created by Venkhatesh on 14-07-2020.
 */
class ProfileRepository(private val db : AppDatabase){
    val TAG:String = "ProfileRepository"

    suspend fun fetchProfile(userName:String): ProfileResponse? {
        var profileResult = CodeChefProfileApi.invoke().profile(userName)
        Log.d(TAG, "fetchProfile: " + profileResult.body()?.rating)
        return profileResult.body()
    }

    suspend fun saveUser(user: UserDetailsResponse){
        Log.d(TAG, "saveUser: " + user.name)
        Log.d(TAG, "saveUser: " + user.username)
        //db.getContestDao().insertUser(user)
    }

}
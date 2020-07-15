package com.example.kotlinchallenge.data.repositories

import android.util.Log
import com.example.kotlinchallenge.data.db.AppDatabase
import com.example.kotlinchallenge.data.network.CodeChefProfileApi
import com.example.kotlinchallenge.data.network.responses.profile.ProfileResponse
import com.example.kotlinchallenge.data.network.responses.profile.UserDetailsResponse

/**
 * Created by Venkhatesh on 14-07-2020.
 */
class ProfileRepository(private val db : AppDatabase){
    val TAG:String = "ProfileRepository"

    suspend fun fetchProfile(): ProfileResponse? {
        var profileResult = CodeChefProfileApi.invoke().profile("venky_2801")
        Log.d(TAG, "fetchProfile: " + profileResult.body()?.rating)
        return profileResult.body()
    }

    suspend fun saveUser(user: UserDetailsResponse){
        Log.d(TAG, "saveUser: " + user.name)
        Log.d(TAG, "saveUser: " + user.username)
        db.getContestDao().insertUser(user)
    }

}
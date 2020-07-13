package com.example.kotlinchallenge.data.db

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.kotlinchallenge.data.network.responses.ArrayDataResponse
import com.example.kotlinchallenge.data.network.responses.profile.UserDetailsResponse

/**
 * Created by Venkhatesh on 13-06-2020.
 */

@Dao
interface ContestDao{

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertUser(user:UserDetailsResponse)
//
//    @Query("select * from user")
//    fun loadUser() : List<UserDetailsResponse>
//
//    @Query("Select * from contests order by RANDOM() limit 1")
//    fun getRandomContest() : LiveData<ArrayDataResponse>
}
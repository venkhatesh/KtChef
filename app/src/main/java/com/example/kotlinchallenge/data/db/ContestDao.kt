package com.example.kotlinchallenge.data.db

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.kotlinchallenge.data.network.responses.ArrayDataResponse
import com.example.kotlinchallenge.data.network.responses.profile.UserDetailsResponse
import com.example.kotlinchallenge.data.network.responses.quotes.QuotesResponse

/**
 * Created by Venkhatesh on 13-06-2020.
 */

@Dao
interface ContestDao{

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertUser(user:UserDetailsResponse)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertQuotes(quotes : List<QuotesResponse>)

    @Query("select * from quotes order by random() limit 1")
    fun getRandomQuote():QuotesResponse
//
//    @Query("select * from user")
//    fun loadUser() : List<UserDetailsResponse>
//
//    @Query("Select * from contests order by RANDOM() limit 1")
//    fun getRandomContest() : LiveData<ArrayDataResponse>
}
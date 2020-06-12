package com.example.kotlinchallenge.data.db

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.kotlinchallenge.data.network.responses.ArrayDataResponse

/**
 * Created by Venkhatesh on 13-06-2020.
 */

@Dao
interface ContestDao{

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(contest:ArrayDataResponse) : Long

    @Query("Select * from contests order by RANDOM() limit 1")
    fun getRandomContest() : LiveData<ArrayDataResponse>
}
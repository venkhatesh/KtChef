package com.example.kotlinchallenge.data.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.kotlinchallenge.data.network.responses.ArrayDataResponse
import com.example.kotlinchallenge.data.network.responses.profile.UserDetailsResponse
import com.example.kotlinchallenge.data.network.responses.quotes.QuotesResponse
import com.example.kotlinchallenge.ui.contest.Contest

/**
 * Created by Venkhatesh on 13-06-2020.
 */
@Database(
    entities = [UserDetailsResponse::class,QuotesResponse::class ],
    version = 1)
abstract class AppDatabase : RoomDatabase() {

    abstract val getContestDao : ContestDao
    //companion object is to make initialization only once in kotlin
//    companion object{
//        //volatile in kotlin is to access this variable from anywhere
//        @Volatile
//        private var instance: AppDatabase ?= null
//        private val LOCK = Any()
//
//        //Check if Instance is null
//        operator fun invoke(context:Context) = instance ?: synchronized(LOCK){
//            instance?:buildDatabase(context).also {
//                instance = it
//            }
//        }
//
//        private fun buildDatabase(context: Context) =
//            Room.databaseBuilder(
//                context.applicationContext,
//                AppDatabase::class.java,
//                "CodeChefDatabase.db"
//            ).build()
//    }

}

private lateinit var INSTANCE: AppDatabase

fun getDatabase(context: Context): AppDatabase {
    synchronized(AppDatabase::class.java) {
        if (!::INSTANCE.isInitialized) {
            INSTANCE = Room.databaseBuilder(context.applicationContext,
                AppDatabase::class.java,
                "CodeChefDatabase.db").allowMainThreadQueries().build()
        }
    }
    return INSTANCE
}
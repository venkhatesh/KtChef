package com.example.kotlinchallenge

import android.util.Log
import androidx.room.Room
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import com.example.kotlinchallenge.data.db.AppDatabase
import com.example.kotlinchallenge.data.db.ContestDao
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

/**
 * Created by Venkhatesh on 07-08-2020.
 */

@RunWith(AndroidJUnit4::class)
class DatabaseTest {

    val TAG = "DatabaseTest"
    lateinit var appDatabase : AppDatabase
    lateinit var contestDao: ContestDao

    @Before
    fun createDb(){
        var context = InstrumentationRegistry.getInstrumentation().targetContext
        appDatabase = Room.inMemoryDatabaseBuilder(context,AppDatabase::class.java).build()
        contestDao = appDatabase.getContestDao
        Log.d(TAG, "createDb: Created Database")
    }

    @After
    fun closeDb(){
        appDatabase.close()
        Log.d(TAG, "closeDb: Database Closed")
    }

    @Test
    fun randomFun(){

    }

}
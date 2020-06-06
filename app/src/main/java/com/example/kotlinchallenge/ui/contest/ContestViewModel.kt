package com.example.kotlinchallenge.ui.contest

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.kotlinchallenge.data.network.responses.ArrayDataResponse
import com.example.kotlinchallenge.data.network.responses.DataResponse
import com.example.kotlinchallenge.data.repositories.ContestRepository
import com.example.kotlinchallenge.ui.NetworkListener
import com.example.kotlinchallenge.util.Coroutines

class ContestViewModel : ViewModel() {
    val TAG : String = "ViewModel"
    var networkListener:NetworkListener ?= null
    var liveResult = MutableLiveData<List<ArrayDataResponse>>()

    fun callOngoingApi(){
        Log.d(TAG,"Call View Model")
        val onGoingResponse = ContestRepository().fetchOngoingContest("ongoing")
        Log.d(TAG,"MVVM Length " + onGoingResponse.value?.size)
        //networkListener?.onSuccess(onGoingResponse)

//        Coroutines.main {
//            val onGoingResponse = ContestRepository().fetchOngoingContest("ongoing")
//            Log.d(TAG,"MVVM Length " + onGoingResponse.size)
//
//        }
    }

    fun callUpcomingApi(){
        networkListener?.onStarted()
        Coroutines.main {
            Log.d(TAG,"Inside Coroutine")
            val onGoingResponse = ContestRepository().fetchUpcomingContest()
            Log.d(TAG, onGoingResponse?.size.toString())
            Log.d(TAG,"ViewModel :" + onGoingResponse?.get(1)?.Code)
            liveResult.postValue(onGoingResponse)
            Log.d(TAG,"MutableLive Data" + liveResult.value?.size.toString())
            onGoingResponse?.let { networkListener?.onSuccess(it) }
            // networkListener?.onSuccess(onGoingResponse)
        }

    }

    fun getMutableLiveContest(): MutableLiveData<List<ArrayDataResponse>> {
        return liveResult
    }
}

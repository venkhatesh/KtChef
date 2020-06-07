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
    val loading = MutableLiveData<Boolean>()

    fun callOngoingApi(){
        Log.d(TAG,"Call View Model")
        loading.value = true
        Coroutines.main {
            val onGoingResponse = ContestRepository().fetchOngoingContest()
            Log.d(TAG,"MVVM Length " + onGoingResponse?.size)
            onGoingResponse?.drop(1)
            liveResult.postValue(onGoingResponse)
            loading.postValue( false)
        }

        //networkListener?.onSuccess(onGoingResponse)

    }

    fun callUpcomingApi(){
        networkListener?.onStarted()
        loading.value = true

        Coroutines.main {
            val upComingResponse = ContestRepository().fetchUpcomingContest()
            upComingResponse?.drop(1)
            liveResult.postValue(upComingResponse)
            upComingResponse?.let { networkListener?.onSuccess(it) }
            loading.postValue( false)
        }

    }

    fun getMutableLiveContest(): MutableLiveData<List<ArrayDataResponse>> {
        return liveResult
    }
}

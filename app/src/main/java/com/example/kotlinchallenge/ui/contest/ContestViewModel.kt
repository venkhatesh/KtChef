package com.example.kotlinchallenge.ui.contest

import android.util.Log
import androidx.lifecycle.ViewModel
import com.example.kotlinchallenge.data.repositories.ContestRepository
import com.example.kotlinchallenge.ui.NetworkListener
import com.example.kotlinchallenge.util.Coroutines

class ContestViewModel : ViewModel() {
    val TAG : String = "ViewModel"
    var networkListener:NetworkListener ?= null


    fun callOngoingApi(){
        Log.d(TAG,"Call View Model")
        Coroutines.main {
            networkListener?.onStarted()
            val onGoingResponse = ContestRepository().fetchOngoingContest("ongoing")
            networkListener?.onSuccess(onGoingResponse)
        }
    }

    fun callUpcomingApi(){
        Coroutines.main {
            networkListener?.onStarted()
            val onGoingResponse = ContestRepository().fetchOngoingContest("upcoming")
            networkListener?.onSuccess(onGoingResponse)
        }
    }

}

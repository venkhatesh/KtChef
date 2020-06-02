package com.example.kotlinchallenge.ui.contest

import androidx.lifecycle.ViewModel
import com.example.kotlinchallenge.data.repositories.ContestRepository
import com.example.kotlinchallenge.ui.NetworkListener
import com.example.kotlinchallenge.util.Coroutines

class ContestViewModel : ViewModel() {
    var networkListener:NetworkListener ?= null

    fun callOngoinfApi(){
        Coroutines.main {
            networkListener?.onStarted()
            val onGoingResponse = ContestRepository().fetchOngoingContest()
            networkListener?.onSuccess()
        }
    }

    fun callUpcomingApi(){
        Coroutines.main {
            networkListener?.onStarted()
            val onGoingResponse = ContestRepository().fetchUpcomingContest()
            networkListener?.onSuccess()
        }
    }

}

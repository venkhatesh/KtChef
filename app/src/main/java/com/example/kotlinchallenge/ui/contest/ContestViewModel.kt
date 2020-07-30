package com.example.kotlinchallenge.ui.contest

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.kotlinchallenge.data.network.responses.ArrayDataResponse
import com.example.kotlinchallenge.data.network.responses.quotes.QuotesResponse
import com.example.kotlinchallenge.data.repositories.ContestRepository
import com.example.kotlinchallenge.ui.NetworkListener
import com.example.kotlinchallenge.util.Coroutines
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class ContestViewModel(private val repository: ContestRepository) : ViewModel() {
    val TAG : String = "ViewModel"
    var networkListener:NetworkListener ?= null
    var liveResult = MutableLiveData<List<ArrayDataResponse>>()
    val loading = MutableLiveData<Boolean>()


    fun callOngoingApi(){
        Log.d(TAG,"Call View Model")
        loading.value = true
//        TODO("Uncomment Netwowrk Calls")
        Coroutines.main {
            var onGoingResponse = repository.fetchOngoingContest()
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
//        TODO("Uncomment Netwowrk Calls")
        Coroutines.main {
            val upComingResponse = repository.fetchUpcomingContest()
            upComingResponse?.drop(1)
            liveResult.postValue(upComingResponse)
            upComingResponse?.let { networkListener?.onSuccess(it) }
            loading.postValue( false)
        }

    }

    fun callPastApi(){
        networkListener?.onStarted()
        loading.value = true
//        TODO("Uncomment Netwowrk Calls")
        Coroutines.main {
            val pastResponse = repository.fetchPastContest()
            pastResponse?.drop(1)
            liveResult.postValue(pastResponse)
            pastResponse?.let { networkListener?.onSuccess(it) }
            loading.postValue( false)
        }

    }


    //fetch quote from api
    fun getQuotes(){
        viewModelScope.launch {
            withContext(Dispatchers.IO){
                val test = repository.fetchQuotes()
                Log.d(TAG, "getQuotes: TEST ${test?.size}")
                //Log.d(TAG, "getQuotes: ${result?.size}")
            }
        }
    }

    //get random quotes from db
    fun getRandomQuotes() : QuotesResponse {
        return repository.getQuotes()
    }

    fun getMutableLiveContest(): MutableLiveData<List<ArrayDataResponse>> {
        return liveResult
    }
}

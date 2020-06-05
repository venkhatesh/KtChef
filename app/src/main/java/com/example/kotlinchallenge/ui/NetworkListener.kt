package com.example.kotlinchallenge.ui

import com.example.kotlinchallenge.data.network.responses.ArrayDataResponse

/**
 * Created by Venkhatesh on 02-06-2020.
 */
interface NetworkListener {

    fun onStarted()
    fun onSuccess(onGoingResponse: List<ArrayDataResponse>)
    fun onFailure(message:String)
}
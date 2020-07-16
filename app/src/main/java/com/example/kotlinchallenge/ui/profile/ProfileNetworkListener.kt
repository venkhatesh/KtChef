package com.example.kotlinchallenge.ui.profile

import com.example.kotlinchallenge.data.network.responses.profile.ProfileResponse

/**
 * Created by Venkhatesh on 16-07-2020.
 */

interface ProfileNetworkListener{
    fun onStarted()
    fun onSuccess(onGoingResponse: ProfileResponse)
    fun onFailure(message:String)
}
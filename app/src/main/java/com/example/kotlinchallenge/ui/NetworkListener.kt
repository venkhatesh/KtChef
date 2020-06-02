package com.example.kotlinchallenge.ui

/**
 * Created by Venkhatesh on 02-06-2020.
 */
interface NetworkListener {

    fun onStarted()
    fun onSuccess()
    fun onFailure(message:String)
}
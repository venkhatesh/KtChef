package com.example.kotlinchallenge.data.network.responses.profile

/**
 * Created by Venkhatesh on 06-07-2020.
 */
data class UserDetailsResponse(
    var name : String,
    var username : String,
    var country : String,
    var state : String,
    var city : String
)
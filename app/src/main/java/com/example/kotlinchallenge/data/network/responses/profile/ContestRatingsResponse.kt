package com.example.kotlinchallenge.data.network.responses.profile

/**
 * Created by Venkhatesh on 06-07-2020.
 */
data class ContestRatingsResponse(
    var code : String,
    var getyear : String,
    var gemonth : String,
    var getday : String,
    var ratings : String,
    var rank : String,
    var name : String,
    var end_date : String
)
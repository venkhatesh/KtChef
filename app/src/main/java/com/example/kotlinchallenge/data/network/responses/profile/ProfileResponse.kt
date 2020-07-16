package com.example.kotlinchallenge.data.network.responses.profile

/**
 * Created by Venkhatesh on 06-07-2020.
 */
data class ProfileResponse(
    var status : String,
    var rating : String,
    var stars : String,
    var highest_rating : String,
    var global_rank : String,
    var country_rank : String,
    var user_details : UserDetailsResponse,
//    var contests : ContestResponse,
    var contest_ratings : List<ContestRatingsResponse>
//    var fully_solved : FullySolvedResponse,
//    var paritally_solved : ParitallySolvedResponse
)
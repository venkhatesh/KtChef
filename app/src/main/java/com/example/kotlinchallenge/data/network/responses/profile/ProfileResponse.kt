package com.example.kotlinchallenge.data.network.responses.profile

/**
 * Created by Venkhatesh on 06-07-2020.
 */

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

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
//    var contests : ContestRatingsResponse,
    var contest_ratings : List<ContestRatingsResponse>
//    var fully_solved : FullySolvedResponse,
//    var paritally_solved : ParitallySolvedResponse
)


data class ContestRatingsResponse(
    var code : String,
    var getyear : String,
    var getmonth : String,
    var getday : String,
    var rating : String,
    var rank : String,
    var name : String,
    var end_date : String
)

const val CURRENT_USER_ID = 0

@Entity(tableName = "user")
data class UserDetailsResponse(
    @ColumnInfo(name="name") var name : String ?= null,
    @ColumnInfo(name="username") var username : String ?= null,
    @ColumnInfo(name="country") var country : String ?= null,
    @ColumnInfo(name="state") var state : String ?= null,
    @ColumnInfo(name="city") var city : String ?= null
){
    @PrimaryKey(autoGenerate = false)
    var uid: Int = CURRENT_USER_ID
}
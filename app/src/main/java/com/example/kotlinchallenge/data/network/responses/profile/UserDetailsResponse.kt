package com.example.kotlinchallenge.data.network.responses.profile

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * Created by Venkhatesh on 06-07-2020.
 */

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
package com.example.kotlinchallenge.data.network.responses.quotes

import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * Created by Venkhatesh on 22-07-2020.
 */

@Entity
data class QuotesResponse constructor(
    @PrimaryKey
    val _id : String,
    val en : String,
    val author : String
)
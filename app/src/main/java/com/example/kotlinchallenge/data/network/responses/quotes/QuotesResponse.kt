package com.example.kotlinchallenge.data.network.responses.quotes

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * Created by Venkhatesh on 22-07-2020.
 */

@Entity(tableName = "quotes")
data class QuotesResponse constructor(
    @PrimaryKey
    @ColumnInfo(name = "id")
    val _id : String,
    @ColumnInfo(name = "quote")
    val en : String,
    @ColumnInfo(name = "author")
    val author : String
)
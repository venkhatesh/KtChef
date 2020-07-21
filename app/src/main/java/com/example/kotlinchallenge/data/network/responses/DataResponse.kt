package com.example.kotlinchallenge.data.network.responses

import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * Created by Venkhatesh on 02-06-2020.
 */
 data class DataResponse (
      val Data : List<ArrayDataResponse>
    )

@Entity(tableName = "contests")
data class ArrayDataResponse(
    @PrimaryKey(autoGenerate = true)
    val Id:Int,
    val Code:String,
    val Name:String,
    val Start:String,
    val End:String
)
package com.example.kotlinchallenge.data.network.responses.youtube

import java.io.Serializable

/**
 * Created by Venkhatesh on 21-07-2020.
 */
data class YoutubeApiResponse(
    var nextPageToken : String ?= null,
    var prevPageToken : String ?= null,
    var items : List<ApiItems> = emptyList()
) : Serializable

data class ApiItems(
    var id : ID,
    var snippet : Snippet
) : Serializable

data class ID(
    var kind : String,
    var videoId : String
) : Serializable

data class Snippet(
    var title : String,
    var description : String,
    var publishedAt : String
) : Serializable
package com.example.kotlinchallenge.data.network.responses.youtube

/**
 * Created by Venkhatesh on 21-07-2020.
 */
data class YoutubeApiResponse(
    var nextPageToken : String ?= null,
    var prevPageToken : String ?= null,
    var items : List<ApiItems> = emptyList()
)

data class ApiItems(
    var id : ID,
    var snippet : Snippet
)

data class ID(
    var videoId : String
)

data class Snippet(
    var title : String,
    var description : String
)
package com.example.kotlinchallenge.data.network.responses.youtube

/**
 * Created by Venkhatesh on 21-07-2020.
 */
data class YoutubeApiResponse(
    var nextPageToken : String,
    var items : List<ApiItems>
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
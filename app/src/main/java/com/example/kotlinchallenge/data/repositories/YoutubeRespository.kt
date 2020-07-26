package com.example.kotlinchallenge.data.repositories

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.example.kotlinchallenge.data.network.CodeChefYoutubeApi
import com.example.kotlinchallenge.data.network.responses.youtube.ApiItems
import kotlinx.coroutines.flow.Flow

/**
 * Created by Venkhatesh on 21-07-2020.
 */
class YoutubeRespository {
    var TAG = "YoutubeRespository"
//    suspend fun fetchYoutubeVideos() : List<ApiItems>? {
//        val videos = CodeChefYoutubeApi.invoke().youtubeVideos()
//        Log.d(TAG, "fetchYoutubeVideos: ${videos.body()?.items?.size}")
//        Log.d(TAG, "fetchYoutubeVideos: ${videos.body()?.items?.get(0)?.id?.videoId}")
//        return videos?.body()?.items
//    }

    fun getVideos() : Flow<PagingData<ApiItems>>{
        return Pager(
            config = PagingConfig(
                pageSize = 50,
                enablePlaceholders = false
            ),
            pagingSourceFactory = {YoutubePagingSource(CodeChefYoutubeApi)}
        ).flow
    }
}
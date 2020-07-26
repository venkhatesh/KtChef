package com.example.kotlinchallenge.ui.video

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.example.kotlinchallenge.data.repositories.YoutubeRespository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import com.example.kotlinchallenge.data.network.responses.youtube.ApiItems as Api

/**
 * Created by Venkhatesh on 21-07-2020.
 */
class VideoViewModel : ViewModel() {
        val TAG = "VideoViewModel"
//    fun fetchYoutubeApi(): List<ApiItems>? {
//        viewModelScope.launch {
//            withContext(Dispatchers.IO){
//                var videoId = YoutubeRespository().fetchYoutubeVideos()
//                return@withContext videoId
//            }
//        }
//        return null
//    }

    fun fetchVideo() : Flow<PagingData<com.example.kotlinchallenge.data.network.responses.youtube.ApiItems>> {
        val result = YoutubeRespository().getVideos().cachedIn(viewModelScope)
        Log.d(TAG, "fetchVideo: ")
        return result
    }
}
package com.example.kotlinchallenge.ui.video

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.kotlinchallenge.data.network.responses.youtube.ApiItems
import com.example.kotlinchallenge.data.repositories.YoutubeRespository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

/**
 * Created by Venkhatesh on 21-07-2020.
 */
class VideoViewModel : ViewModel() {

    fun fetchYoutubeApi(): List<ApiItems>? {
        viewModelScope.launch {
            withContext(Dispatchers.IO){
                var videoId = YoutubeRespository().fetchYoutubeVideos()
                return@withContext videoId
            }
        }
        return null
    }
}
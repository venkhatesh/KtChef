package com.example.kotlinchallenge.data.repositories

import android.util.Log
import androidx.paging.PagingSource
import com.example.kotlinchallenge.data.network.CodeChefYoutubeApi
import com.example.kotlinchallenge.data.network.responses.youtube.ApiItems
import com.example.kotlinchallenge.ui.video.ChannelId
import retrofit2.HttpException
import java.io.IOException

/**
 * Created by Venkhatesh on 26-07-2020.
 */
class YoutubePagingSource(
    private val api: CodeChefYoutubeApi.Companion
) : PagingSource<String,ApiItems>(){

    val TAG = "YoutubePaginSource"

    override suspend fun load(params: LoadParams<String>): LoadResult<String, ApiItems> {
        Log.d(TAG, "load: params ${params.key}")
        val position = params.key ?: ""
        return try{
            val response = api.invoke().youtubeVideos(position,ChannelId.getChannelId())
            val videos = response.items
            //position = response.nextPageToken
            Log.d(TAG, "load: ${videos.get(0).id}")
            Log.d(TAG, "load: ${videos.size}")
            Log.d(TAG, "load: Token ${response.nextPageToken}")
            Log.d(TAG, "load: previoud Toke ${response.prevPageToken} 1")
            LoadResult.Page(
                data = videos,
                prevKey = if(response.prevPageToken.equals(null)) null else response.prevPageToken,
                nextKey = response.nextPageToken
            )
        }
        catch (exception: IOException) {
            return LoadResult.Error(exception)
        } catch (exception: HttpException) {
            return LoadResult.Error(exception)
        }
    }

    override val keyReuseSupported: Boolean
        get() = true
}
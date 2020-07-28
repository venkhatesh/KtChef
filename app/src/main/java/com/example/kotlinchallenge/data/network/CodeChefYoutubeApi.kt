package com.example.kotlinchallenge.data.network

import com.example.kotlinchallenge.BuildConfig
import com.example.kotlinchallenge.data.network.responses.DataResponse
import com.example.kotlinchallenge.data.network.responses.youtube.YoutubeApiResponse
import okhttp3.OkHttpClient
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

/**
 * Created by Venkhatesh on 21-07-2020.
 */
interface CodeChefYoutubeApi {

    @GET("youtube/v3/search?key=${BuildConfig.YOUTUBE_API_KEY}&channelId=UCmk2YHXZQk_3GsLKBqsZoBQ&maxResults=508&part=snippet&order=date")
    suspend fun youtubeVideos(
        @Query("pageToken") token : String
    ): YoutubeApiResponse

    companion object{
        val okHttpClient = OkHttpClient().newBuilder()
            .connectTimeout(60, java.util.concurrent.TimeUnit.SECONDS)
            .writeTimeout(60,java.util.concurrent.TimeUnit.SECONDS)
            .readTimeout(60,java.util.concurrent.TimeUnit.SECONDS)
            .build()

        operator fun invoke() : CodeChefYoutubeApi {
            return Retrofit.Builder()
                .baseUrl("https://www.googleapis.com/")
                .client(okHttpClient)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(CodeChefYoutubeApi::class.java)
        }
    }
}
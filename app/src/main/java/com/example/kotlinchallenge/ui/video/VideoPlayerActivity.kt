package com.example.kotlinchallenge.ui.video

import android.app.PictureInPictureParams
import android.os.Bundle
import android.util.Rational
import com.example.kotlinchallenge.BuildConfig
import com.example.kotlinchallenge.R
import com.example.kotlinchallenge.data.network.responses.youtube.ApiItems
import com.google.android.youtube.player.YouTubeBaseActivity
import com.google.android.youtube.player.YouTubeInitializationResult
import com.google.android.youtube.player.YouTubePlayer
import com.google.android.youtube.player.YouTubePlayerView
import kotlinx.android.synthetic.main.activity_video_player.*


class VideoPlayerActivity : YouTubeBaseActivity() , YouTubePlayer.OnFullscreenListener {
    lateinit var youtubePlayer : YouTubePlayerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_video_player)
        youtubePlayer = findViewById<YouTubePlayerView>(R.id.youtube_video_player)
        var videoItem : ApiItems = intent.extras?.getSerializable("video") as ApiItems
        youtube_video_player_title_tv.text = videoItem.snippet.title
        youtube_video_player_description_tv.text = videoItem.snippet.description
        youtube_video_player_date_tv.text = videoItem.snippet.publishedAt
        youtubePlayer.initialize(BuildConfig.YOUTUBE_API_KEY,
        object : YouTubePlayer.OnInitializedListener{
            override fun onInitializationSuccess(
                p0: YouTubePlayer.Provider?,
                p1: YouTubePlayer?,
                p2: Boolean
            ) {
                p1?.setOnFullscreenListener {
                    if (it){
                        p1?.setFullscreen(true)
                    }else{
                        p1?.setFullscreen(false)
                    }
                }
                p1?.loadVideo(videoItem.id.videoId)
            }

            override fun onInitializationFailure(
                p0: YouTubePlayer.Provider?,
                p1: YouTubeInitializationResult?
            ) {

            }

        })
    }

    override fun onFullscreen(p0: Boolean) {

    }

}
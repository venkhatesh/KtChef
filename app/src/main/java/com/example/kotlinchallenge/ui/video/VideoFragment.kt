package com.example.kotlinchallenge.ui.video

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.kotlinchallenge.BuildConfig
import com.example.kotlinchallenge.R
import com.google.android.youtube.player.YouTubeInitializationResult
import com.google.android.youtube.player.YouTubePlayer
import com.google.android.youtube.player.YouTubePlayer.OnInitializedListener
import com.google.android.youtube.player.YouTubePlayerFragment


/**
 * Created by Venkhatesh on 19-07-2020.
 */

class VideoFragment  : Fragment() {

    var TAG : String = "VideoFragment"
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_videos,container,false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        var youtubeFragment: YouTubePlayerFragment =
            (activity?.fragmentManager?.findFragmentById(R.id.youtube_player) as YouTubePlayerFragment)
        youtubeFragment.initialize(BuildConfig.YOUTUBE_API_KEY,
            object : OnInitializedListener {
                override fun onInitializationSuccess(
                    provider: YouTubePlayer.Provider,
                    youTubePlayer: YouTubePlayer, b: Boolean
                ) {
                    // do any work here to cue video, play video, etc.
                    youTubePlayer.cueVideo("5xVh-7ywKpE")
                    Log.d(TAG, "onInitializationSuccess: ")
                }

                override fun onInitializationFailure(
                    provider: YouTubePlayer.Provider,
                    youTubeInitializationResult: YouTubeInitializationResult
                ) {
                    Log.d(TAG, "onInitializationFailure: ")
                }
            })
    }
}
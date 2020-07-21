package com.example.kotlinchallenge.ui.video

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import com.example.kotlinchallenge.BuildConfig
import com.example.kotlinchallenge.R
import com.example.kotlinchallenge.ui.contest.ContestViewModel
import com.google.android.youtube.player.YouTubeInitializationResult
import com.google.android.youtube.player.YouTubePlayer
import com.google.android.youtube.player.YouTubePlayer.OnInitializedListener
import com.google.android.youtube.player.YouTubePlayerFragment


/**
 * Created by Venkhatesh on 19-07-2020.
 */

class VideoFragment  : Fragment() {
    private lateinit var viewModel: VideoViewModel

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
        viewModel = ViewModelProviders.of(this).get(VideoViewModel::class.java)
        var result = viewModel.fetchYoutubeApi()
        Log.d(TAG, "onViewCreated: ${result?.size}")
//        var youtubeFragment: YouTubePlayerFragment =
//            (activity?.fragmentManager?.findFragmentById(R.id.youtube_player) as YouTubePlayerFragment)
//        youtubeFragment.initialize(BuildConfig.YOUTUBE_API_KEY,
//            object : OnInitializedListener {
//                override fun onInitializationSuccess(
//                    provider: YouTubePlayer.Provider,
//                    youTubePlayer: YouTubePlayer, b: Boolean
//                ) {
//                    // do any work here to cue video, play video, etc.
//                    youTubePlayer.cueVideo("tYr10me40-8")
//                    Log.d(TAG, "onInitializationSuccess: ")
//                }
//
//                override fun onInitializationFailure(
//                    provider: YouTubePlayer.Provider,
//                    youTubeInitializationResult: YouTubeInitializationResult
//                ) {
//                    Log.d(TAG, "onInitializationFailure: ")
//                }
//            })

    }
}
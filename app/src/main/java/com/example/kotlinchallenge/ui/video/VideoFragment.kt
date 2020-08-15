package com.example.kotlinchallenge.ui.video

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.kotlinchallenge.BuildConfig
import com.example.kotlinchallenge.R
import com.example.kotlinchallenge.ui.contest.ContestRecyclerAdapter
import com.example.kotlinchallenge.ui.contest.ContestViewModel
import com.google.android.youtube.player.YouTubeInitializationResult
import com.google.android.youtube.player.YouTubePlayer
import com.google.android.youtube.player.YouTubePlayer.OnInitializedListener
import com.google.android.youtube.player.YouTubePlayerFragment
import kotlinx.android.synthetic.main.fragment_ongoing.*
import kotlinx.android.synthetic.main.fragment_videos.*
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch


/**
 * Created by Venkhatesh on 19-07-2020.
 */

class VideoFragment  : Fragment(), View.OnClickListener {
    private lateinit var viewModel: VideoViewModel
    private var adapter: VideosAdapter = VideosAdapter()
    private lateinit var linearLayoutManager: LinearLayoutManager
    val CODECHEF_CHANNEL = "UCmk2YHXZQk_3GsLKBqsZoBQ"
    val LEARN_COMPETITIVE_PROGRAMMING_WITH_CODECHEF = "UCh-5M0r0SBgb5xNCFXG7aXQ"

    var TAG : String = "VideoFragment"
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_videos,container,false)
    }

    @SuppressLint("ResourceType")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        ChannelId.setChannelId(LEARN_COMPETITIVE_PROGRAMMING_WITH_CODECHEF)
        viewModel = ViewModelProviders.of(this).get(VideoViewModel::class.java)
        linearLayoutManager = LinearLayoutManager(activity)
        videos_recycler.layoutManager = linearLayoutManager
        videos_recycler.adapter = adapter
        val divider = DividerItemDecoration(videos_recycler.getContext(), DividerItemDecoration.VERTICAL)
        divider.setDrawable(context?.let { it1 -> ContextCompat.getDrawable(it1, R.layout.custom_divider) }!!)
        videos_recycler.addItemDecoration(divider)
        codechef_fab.setOnClickListener(this)
        learn_competitive_fab.setOnClickListener(this)
        networkCall()
//        lifecycleScope.launch {
//            viewModel.fetchVideo().collect{
//                Log.d(TAG, "onViewCreated: ")
//                adapter.submitData(it)
//            }
//        }

        //var result = viewModel.fetchYoutubeApi()
        //Log.d(TAG, "onViewCreated: ${result?.size}")

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


    fun networkCall(){
        lifecycleScope.launch {
            viewModel.fetchVideo().collect{
                Log.d(TAG, "onViewCreated: ")
                adapter.submitData(it)
            }
        }
    }


    override fun onClick(v: View?) {
        when(v?.id){
            R.id.codechef_fab -> {
                ChannelId.setChannelId(CODECHEF_CHANNEL)
                networkCall()
            }
            R.id.learn_competitive_fab -> {
                ChannelId.setChannelId(LEARN_COMPETITIVE_PROGRAMMING_WITH_CODECHEF)
                networkCall()
            }
        }
    }
}
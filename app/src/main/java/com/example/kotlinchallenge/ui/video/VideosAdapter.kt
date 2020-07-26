package com.example.kotlinchallenge.ui.video

import android.view.View
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.kotlinchallenge.R
import com.example.kotlinchallenge.data.network.responses.youtube.ApiItems
import com.example.kotlinchallenge.ui.contest.ContestRecyclerAdapter
import com.example.kotlinchallenge.util.inflate

/**
 * Created by Venkhatesh on 26-07-2020.
 */
class VideosAdapter :PagingDataAdapter<ApiItems, RecyclerView.ViewHolder>(
    REPO_COMPARATOR
) {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return VideoViewHolder.create(parent)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
            val videoItem = getItem(position)
        if (videoItem != null) {
            (holder as VideoViewHolder).bind(videoItem)
        }
    }

    companion object {
        private val REPO_COMPARATOR = object : DiffUtil.ItemCallback<ApiItems>() {
            override fun areItemsTheSame(oldItem: ApiItems, newItem: ApiItems): Boolean =
                oldItem.id == newItem.id

            override fun areContentsTheSame(oldItem: ApiItems, newItem: ApiItems): Boolean =
                oldItem == newItem
        }
    }



}
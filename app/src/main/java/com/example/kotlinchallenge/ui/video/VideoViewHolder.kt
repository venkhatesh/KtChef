package com.example.kotlinchallenge.ui.video

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.kotlinchallenge.R
import com.example.kotlinchallenge.data.network.responses.youtube.ApiItems
import kotlinx.android.synthetic.main.youtube_video_item.view.*

/**
 * Created by Venkhatesh on 26-07-2020.
 */
class VideoViewHolder(view: View) : RecyclerView.ViewHolder(view) {
    private val id = view.findViewById<TextView>(R.id.youtube_video_id)
    private val name = view.findViewById<TextView>(R.id.youtube_video_title_tv)
    private val description = view.findViewById<TextView>(R.id.youtube_video_description_tv)
    private val date = view.findViewById<TextView>(R.id.youtube_video_date_tv)
    private val videoThumbnal = view.findViewById<ImageView>(R.id.youtube_video_iv)

    fun bind(item:ApiItems){
        name.text = item.snippet.title
        description.text = item.snippet.description
        date.text = item.snippet.publishedAt
        Glide.with(itemView.context).load("https://i.ytimg.com/vi/${item.id.videoId}/default.jpg").into(videoThumbnal)
    }

    companion object {
        fun create(parent: ViewGroup): VideoViewHolder {
            val view = LayoutInflater.from(parent.context)
                .inflate(R.layout.youtube_video_item, parent, false)
            return VideoViewHolder(view)
        }
    }

}
package com.example.kotlinchallenge.ui.video

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.kotlinchallenge.R
import com.example.kotlinchallenge.data.network.responses.youtube.ApiItems
import kotlinx.android.synthetic.main.youtube_video_item.view.*

/**
 * Created by Venkhatesh on 26-07-2020.
 */
class VideoViewHolder(view: View) : RecyclerView.ViewHolder(view) {
    private val id = view.findViewById<TextView>(R.id.youtube_video_id)

    fun bind(item:ApiItems){
        id.text = item.snippet.title
    }

    companion object {
        fun create(parent: ViewGroup): VideoViewHolder {
            val view = LayoutInflater.from(parent.context)
                .inflate(R.layout.youtube_video_item, parent, false)
            return VideoViewHolder(view)
        }
    }

}
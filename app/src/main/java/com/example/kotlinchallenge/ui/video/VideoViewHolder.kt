package  com.example.kotlinchallenge.ui.video

import android.content.Intent
import android.os.Bundle
import android.util.Log
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
class VideoViewHolder(view: View) : RecyclerView.ViewHolder(view),View.OnClickListener {
    val TAG = "VideoViewHolder"
    init {
        view.setOnClickListener(this)
    }
    private val id = view.findViewById<TextView>(R.id.youtube_video_id)
    private val name = view.findViewById<TextView>(R.id.youtube_video_title_tv)
    private val description = view.findViewById<TextView>(R.id.youtube_video_description_tv)
    private val date = view.findViewById<TextView>(R.id.youtube_video_date_tv)
    private val videoThumbnail = view.findViewById<ImageView>(R.id.youtube_video_iv)

    fun bind(item:ApiItems){
        name.text = item.snippet.title
        description.text = item.snippet.description
        date.text = item.snippet.publishedAt
        Glide.with(itemView.context).load("https://i.ytimg.com/vi/${item.id.videoId}/mqdefault.jpg").into(videoThumbnail)
        videoThumbnail.setOnClickListener(View.OnClickListener {
            val intent = Intent(itemView.context,VideoPlayerActivity::class.java)
            var b = Bundle()
            b.putSerializable("video",item)
            intent.putExtras(b)
            itemView.context.startActivity(intent)
        })
    }

    companion object {
        fun create(parent: ViewGroup): VideoViewHolder {
            val view = LayoutInflater.from(parent.context)
                .inflate(R.layout.youtube_video_item, parent, false)
            return VideoViewHolder(view)
        }
    }

    override fun onClick(v: View?) {
        Log.d(TAG, "onClick: TAP TAP")
        Log.d(TAG, "onClick: Which ID ${v?.id}")
        if (v != null) {
            if (v.id == R.id.youtube_video_cv){
                Log.d(TAG, "onClick: TAP TAP youtube thumbnail")
            }
        }
    }

}

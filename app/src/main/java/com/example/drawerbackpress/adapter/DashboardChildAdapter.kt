package com.adapter

import Contents
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.drawerbackpress.R
import com.example.drawerbackpress.utils.IMAGE_URL
import com.makeramen.roundedimageview.RoundedImageView
import kotlinx.android.synthetic.main.item_layout_audio.view.*
import kotlinx.android.synthetic.main.item_layout_video.view.*

class DashboardChildAdapter(var context: Context, var contentItems: ArrayList<Contents>, var type: String , var child: onChildClick) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    companion object {
        var VIDEO = 0
        var AUDIO = 1
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        var viewHolder: RecyclerView.ViewHolder? = null
        val inflater = LayoutInflater.from(parent.context)

        when (viewType) {
            VIDEO -> {
                val viewItem = inflater.inflate(R.layout.item_layout_video, parent, false)
                viewHolder = VideoViewHolder(viewItem)
            }
            AUDIO -> {
                val second = inflater.inflate(R.layout.item_layout_audio, parent, false)
                viewHolder = AudioViewHolder(second)
            }
        }

        return viewHolder!!

    }

    override fun getItemCount(): Int {
        return contentItems.size
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (getItemViewType(position)) {
            VIDEO -> {
                val firstHoler = holder as VideoViewHolder
                var contents = contentItems[position]
                var path = IMAGE_URL + contents.imageUrl
                Glide.with(context).load(path).into(firstHoler.imageViewMovie)
                holder.view.setOnClickListener {
                    child.onVideoChildCallBack(firstHoler.adapterPosition , contents)
                }
            }

            AUDIO ->{
                val firstHoler = holder as AudioViewHolder
                var contents = contentItems.get(position)
                var path = IMAGE_URL + contents.imageUrl
                Glide.with(context).load(path).into(firstHoler.imageViewMovie)
                holder.view.setOnClickListener {
                    child.onAudioChildClickCallBack(firstHoler.adapterPosition , contents)
                }

            }
        }
    }

    override fun getItemViewType(position: Int): Int {
        return when {
            type.contentEquals("video") -> VIDEO
            type.contentEquals("audio") -> AUDIO
            type.contentEquals("album")-> AUDIO
            else -> VIDEO
        }
    }
    inner class AudioViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val imageViewMovie = view.audio_thumb_image as RoundedImageView
        var view = view
    }

    inner class VideoViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val imageViewMovie = view.video_thumb_image as RoundedImageView
        var view = view
    }

    interface onChildClick{
        fun onAudioChildClickCallBack(position: Int , contents: Contents)
        fun onVideoChildCallBack(position: Int , contents: Contents)
    }

}
package com.example.videoplayer

import android.content.Intent
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RelativeLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder

class VideoAdaptor(val videoShowScreen: VideoShowScreen, val videosList: ArrayList<ModelVideo>) :
    RecyclerView.Adapter<VideoAdaptor.ViewData>() {

    class ViewData(itemView: View) : ViewHolder(itemView) {
        var videoName_txt = itemView.findViewById<TextView>(R.id.videoName_txt)
        var video_click = itemView.findViewById<RelativeLayout>(R.id.video_click)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewData {
        var view =
            LayoutInflater.from(videoShowScreen).inflate(R.layout.video_design, parent, false)
        return ViewData(view)
    }

    override fun onBindViewHolder(holder: ViewData, position: Int) {
        holder.videoName_txt.text = videosList[position].title

        holder.video_click.setOnClickListener {
            val intent = Intent(videoShowScreen, ShowVideoScreen::class.java)
            intent.putExtra("uri",videosList[position].data)

            Log.e("TAG", "onBindViewHolder: ${videosList[position].data}")
            videoShowScreen.startActivity(intent)
        }
    }

    override fun getItemCount(): Int {
        return videosList.size
    }

}
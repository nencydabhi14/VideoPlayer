package com.example.videoplayer

import android.net.Uri
import android.os.Bundle
import android.widget.MediaController
import android.widget.Toast
import android.widget.VideoView
import androidx.appcompat.app.AppCompatActivity

class ShowVideoScreen : AppCompatActivity() {

    lateinit var videoView: VideoView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_show_video_screen)

        val uriPath: Uri? = intent.getParcelableExtra("uri")

        Toast.makeText(this, " $uriPath", Toast.LENGTH_SHORT)
            .show()

        videoView = findViewById(R.id.videoView)
        val uri: Uri = Uri.parse(uriPath.toString())
        videoView.setVideoURI(uri)
        val mediaController = MediaController(this)
        mediaController.setAnchorView(videoView)
        mediaController.setMediaPlayer(videoView)
        videoView.setMediaController(mediaController)
        videoView.start()


    }
}
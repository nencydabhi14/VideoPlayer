package com.example.videoplayer

import android.content.ContentUris
import android.os.Bundle
import android.provider.MediaStore
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.videoplayer.databinding.ActivityVideoShowScreenBinding
import java.util.*

class VideoShowScreen : AppCompatActivity() {

    private val videosList = arrayListOf<ModelVideo>()

    lateinit var videoShowScreenBinding: ActivityVideoShowScreenBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        videoShowScreenBinding = ActivityVideoShowScreenBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(videoShowScreenBinding.root)

        val folderName = intent.getStringExtra("fName")
        folderName?.let { loadVideos(it) }


        val videoAdaptor =
            VideoAdaptor(this@VideoShowScreen, videosList)
        val lm = LinearLayoutManager(this)
        videoShowScreenBinding.videoRv.adapter = videoAdaptor
        videoShowScreenBinding.videoRv.layoutManager = lm

    }

    private fun loadVideos(name: String) {
        val projection = arrayOf(
            MediaStore.Video.Media._ID,
            MediaStore.Video.Media.DISPLAY_NAME,
            MediaStore.Video.Media.DURATION
        )
        val sortOrder = MediaStore.Video.Media.DATE_ADDED + " DESC"

        val cursor = contentResolver.query(
            MediaStore.Video.Media.EXTERNAL_CONTENT_URI,
            projection,
            MediaStore.Video.Media.DATA + " like ? ",
            arrayOf("%$name%"),
            null
        )
        if (cursor != null) {
            val idColumn = cursor.getColumnIndexOrThrow(MediaStore.Video.Media._ID)
            val titleColumn = cursor.getColumnIndexOrThrow(MediaStore.Video.Media.DISPLAY_NAME)
            val durationColumn = cursor.getColumnIndexOrThrow(MediaStore.Video.Media.DURATION)
            while (cursor.moveToNext()) {
                val id = cursor.getLong(idColumn)
                val title = cursor.getString(titleColumn)
                val duration = cursor.getInt(durationColumn)
                val data = ContentUris.withAppendedId(
                    MediaStore.Video.Media.EXTERNAL_CONTENT_URI, id
                )
                var duration_formatted: String
                val sec = duration / 1000 % 60
                val min = duration / (1000 * 60) % 60
                val hrs = duration / (1000 * 60 * 60)
                duration_formatted = if (hrs == 0) {
                    min.toString() + ":" + java.lang.String.format(Locale.UK, "%02d", sec)
                } else {
                    "$hrs:" + java.lang.String.format(
                        Locale.UK, "%02d", min
                    ) + ":" + java.lang.String.format(Locale.UK, "%02d", sec)
                }
                videosList.add(ModelVideo(id, data, title, duration_formatted))
            }
        }

    }

}


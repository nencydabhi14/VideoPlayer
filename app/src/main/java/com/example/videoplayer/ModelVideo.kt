package com.example.videoplayer

import android.net.Uri

data class ModelVideo(val id: Long, val data: Uri, val title: String, val durationFormatted: String)
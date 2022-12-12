package com.example.videoplayer

import android.annotation.SuppressLint
import android.content.ContentResolver
import android.content.Intent
import android.database.Cursor
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.videoplayer.databinding.ActivityMainBinding
import java.io.File
import kotlin.collections.ArrayList

class MainActivity : AppCompatActivity() {

    private var foldersList = arrayListOf<ModelFolder>()

    lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityMainBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        getVideoDirectories()
    }

    @SuppressLint("Range")
    fun getVideoDirectories() {
        val directories: ArrayList<String> = ArrayList()
        val contentResolver: ContentResolver = contentResolver
        val queryUri: Uri = MediaStore.Video.Media.EXTERNAL_CONTENT_URI
        val projection = arrayOf(
            MediaStore.Video.Media.DATA
        )
        val includeImages = MediaStore.Video.Media.MIME_TYPE + " LIKE 'video/%' "
        val excludeGif =
            " AND " + MediaStore.Video.Media.MIME_TYPE + " != 'video/gif' " + " AND " + MediaStore.Video.Media.MIME_TYPE + " != 'video/giff' "
        val selection = includeImages + excludeGif
        val cursor: Cursor? = contentResolver.query(queryUri, projection, selection, null, null)
        if (cursor != null && cursor.moveToFirst()) {
            do {
                val photoUri: String = cursor.getString(cursor.getColumnIndex(projection[0]))
                if (!directories.contains(File(photoUri).parent)) {
                    File(photoUri).parent?.let { directories.add(it) }
                }
            } while (cursor.moveToNext())
        }
        val folderVideoAdaptor =
            FolderVideoAdaptor(this@MainActivity, directories) { position, holder ->
                holder.folderName_txt.text = File(directories[position]).name

                holder.folder_click.setOnClickListener {
                    val intent = Intent(this, VideoShowScreen::class.java)
                    intent.putExtra("fName", File(directories[position]).name)
                    startActivity(intent)
                }

            }

        val lm = LinearLayoutManager(this)
        binding.folderRecycler.adapter = folderVideoAdaptor
        binding.folderRecycler.layoutManager = lm

        Log.e("TAG", "getVideoDirectories: $directories")

    }


}
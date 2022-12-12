package com.example.videoplayer

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RelativeLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder

class FolderVideoAdaptor(
    val mainActivity: MainActivity,
    val directories: ArrayList<String>,
    val click: (Int, ViewData) -> Unit
) :
    RecyclerView.Adapter<FolderVideoAdaptor.ViewData>() {

    class ViewData(itemView: View) : ViewHolder(itemView) {
        var folderName_txt = itemView.findViewById<TextView>(R.id.folderName_txt)
        var folder_click = itemView.findViewById<RelativeLayout>(R.id.folder_click)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewData {
        var view = LayoutInflater.from(mainActivity).inflate(R.layout.folder_design, parent, false)
        return ViewData(view)
    }

    override fun onBindViewHolder(holder: ViewData, position: Int) {

        click.invoke(position,holder)
    }

    override fun getItemCount(): Int {
        return directories.size
    }

}
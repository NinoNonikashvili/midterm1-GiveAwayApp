package com.example.midterm1.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.example.midterm1.R
import com.example.midterm1.models.LoadedItem

class ImagesAdapter(private val items:List<LoadedItem>):
    RecyclerView.Adapter<ImagesAdapter.viewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ImagesAdapter.viewHolder {
        return viewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.recycler_item, parent, false))
    }

    override fun onBindViewHolder(holder: ImagesAdapter.viewHolder, position: Int) {
        holder.imageItem.load(items[position].imageUri)

    }

    override fun getItemCount() =items.size
    inner class viewHolder(view: View):RecyclerView.ViewHolder(view){
        val imageItem = view.findViewById<ImageView>(R.id.Recycler_item)


    }
}
package com.example.images.ui.image_details

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.example.images.data.model.Image
import com.example.images.databinding.FragmentImageDetailsBinding
import com.example.images.databinding.ImageListSingleItemBinding
import com.example.images.ui.custom.GenericImage

/**
 * [RecyclerView.Adapter] that can display a [PlaceholderItem].
 * TODO: Replace the implementation with code for your data type.
 */
class ImageRecyclerViewAdapter(
    private val values: List<Image>,
    private val onViewClickListener: ((View) -> Unit)? = null
) : RecyclerView.Adapter<ImageRecyclerViewAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {

        return ViewHolder(
            ImageListSingleItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = values[position]
        holder.genericImage.changeText(item.name)

        onViewClickListener?.let {
            holder.genericImage.onViewClick = it
        }
    }

    override fun getItemCount(): Int = values.size

    inner class ViewHolder(binding: ImageListSingleItemBinding) : RecyclerView.ViewHolder(binding.root) {
        val genericImage: GenericImage = binding.genericImage
    }

}
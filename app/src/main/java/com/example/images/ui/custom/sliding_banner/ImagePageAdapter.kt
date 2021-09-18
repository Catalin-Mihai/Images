package com.example.images.ui.custom.sliding_banner

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.annotation.DrawableRes
import androidx.recyclerview.widget.RecyclerView
import com.example.images.R

class ImagePageAdapter(private val context: Context): RecyclerView.Adapter<ImagePageAdapter.ItemViewHolder>(){

    private lateinit var _items: List<Int>

    var items: List<Int>
        get() = _items
        @SuppressLint("NotifyDataSetChanged")
        set(value) {
            _items = value
            notifyDataSetChanged()
        }

    private val layoutInflater = LayoutInflater.from(context)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder =
        ItemViewHolder(layoutInflater.inflate(R.layout.item_sliding_banner, parent, false))

    override fun getItemCount() = items.size

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) =
        holder.bind(items[position])

    class ItemViewHolder(private val view: View): RecyclerView.ViewHolder(view) {

        fun bind(@DrawableRes imageId: Int) {
            (view.findViewById(R.id.itemImage) as ImageView).apply {
                setImageResource(imageId)
            }
        }
    }
}
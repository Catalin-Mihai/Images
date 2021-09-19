package com.example.images.ui.custom

import android.content.Context
import android.util.AttributeSet
import android.view.View
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.viewpager2.widget.ViewPager2
import com.example.images.R
import com.example.images.ui.custom.sliding_banner.ImagePageAdapter
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator

class GenericImage @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
): ConstraintLayout(context, attrs, defStyleAttr) {

    private var imageText: TextView
    var onClick: (View) -> Unit = {}

    init {
        val parentView = View.inflate(context, R.layout.generic_square_image, this)
        imageText = parentView.findViewById(R.id.imageText) as TextView
        imageText.setOnClickListener { onClick(it) }
    }

    fun changeText(text: String){
        imageText.text = text
    }


}
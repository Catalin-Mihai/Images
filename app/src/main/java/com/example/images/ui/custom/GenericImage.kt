package com.example.images.ui.custom

import android.content.Context
import android.util.AttributeSet
import android.util.Log
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
    private var parentElement: View

    init {
        parentElement = View.inflate(context, R.layout.generic_square_image, this)
        imageText = parentElement.findViewById(R.id.imageText) as TextView
    }

    //Make the click listener available only if they are required
    //If we would provide empty body declaration, when you press the text,
    //the text click listener won't let the view click listener to trigger
    //(as it is theoretically defined and stops the click event propagation)
    //So, let's use the listener only if we need to.

    var onImageTextClick: ((View) -> Unit)? = null
        set(value) {
            field = value
            value?.let { lambda ->
                imageText.setOnClickListener { view ->
                    lambda(view)
                }
            }
        }

    var onViewClick: ((View) -> Unit)? = null
        set(value) {
            field = value
            value?.let { lambda ->
                parentElement.setOnClickListener { view ->
                    lambda(view)
                }
            }
        }

    fun changeText(text: String){
        imageText.text = text
    }

    fun getText(): CharSequence? = imageText.text
}
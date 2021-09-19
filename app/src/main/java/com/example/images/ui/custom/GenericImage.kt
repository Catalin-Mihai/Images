package com.example.images.ui.custom

import android.content.Context
import android.util.AttributeSet
import android.view.View
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import com.example.images.R
import com.example.images.util.toPx

//Custom image element for easier use across the development flow.
class GenericImage @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
): ConstraintLayout(context, attrs, defStyleAttr) {

    private var imageText: TextView
    private var parentElement: View

    init {
        parentElement = View.inflate(context, com.example.images.R.layout.generic_square_image, this)
        imageText = parentElement.findViewById(com.example.images.R.id.imageText) as TextView


        //Custom text size because the auto scale feature works only for API26+
        context.theme.obtainStyledAttributes(
            attrs, R.styleable.GenericImage, 0, 0
        ).apply {
            try {
                val nameTextSize = getDimensionPixelSize(R.styleable.GenericImage_nameTextSize, 8.toPx)
                imageText.textSize = nameTextSize.toFloat()
            } finally {
                recycle()
            }
        }
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
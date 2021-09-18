package com.example.images.ui.custom.sliding_banner

import android.content.Context
import android.util.AttributeSet
import android.view.View
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.viewpager2.widget.ViewPager2
import com.example.images.R
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator

class SlidingBanner @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
): ConstraintLayout(context, attrs, defStyleAttr) {

    init {
        val parentView = View.inflate(context, R.layout.view_sliding_banner, this)
        val pager = parentView.findViewById(R.id.viewPager) as ViewPager2
        val tabLayout = parentView.findViewById(R.id.tabDots) as TabLayout

        val pagerAdapter = ImagePageAdapter(getContext())

        val images = listOf(
            R.drawable.image1,
            R.drawable.image2,
            R.drawable.image3
        )
        pagerAdapter.items = images

        pager.adapter = pagerAdapter

        //Needed line to link the tabs with the pager.
        //No need for custom logic
        TabLayoutMediator(tabLayout, pager) { _, _ -> }.attach()
    }
}
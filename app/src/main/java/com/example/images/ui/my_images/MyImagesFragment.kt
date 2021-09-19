package com.example.images.ui.my_images

import android.os.Bundle
import android.view.View
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.example.images.ui.MainActivityViewModel
import com.example.images.ui.generic.GenericImageListRecyclerFragment

class MyImagesFragment : GenericImageListRecyclerFragment() {

    private val viewModel: MainActivityViewModel by activityViewModels()

    override fun goToImageDetailsFragment(imageName: String){
        val action = MyImagesFragmentDirections.actionNavigationMyImagesToImageDetailsFragment(imageName)
        findNavController().navigate(action)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.liveMyImages.observe(viewLifecycleOwner, {
            super.pushNewDataSet(it)
        })
    }
}
package com.example.images.ui.best_images

import android.os.Bundle
import android.view.View
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.example.images.ui.MainActivityViewModel
import com.example.images.ui.generic.GenericImageListRecyclerFragment

class BestImagesFragment : GenericImageListRecyclerFragment() {

    private val viewModel: MainActivityViewModel by activityViewModels()

    override fun goToImageDetailsFragment(imageName: String){
        val action = BestImagesFragmentDirections.actionNavigationBestImagesToImageDetailsFragment(imageName)
        findNavController().navigate(action)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.liveBestImages.observe(viewLifecycleOwner, {
            super.pushNewDataSet(it)
        })
    }

}
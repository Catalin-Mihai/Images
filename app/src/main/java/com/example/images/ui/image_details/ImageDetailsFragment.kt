package com.example.images.ui.image_details

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.images.data.model.Image
import com.example.images.databinding.FragmentImageDetailsBinding
import com.example.images.databinding.FragmentMyImagesBinding

/**
 * A fragment representing a list of Items.
 */
class ImageDetailsFragment : Fragment() {

    private var imageName: String? = null
    private var _binding: FragmentImageDetailsBinding? = null

    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        arguments?.let {
            imageName = it.getString(ARG_IMAGE_NAME)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentImageDetailsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    companion object {

        const val ARG_IMAGE_NAME = "imageName"

        @JvmStatic
        fun newInstance(imageName: String) =
            ImageDetailsFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_IMAGE_NAME, imageName)
                }
            }
    }
}
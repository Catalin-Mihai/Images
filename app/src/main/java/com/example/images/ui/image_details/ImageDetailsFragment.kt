package com.example.images.ui.image_details

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.images.databinding.FragmentImageDetailsBinding

/**
 * A fragment representing a list of Items.
 */
class ImageDetailsFragment : Fragment() {

    private lateinit var imageName: String
    private var _binding: FragmentImageDetailsBinding? = null

    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        arguments?.let {
            imageName = it.getString(ARG_IMAGE_NAME)!!
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentImageDetailsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        //Set the image text
        binding.genericImage.changeText(imageName)

        //Go back if we touch the image name
        binding.genericImage.onImageTextClick = { _ ->
            findNavController().navigateUp()
        }
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
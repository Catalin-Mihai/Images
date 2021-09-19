package com.example.images.ui.home

import android.os.Bundle
import android.util.Log
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.GridLayout
import android.widget.TextView
import androidx.core.content.res.ResourcesCompat
import androidx.core.view.setMargins
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.example.images.R
import com.example.images.data.model.Category
import com.example.images.data.model.Image
import com.example.images.databinding.FragmentHomeBinding
import com.example.images.ui.MainActivityViewModel
import com.example.images.ui.custom.GenericImage
import com.example.images.util.toPx

class HomeFragment : Fragment() {

    private val viewModel: MainActivityViewModel by activityViewModels()
    private var _binding: FragmentHomeBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!
    private val gridLayout get() = _binding?.gridLayout!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    private fun makeGenericImage(text: String, start: Int,
                                 onViewClickListener: (View) -> Unit = {},
                                 onTextClickListener: ((View) -> Unit)? = null): View {

        //Inflate the square image
        val genericImage = GenericImage(requireContext())
        genericImage.changeText(text)

        //Set the grid params. It occupies 1 slot (of 3) and has column weight of 1
        val params = GridLayout.LayoutParams()
        params.columnSpec = GridLayout.spec(start, 1, 1f)
        params.width = 0.toPx
        params.height = ViewGroup.LayoutParams.WRAP_CONTENT
        params.setMargins(8.toPx)
        genericImage.layoutParams = params

        //Add a click listener to the element
        genericImage.onViewClick = onViewClickListener
        onTextClickListener?.let { genericImage.onImageTextClick = it }

        return genericImage
    }

    //For every category, the category title will occupy 3 columns to simulate a edge to edge title.
    private fun makeCategoryTextView(text: String): TextView {

        val categoryTV = TextView(context)

        categoryTV.text = text
        categoryTV.gravity = Gravity.CENTER
        categoryTV.textSize = 20f
        categoryTV.setTextColor(ResourcesCompat.getColor(resources, R.color.black, null))


        val params = GridLayout.LayoutParams()
        //Occupies 3 slots on the row and has column weight of 1
        params.columnSpec = GridLayout.spec(0, 3, 1f)
        params.width = 0.toPx
        params.height = ViewGroup.LayoutParams.WRAP_CONTENT
        params.topMargin = 24.toPx
        params.leftMargin = 8.toPx
        params.rightMargin = 8.toPx
        params.bottomMargin = 8.toPx

        categoryTV.layoutParams = params

        return categoryTV
    }

    private fun goToImageDetailsFragment(imageName: String){
        val action = HomeFragmentDirections.actionNavigationHomeToImageDetailsFragment(imageName)
        findNavController().navigate(action)
    }

    //We have to build programmatically the Grid View as the requirement is to use a Scroll View
    // (It could be easier achieved with RecyclerView).
    //So let's build a grid view with 3 columns. Every picture will occupy a single slot from a total of 3.
    private fun populateCategoryImages(categories: Map<Category, List<Image>>){

        categories.entries.forEach { pair ->

            val categoryName = pair.key.name

            //Add category textview
            gridLayout.addView(makeCategoryTextView(categoryName))

            //Add the images associated with the category
            pair.value.forEachIndexed { index, image ->

                val imageClickListener: (View) -> Unit = {
                    Log.e("Ceva", "Click")
                    goToImageDetailsFragment(image.name)
                }

                val imageElement = makeGenericImage(text=image.name, start=index % 3, onViewClickListener = imageClickListener)
                gridLayout.addView(imageElement)
            }
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        //When request response is ready, populate the Scroll View
        viewModel.livePhotosByCategories.observe(viewLifecycleOwner, {
            populateCategoryImages(it)
        })
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}


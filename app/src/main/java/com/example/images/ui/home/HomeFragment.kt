package com.example.images.ui.home

import android.content.res.Resources
import android.os.Bundle
import android.util.Log
import android.view.*
import android.widget.GridLayout
import android.widget.TextView
import androidx.core.content.res.ResourcesCompat
import androidx.core.view.marginTop
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
        /*val genericImage = View.inflate(context, R.layout.generic_square_image, null)
        val imageText = genericImage.findViewById(R.id.imageText) as TextView
        imageText.text = text*/
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

    /*private fun populateImagesByCategories(images: List<Image>){

        //We need to group up the images by category first
        val groups = images.groupBy { it.category }

        groups.entries.forEach { pair ->

            //NonNull category
            pair.key?.let { category ->

                //Valid category name. Don't display the images of a null-named category
                category.name?.let { categoryName ->

                    //Add category textview
                    gridLayout.addView(makeCategoryTextView(categoryName))

                    //Add the images associated with the category
                    pair.value.forEachIndexed { index, image ->

                        //We need a valid name for the image because we need that name later in another actions.
                        //Let's ignore the empty ones for now.
                        image.name?.let {
                            val imageElement = makeGenericImage(image.name, index % 3)
                            gridLayout.addView(imageElement)
                        }
                    }
                }
            }
        }
    }*/

    private fun goToImageDetailsFragment(imageName: String){
        val action = HomeFragmentDirections.actionNavigationHomeToImageDetailsFragment(imageName)
        findNavController().navigate(action)
    }

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

        viewModel.livePhotosByCategories.observe(viewLifecycleOwner, {
            populateCategoryImages(it)
        })
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}

val Float.toPx get() = this * Resources.getSystem().displayMetrics.density
val Float.toDp get() = this / Resources.getSystem().displayMetrics.density

val Int.toPx get() = (this * Resources.getSystem().displayMetrics.density).toInt()
val Int.toDp get() = (this / Resources.getSystem().displayMetrics.density).toInt()
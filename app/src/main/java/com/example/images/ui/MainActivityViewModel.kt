package com.example.images.ui

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.images.data.api.ApiBuilder
import com.example.images.data.model.Category
import com.example.images.data.model.Image
import kotlinx.coroutines.launch

class MainActivityViewModel : ViewModel() {

    //The ViewModel will load before the MainActivity is loaded.
    //So the request will be sent when the app starts.

    val liveAllImages = MutableLiveData<List<Image>>()
    val liveCategories = MutableLiveData<List<Category>>()
    val livePhotosByCategories = MutableLiveData<Map<Category, List<Image>>>()

    init {
        viewModelScope.launch {


                val data = ApiBuilder.instance.getImagesData()!!

                val allImages = data.allImages
                val categories = data.categories

                liveAllImages.postValue(allImages)
                liveCategories.postValue(categories)

                //Let's group the images by categories
                val imagesByCategories = categories.map { //List of Pairs of <Category, List<ImagesOfThatCategory>>

                    Pair(it, it.imagesIds.map { imageId -> //Get the Image object by imageId
                        allImages.find { image -> image.id == imageId }!!
                    })

                }.toMap() //Convert the list of pairs to Map

                livePhotosByCategories.postValue(imagesByCategories)
                Log.e("Response", data.toString())



        }
    }

    private val _text = MutableLiveData<String>().apply {
        value = "This is home Fragment"
    }
    val text: LiveData<String> = _text
}
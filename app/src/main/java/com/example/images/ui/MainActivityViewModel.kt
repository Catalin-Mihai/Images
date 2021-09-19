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
import org.json.JSONException

class MainActivityViewModel : ViewModel() {

    val livePhotosByCategories = MutableLiveData<Map<Category, List<Image>>>()
    val liveMyImages = MutableLiveData<List<Image>>()
    val liveBestImages = MutableLiveData<List<Image>>()

    init {
        viewModelScope.launch {

            //The ViewModel will load before the MainActivity is loaded.
            //So the request will be sent when the app starts.
            try {
                ApiBuilder.instance.testPostRequest("en", "1")
            }
            catch (e: Exception){
                Log.e("Test request", e.toString())
            }

            try {
                val data = ApiBuilder.instance.getImagesData()!!

                val allImages = data.allImages
                val categories = data.categories
                val myImages = data.myImages
                val bestImages = data.bestImages

                fun getImageById(imageId: Int): Image = allImages.find { image -> image.id == imageId }!!

                //Let's group the images by categories
                val imagesByCategories = categories.map { //List of Pairs of <Category, List<ImagesOfThatCategory>>

                    Pair(it, it.imagesIds.map { imageId -> //Get the Image object by imageId
                        getImageById(imageId)
                    })

                }.toMap() //Convert the list of pairs to Map
                livePhotosByCategories.postValue(imagesByCategories)


                //Let's get my images list
                val myImagesList = myImages.map { imageId -> getImageById(imageId) }
                liveMyImages.postValue(myImagesList)

                //Let's get my images list
                val bestImagesList = bestImages.map { imageId -> getImageById(imageId) }
                liveBestImages.postValue(bestImagesList)


//                Log.e("Response", data.toString())

            } catch (e: Exception){
                Log.e("Request Exception", e.toString())
            }
        }
    }

}
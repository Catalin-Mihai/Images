package com.example.images.ui.home

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.images.data.api.ApiBuilder
import com.example.images.data.model.Image
import kotlinx.coroutines.launch

class HomeViewModel : ViewModel() {

    //The ViewModel will load before the MainActivity is loaded.
    //So the request will be sent when the app starts.

    val liveAllImages = MutableLiveData<List<Image>>()

    init {
        viewModelScope.launch {
            val data = ApiBuilder.instance.getImagesData()
            Log.e("Response", data.toString())
        }
    }

    private val _text = MutableLiveData<String>().apply {
        value = "This is home Fragment"
    }
    val text: LiveData<String> = _text
}
package com.example.images.ui.generic

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.images.data.model.Image
import com.example.images.databinding.FragmentGenericImageListRecyclerBinding
import com.example.images.ui.MainActivityViewModel
import com.example.images.ui.custom.GenericImage

//Generic class for the recycler view screens where there is only a single list with different datasets.
abstract class GenericImageListRecyclerFragment: Fragment() {

    private val viewModel: MainActivityViewModel by activityViewModels()
    private var _binding: FragmentGenericImageListRecyclerBinding? = null
    private var dataSet: ArrayList<Image> = ArrayList()

    private var _adapter: ImageRecyclerViewAdapter? = null
    val adapter get() = _adapter!!

    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentGenericImageListRecyclerBinding.inflate(inflater, container, false)
        return binding.root
    }

    protected abstract fun goToImageDetailsFragment(imageName: String)

    @SuppressLint("NotifyDataSetChanged")
    protected fun pushNewDataSet(dataSet: List<Image>){

        this.dataSet.clear()
        this.dataSet.addAll(dataSet)
        adapter.notifyDataSetChanged()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        _adapter = ImageRecyclerViewAdapter(dataSet) { viewClicked ->
            val genericImage = viewClicked as GenericImage
            val imageName = genericImage.getText().toString()
            goToImageDetailsFragment(imageName = imageName)
        }

        binding.recyclerView.layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
        binding.recyclerView.adapter = adapter
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
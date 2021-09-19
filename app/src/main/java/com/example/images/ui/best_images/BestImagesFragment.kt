package com.example.images.ui.best_images

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.images.databinding.FragmentBestImagesBinding
import com.example.images.ui.MainActivityViewModel
import com.example.images.ui.image_details.ImageRecyclerViewAdapter

class BestImagesFragment : Fragment() {

    private val viewModel: MainActivityViewModel by activityViewModels()
    private var _binding: FragmentBestImagesBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentBestImagesBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.liveBestImages.observe(viewLifecycleOwner, {
            val adapter = ImageRecyclerViewAdapter(it)
            binding.recyclerView.layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
            binding.recyclerView.adapter = adapter
        })
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
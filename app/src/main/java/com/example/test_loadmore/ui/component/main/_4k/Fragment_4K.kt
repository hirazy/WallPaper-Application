package com.example.test_loadmore.ui.component.main._4k

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.GridLayoutManager
import com.example.test_loadmore.R
import com.example.test_loadmore.base.OBase
import com.example.test_loadmore.data.Resource
import com.example.test_loadmore.data.dto.config.PopularResource
import com.example.test_loadmore.data.dto.image.Image
import com.example.test_loadmore.databinding.Fragment4KFragmentBinding
import com.example.test_loadmore.ui.base.BaseFragment
import com.example.test_loadmore.ui.base.listeners.RecyclerItemListener
import com.example.test_loadmore.ui.component.adapter.ImageAdapter
import com.example.test_loadmore.utils.observe
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class Fragment_4K(var data: PopularResource) : BaseFragment() {

    private val viewModel: Fragment4KViewModel by viewModels()
    lateinit var binding: Fragment4KFragmentBinding

    lateinit var adapter: ImageAdapter


    override fun observeViewModel() {
        observe(viewModel.listData, ::handleRequest)
    }

    private fun handleRequest(data: Resource<List<Image>>){
        when (data) {
            is Resource.Success -> {
                Log.e("handleRequest4K", data.data!!.size.toString())
                adapter.setData(data.data!!)
            }

            is Resource.Loading -> {

            }

            is Resource.DataError -> {

            }
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding  = Fragment4KFragmentBinding.inflate(layoutInflater)
        var view = binding.root

        viewModel.fetchData(data)

        Log.e("Fragment_4K", data.id_end.toString())

        adapter = ImageAdapter(object : RecyclerItemListener {
            override fun onItemSelected(index: Int, data: OBase) {

            }

            override fun onOption(index: Int, data: OBase) {

            }

        })

        binding.rcclv4K.layoutManager = GridLayoutManager(requireContext(), 3)
        binding.rcclv4K.adapter = adapter

        return view
    }



}
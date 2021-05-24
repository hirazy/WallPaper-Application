package com.example.test_loadmore.ui.component.main.live

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.Navigation
import androidx.recyclerview.widget.GridLayoutManager
import com.example.test_loadmore.base.OBase
import com.example.test_loadmore.data.Resource
import com.example.test_loadmore.data.dto.argument.ArgumentDetailImage
import com.example.test_loadmore.data.dto.argument.ArgumentViewAll
import com.example.test_loadmore.data.dto.config.PopularResource
import com.example.test_loadmore.data.dto.image.Image
import com.example.test_loadmore.databinding.LiveFragmentBinding
import com.example.test_loadmore.ui.base.BaseFragment
import com.example.test_loadmore.ui.base.listeners.RecyclerItemListener
import com.example.test_loadmore.ui.component.adapter.ImageAdapter
import com.example.test_loadmore.ui.component.detail.image.DetailImageFragmentDirections
import com.example.test_loadmore.ui.component.main.MainFragmentDirections
import com.example.test_loadmore.utils.convertType
import com.example.test_loadmore.utils.observe
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class LiveFragment(var data: PopularResource) : BaseFragment() {

    private val viewModel: LiveViewModel by viewModels()

    lateinit var binding: LiveFragmentBinding

    lateinit var adapter: ImageAdapter

    override fun observeViewModel() {
        observe(viewModel.listData, ::handleRequest)
    }

    private fun handleRequest(data: Resource<List<Image>>) {
        when (data) {
            is Resource.Success -> {
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

        binding = LiveFragmentBinding.inflate(layoutInflater)

        adapter = ImageAdapter(object : RecyclerItemListener {
            override fun onItemSelected(index: Int, data: OBase) {
                var o = data as Image

                var birections = MainFragmentDirections.actionMainFragmentToVideoImageFragment(
                    ArgumentDetailImage(o.id, o.type)
                )
                view?.let { _view ->
                    Navigation.findNavController(_view).navigate(birections)
                }

            }

            override fun onOption(index: Int, data: OBase) {

            }

        })

        viewModel.fetchData(data)

        binding.rcclvLive.layoutManager = GridLayoutManager(requireContext(), 3)
        binding.rcclvLive.adapter = adapter

        binding.layoutLiveSearch.setOnClickListener {
            var birections = MainFragmentDirections.actionMainFragmentToSearchFragment()
            view?.let { _view ->
                Navigation.findNavController(_view).navigate(birections)
            }
        }

        var view = binding.root
        return view
    }

}
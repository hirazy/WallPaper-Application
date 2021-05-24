package com.example.test_loadmore.ui.component.main._4d

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.Navigation
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.test_loadmore.base.OBase
import com.example.test_loadmore.data.Resource
import com.example.test_loadmore.data.dto.argument.ArgumentDetailImage
import com.example.test_loadmore.data.dto.config.PopularResource
import com.example.test_loadmore.data.dto.image.Image
import com.example.test_loadmore.databinding.Fragment4DFragmentBinding
import com.example.test_loadmore.ui.base.BaseFragment
import com.example.test_loadmore.ui.base.listeners.RecyclerItemListener
import com.example.test_loadmore.ui.component.adapter.ImageAdapter
import com.example.test_loadmore.ui.component.main.MainFragmentDirections
import com.example.test_loadmore.utils.observe
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.fragment_4_d_fragment.view.*

@AndroidEntryPoint
class Fragment_4D(var data: PopularResource): BaseFragment() {

    private val viewModel: Fragment4DViewModel by viewModels()

    lateinit var binding: Fragment4DFragmentBinding

    lateinit var adapter: ImageAdapter

    var isLoading = false

    override fun observeViewModel() {
        observe(viewModel.list4D, ::handleRequest)
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
        binding = Fragment4DFragmentBinding.inflate(layoutInflater)

        adapter = ImageAdapter(object : RecyclerItemListener {
            override fun onItemSelected(index: Int, data: OBase) {
                var o = data as Image

                var birections = MainFragmentDirections.actionMainFragmentToSwipeImageFragment(
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

        binding.rcclv4D.layoutManager = GridLayoutManager(requireContext(), 3)
        binding.rcclv4D.adapter = adapter

        binding.layout4DSearch.setOnClickListener {
            var birections = MainFragmentDirections.actionMainFragmentToSearchFragment()
            view?.let { _view ->
                Navigation.findNavController(_view).navigate(birections)
            }
        }

        binding.rcclv4D.rcclv4D.addOnScrollListener(object :
            RecyclerView.OnScrollListener() {
            override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                super.onScrollStateChanged(recyclerView, newState)
            }

            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)
                val linearLayoutManager: LinearLayoutManager =
                    recyclerView.layoutManager as LinearLayoutManager

                if (!isLoading) {
                    var size = viewModel.list4D.value?.data?.size!! - 1
                    Log.e("loadMore", size.toString())
                    if (size != -1 && linearLayoutManager.findLastCompletelyVisibleItemPosition() == size && size < viewModel.end - viewModel.start) {
                        Log.e("loadMore", "viewModel")
                        loadMore()
                        isLoading = true
                    }
                }
            }
        })

        var view = binding.root
        return view
    }

    fun loadMore(){

    }

}
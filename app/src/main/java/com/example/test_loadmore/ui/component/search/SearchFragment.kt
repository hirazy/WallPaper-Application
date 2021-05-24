package com.example.test_loadmore.ui.component.search

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SearchView
import androidx.fragment.app.viewModels
import androidx.navigation.Navigation
import androidx.navigation.Navigator
import androidx.navigation.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.example.test_loadmore.base.OBase
import com.example.test_loadmore.data.Resource
import com.example.test_loadmore.data.dto.argument.ArgumentDetailImage
import com.example.test_loadmore.data.dto.image.Image
import com.example.test_loadmore.databinding.SearchFragmentBinding
import com.example.test_loadmore.ui.base.BaseFragment
import com.example.test_loadmore.ui.base.listeners.RecyclerItemListener
import com.example.test_loadmore.ui.component.adapter.ImageAdapter
import com.example.test_loadmore.utils.observe
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SearchFragment : BaseFragment() {

    private val viewModel: SearchViewModel by viewModels()

    lateinit var binding: SearchFragmentBinding

    lateinit var adapter: ImageAdapter
    override fun observeViewModel() {
        observe(viewModel.listData, ::handleRequest)
    }

    private fun handleRequest(data: Resource<List<Image>>) {
        when (data) {
            is Resource.Success -> {
                binding.rcclvSearch.visibility = View.VISIBLE
                binding.pbSearch.visibility = View.GONE
                adapter.setData(data.data!!)
                binding.tvSearchEmpty.visibility = View.GONE
            }

            is Resource.DataError -> {
                binding.pbSearch.visibility = View.GONE
                binding.tvSearchEmpty.visibility = View.VISIBLE
                binding.rcclvSearch.visibility = View.GONE
            }
        }
    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = SearchFragmentBinding.inflate(layoutInflater)

        adapter = ImageAdapter(object : RecyclerItemListener {
            override fun onItemSelected(index: Int, data: OBase) {
                var o = data as Image
                var birections = SearchFragmentDirections.actionSearchFragmentToDetailImageFragment(
                    ArgumentDetailImage(o.id, o.type)
                )
                view?.let { _view ->
                    Navigation.findNavController(_view).navigate(birections)
                }
            }

            override fun onOption(index: Int, data: OBase) {

            }
        })

        binding.icSearchClear.setOnClickListener {
            binding.edtSearch.setQuery("", false)
        }


        binding.btnSearchBack.setOnClickListener {
            view?.findNavController()?.navigateUp()
        }

        binding.edtSearch.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                binding.pbSearch.visibility = View.VISIBLE
                viewModel.searchData(query.toString())
                return true
            }

            override fun onQueryTextChange(newText: String?): Boolean {

                return true
            }

        })

        binding.rcclvSearch.layoutManager = GridLayoutManager(requireContext(), 3)
        binding.rcclvSearch.adapter = adapter


        var view = binding.root
        return view
    }

}
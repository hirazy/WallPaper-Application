package com.example.test_loadmore.ui.component.main.categories

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.test_loadmore.base.OBase
import com.example.test_loadmore.data.Resource
import com.example.test_loadmore.data.dto.argument.ArgumentViewAll
import com.example.test_loadmore.data.dto.categories.Category
import com.example.test_loadmore.databinding.CategoriesFragmentBinding
import com.example.test_loadmore.ui.base.BaseFragment
import com.example.test_loadmore.ui.base.listeners.RecyclerItemListener
import com.example.test_loadmore.ui.component.main.MainFragmentDirections
import com.example.test_loadmore.ui.component.main.categories.adapter.CategoriesAdapter
import com.example.test_loadmore.utils.observe

class CategoriesFragment(var data: List<Category>) : BaseFragment() {

    lateinit var binding: CategoriesFragmentBinding

    private val viewModel: CategoriesViewModel by viewModels()

    lateinit var adapter: CategoriesAdapter
    override fun observeViewModel() {
        observe(viewModel.listData, ::handleRequest)
    }

    private fun handleRequest(data: Resource<List<Category>>){
        when(data){
            is Resource.Success->{
                adapter.setData(data.data!!)
            }
        }
    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        Log.e("CategoriesFragment", data.size.toString())

        binding = CategoriesFragmentBinding.inflate(layoutInflater)
        var view = binding.root

        adapter = CategoriesAdapter(object : RecyclerItemListener {
            override fun onItemSelected(index: Int, data: OBase) {
                var o = data as Category
                var birections = MainFragmentDirections.actionMainFragmentToViewAllFragment(
                    ArgumentViewAll(o.name)
                )
                view?.let { _view ->
                    Navigation.findNavController(_view).navigate(birections)
                }
            }

            override fun onOption(index: Int, data: OBase) {

            }

        })

        binding.rcclvCategories.layoutManager = LinearLayoutManager(requireContext())
        binding.rcclvCategories.adapter = adapter

        viewModel.fetchData(data)

        return view
    }


}
package com.example.test_loadmore.ui.component.viewall

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.Navigation
import androidx.navigation.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.GridLayoutManager
import com.example.test_loadmore.base.OBase
import com.example.test_loadmore.data.Resource
import com.example.test_loadmore.data.dto.argument.ArgumentDetailImage
import com.example.test_loadmore.data.dto.argument.ArgumentViewAll
import com.example.test_loadmore.data.dto.categories.CategoryL
import com.example.test_loadmore.data.dto.image.Image
import com.example.test_loadmore.databinding.ViewAllFragmentBinding
import com.example.test_loadmore.listType
import com.example.test_loadmore.ui.base.BaseFragment
import com.example.test_loadmore.ui.base.listeners.RecyclerItemListener
import com.example.test_loadmore.ui.component.adapter.ImageAdapter
import com.example.test_loadmore.ui.component.main.MainFragmentDirections
import com.example.test_loadmore.utils.convertType
import com.example.test_loadmore.utils.observe
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ViewAllFragment : BaseFragment() {

    val args by navArgs<ViewAllFragmentArgs>()
    lateinit var binding: ViewAllFragmentBinding
    private val viewModel: ViewAllViewModel by viewModels()
    lateinit var adapter: ImageAdapter


    override fun observeViewModel() {
        observe(viewModel.listData, ::handleRequest)
    }

    private fun handleRequest(data: Resource<List<Image>>) {
        when (data) {
            is Resource.Success -> {
                adapter.setData(data.data!!)
            }
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = ViewAllFragmentBinding.inflate(layoutInflater)
        val view = binding.root

        var name = ""

//        for (i in 0 until listType.size) {
//            if (args.data!!.name == listType[i]) {
//                name = args.data!!.name
//                break
//            }
//        }

        binding.tvViewAll.text = args.data!!.name


        adapter = ImageAdapter(object : RecyclerItemListener {
            override fun onItemSelected(index: Int, data: OBase) {

                var o = data as Image

                var birections =
                    ViewAllFragmentDirections.actionViewAllFragmentToDetailImageFragment(
                        ArgumentDetailImage(o.id, o.type)
                    )
                view?.let { _view ->
                    Navigation.findNavController(_view).navigate(birections)
                }
            }

            override fun onOption(index: Int, data: OBase) {

            }

        })

        binding.rcclvViewAll.layoutManager = GridLayoutManager(requireContext(), 3)
        binding.rcclvViewAll.adapter = adapter

        viewModel.fetchData(name)

        binding.btnBackViewAll.setOnClickListener {
            view?.let { _view ->
                view?.findNavController()?.navigateUp()
            }
        }

        return view
    }

}
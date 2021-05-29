package com.example.test_loadmore.ui.component.fav_download

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import android.os.Handler
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.NavArgs
import androidx.navigation.Navigation
import androidx.navigation.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.GridLayoutManager
import com.example.test_loadmore.R
import com.example.test_loadmore.base.OBase
import com.example.test_loadmore.data.Resource
import com.example.test_loadmore.data.dto.argument.ArgumentDetailImage
import com.example.test_loadmore.data.dto.argument.ArgumentFavDownLoad
import com.example.test_loadmore.data.dto.image.Image
import com.example.test_loadmore.databinding.FavDownLoadFragmentBinding
import com.example.test_loadmore.ui.base.BaseFragment
import com.example.test_loadmore.ui.base.listeners.RecyclerItemListener
import com.example.test_loadmore.ui.component.adapter.ImageAdapter
import com.example.test_loadmore.ui.component.main.MainFragmentDirections
import com.example.test_loadmore.utils.observe
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.app_bar_top.view.*

@AndroidEntryPoint
class FavDownLoadFragment : BaseFragment() {

    val args by navArgs<FavDownLoadFragmentArgs>()

    lateinit var binding: FavDownLoadFragmentBinding

    lateinit var adapter: ImageAdapter

    private val viewModel: FavDownLoadViewModel by viewModels()
    override fun observeViewModel() {
        observe(viewModel.listData, ::handleRequest)
    }

    private fun handleRequest(data: Resource<List<Image>>){
        when(data){
            is Resource.Success -> {
                Log.e("FavDownLoadFragment", "handleRequest: " + data.data.toString() )
                adapter.setData(data.data!!)
            }
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = FavDownLoadFragmentBinding.inflate(layoutInflater)

        binding.btnFavDownLoadBack.setOnClickListener {
            view?.let { _view ->
                view?.findNavController()?.navigateUp()
            }
        }

        adapter = ImageAdapter(object : RecyclerItemListener {
            override fun onItemSelected(index: Int, data: OBase) {
                var o = data as Image
                var birections = FavDownLoadFragmentDirections.actionFavDownLoadFragmentToDetailImageFragment(
                    ArgumentDetailImage(o.id, o.type)
                )
                view?.let { _view ->
                    Navigation.findNavController(_view).navigate(birections)
                }
            }

            override fun onOption(index: Int, data: OBase) {

            }

        })

        binding.rcclvFavDownLoad.adapter = adapter
        binding.rcclvFavDownLoad.layoutManager = GridLayoutManager(requireContext(), 3)
//
//        binding.swrFavDownLoad.setOnRefreshListener{
//            var handler = Handler()
//            handler.postDelayed({
//                binding.swrFavDownLoad.isRefreshing = false
//                viewModel.reLoad()
//            }, 2000)
//        }

        viewModel.fetchData(args.data!!)

        var view = binding.root
        return view
    }
}
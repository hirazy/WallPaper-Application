package com.example.test_loadmore.ui.component.main.top

import android.os.Bundle
import android.os.Handler
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.example.test_loadmore.databinding.TopFragmentBinding
import com.example.test_loadmore.ui.base.BaseFragment
import com.example.test_loadmore.ui.component.main.top.adapter.CategoriesTopAdapter
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.app_bar_top.view.*


@AndroidEntryPoint
class TopFragment : BaseFragment() {

    lateinit var binding: TopFragmentBinding

    lateinit var adapterCategory: CategoriesTopAdapter

    private val viewModel: TopViewModel by viewModels()

    private var handler: Handler? = null

    companion object {
        fun newInstance() = TopFragment()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = TopFragmentBinding.inflate(layoutInflater)

        handler = Handler()

        binding.drawerLayout.btnTopMenu.setOnClickListener {
            binding.drawerLayout.openDrawer(Gravity.LEFT)
        }
        binding.drawerLayout.layoutSearch.setOnClickListener {
            view.let {

            }
        }
        binding.drawerLayout.swrTop.setOnRefreshListener {

            binding.drawerLayout.swrTop.isRefreshing = false;
        }


        var view = binding.root
        return view
    }

    override fun observeViewModel() {

    }




    // To equal size
    // android:scaleType="centerInside"
    //   android:adjustViewBounds="true"

}
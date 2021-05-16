package com.example.test_loadmore.ui.component.viewall

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import com.example.test_loadmore.R
import com.example.test_loadmore.databinding.ViewAllFragmentBinding
import com.example.test_loadmore.ui.base.BaseFragment
import com.example.test_loadmore.ui.component.main.top.TopViewModel

class ViewAllFragment : BaseFragment() {

    // val args by navArgs<ViewAll>()

    companion object {
        fun newInstance() = ViewAllFragment()
    }

    lateinit var binding: ViewAllFragmentBinding
    private val viewModel: ViewAllFragment by viewModels()
    override fun observeViewModel() {

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = ViewAllFragmentBinding.inflate(layoutInflater)
        val view  = binding.root

        // var arguments = args.data!!

        return view
    }

}
package com.example.test_loadmore.ui.component.main._4k

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.test_loadmore.R

class Fragment_4K : Fragment() {

    companion object {
        fun newInstance() = Fragment_4K()
    }

    private lateinit var viewModel: Fragment4KViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_4_k_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(Fragment4KViewModel::class.java)
        // TODO: Use the ViewModel
    }

}
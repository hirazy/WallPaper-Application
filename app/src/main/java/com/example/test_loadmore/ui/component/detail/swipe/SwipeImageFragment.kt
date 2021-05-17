package com.example.test_loadmore.ui.component.detail.swipe

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.test_loadmore.R

class SwipeImageFragment : Fragment() {

    companion object {
        fun newInstance() = SwipeImageFragment()
    }

    private lateinit var viewModel: SwipeImageViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.swipe_image_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(SwipeImageViewModel::class.java)
        // TODO: Use the ViewModel
    }

}
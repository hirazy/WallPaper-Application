package com.example.test_loadmore.ui.component.detail.video

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.test_loadmore.R

class VideoImageFragment : Fragment() {

    companion object {
        fun newInstance() = VideoImageFragment()
    }

    private lateinit var viewModel: VideoImageViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.video_image_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(VideoImageViewModel::class.java)
        // TODO: Use the ViewModel
    }

}
package com.example.test_loadmore.ui.component.detail.swipe

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.findNavController
import androidx.navigation.fragment.navArgs
import com.example.test_loadmore.BASE_URL
import com.example.test_loadmore.R
import com.example.test_loadmore.TYPE_4K
import com.example.test_loadmore.databinding.SwipeImageFragmentBinding
import com.example.test_loadmore.ui.base.BaseFragment
import com.example.test_loadmore.ui.component.detail.image.DetailImageFragmentArgs
import com.squareup.picasso.Picasso
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SwipeImageFragment : BaseFragment() {

    lateinit var binding: SwipeImageFragmentBinding

    val args by navArgs<SwipeImageFragmentArgs>()


    private val  viewModel: SwipeImageViewModel by viewModels()

    override fun observeViewModel() {

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        requireActivity().window.decorView.apply {
            // Hide both the navigation bar and the status bar.
            // SYSTEM_UI_FLAG_FULLSCREEN is only available on Android 4.1 and higher, but as
            // a general rule, you should design your app to hide the status bar whenever you
            // hide the navigation bar.
            systemUiVisibility =
                View.SYSTEM_UI_FLAG_HIDE_NAVIGATION or View.SYSTEM_UI_FLAG_FULLSCREEN
        }

        binding = SwipeImageFragmentBinding.inflate(layoutInflater)

        var o = args.data

        var typeVal = ""

        if(o!!.type == TYPE_4K){
            typeVal = "4k/"
        }
        else{
            typeVal = "4d/"
        }

        binding.layoutDetailSwipeBack.setOnClickListener {
            view?.let { _view ->
                view?.findNavController()?.navigateUp()
            }
        }

        Picasso.get().load(BASE_URL + typeVal + o!!.id + ".jpg").fit()
            .into(binding.imgDetailSwipeImage, object : com.squareup.picasso.Callback {
                override fun onSuccess() {

                    binding.pbSwipeImage.visibility = View.GONE
                }

                override fun onError(e: Exception?) {
                }
            })

        var view = binding.root
        return view
    }
}
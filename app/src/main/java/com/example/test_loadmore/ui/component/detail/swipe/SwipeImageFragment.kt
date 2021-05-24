package com.example.test_loadmore.ui.component.detail.swipe

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
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

        binding = SwipeImageFragmentBinding.inflate(layoutInflater)

        var o = args.data

        var typeVal = ""

        if(o!!.type == TYPE_4K){
            typeVal = "4k/"
        }
        else{
            typeVal = "4d/"
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
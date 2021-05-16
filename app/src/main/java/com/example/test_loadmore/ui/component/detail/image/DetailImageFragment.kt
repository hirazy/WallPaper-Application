package com.example.test_loadmore.ui.component.detail.image

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.findNavController
import androidx.navigation.fragment.navArgs
import com.example.test_loadmore.BASE_URL
import com.example.test_loadmore.databinding.DetailImageFragmentBinding
import com.example.test_loadmore.ui.base.BaseFragment
import com.squareup.picasso.Picasso

class DetailImageFragment : BaseFragment() {

    val args by navArgs<DetailImageFragmentArgs>()

    lateinit var binding: DetailImageFragmentBinding

    private val viewModel: DetailImageViewModel by viewModels()


    override fun observeViewModel() {

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        var o = args.data!!

        binding = DetailImageFragmentBinding.inflate(layoutInflater)
        Picasso.get().load(BASE_URL + "image/" + o.id.toString() + ".jpg").fit()
            .into(binding.imgDetailImage, object : com.squareup.picasso.Callback {
                override fun onSuccess() {

                    binding.pbDetailImage.visibility = View.GONE
                }

                override fun onError(e: Exception?) {
                }
            })

        binding.layoutDetailImageBack.setOnClickListener {
            view?.let { _view ->

                view?.findNavController()?.navigateUp()
            }
        }


        val view = binding.root
        return view
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)


    }

}
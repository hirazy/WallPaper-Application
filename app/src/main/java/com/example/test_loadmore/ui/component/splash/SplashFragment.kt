package com.example.test_loadmore.ui.component.splash

import android.content.Intent
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.os.Handler
import android.provider.Settings
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.test_loadmore.R
import com.example.test_loadmore.data.Resource
import com.example.test_loadmore.data.dto.argument.ArgumentRequestNetwork
import com.example.test_loadmore.databinding.SplashFragmentBinding
import com.example.test_loadmore.ui.base.BaseFragment
import com.example.test_loadmore.ui.component.splash.adapter.LoadMoreAdapter
import com.example.test_loadmore.utils.observe
import com.google.android.gms.ads.AdListener
import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.LoadAdError
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.splash_fragment.*


@AndroidEntryPoint
class SplashFragment : BaseFragment() {

    private val viewModel: SplashViewModel by viewModels()
    lateinit var adapter: LoadMoreAdapter
    var isLoading = false

    lateinit var binding: SplashFragmentBinding

    var list = ArrayList<String>()

    override fun observeViewModel() {
        observe(viewModel.resultRequest, ::handleRequest)
    }

    private fun handleRequest(data: Resource<ArgumentRequestNetwork>) {
        when (data) {
            is Resource.Success -> {
                Log.e("handleRequest", data.data!!.category.size.toString())
                navigate(data.data!!)
            }
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = SplashFragmentBinding.inflate(layoutInflater)
        var view = binding.root

        return view
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        populateData()

        adapter = LoadMoreAdapter(list)

        rcclvMain.layoutManager = LinearLayoutManager(requireContext())
        rcclvMain.adapter = adapter

        initScrollListener()

        val adRequest = AdRequest.Builder().build()
        //  adViewSplash.loadAd(adRequest)

        adViewSplash.adListener = object : AdListener() {
            override fun onAdClosed() {
                super.onAdClosed()
            }

            override fun onAdClicked() {
                super.onAdClicked()
            }

            override fun onAdFailedToLoad(p0: LoadAdError) {
                super.onAdFailedToLoad(p0)
            }
        }
    }

    private fun navigate(result: ArgumentRequestNetwork) {
        view?.let { _view ->
            var birections = SplashFragmentDirections.actionSplashFragmentToMainFragment(result)
            Navigation.findNavController(_view).navigate(birections)
        }
    }

    private fun initScrollListener() {
        rcclvMain.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                super.onScrollStateChanged(recyclerView, newState)
            }

            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)
                val linearLayoutManager: LinearLayoutManager =
                    recyclerView.layoutManager as LinearLayoutManager
                if (!isLoading) {
                    if (linearLayoutManager != null && linearLayoutManager.findLastCompletelyVisibleItemPosition() === list.size - 1) {
                        loadMore()
                        isLoading = true
                    }
                }
            }
        })
    }

    private fun loadMore() {
        list.add(null.toString())
        adapter.notifyItemInserted(list.size - 1)
        val handler = Handler()
        handler.postDelayed({
            list.removeAt(list.size - 1)
            val scrollPosition: Int = list.size
            adapter.notifyItemRemoved(scrollPosition)
            var currentSize = scrollPosition
            val nextLimit = currentSize + 10
            while (currentSize - 1 < nextLimit) {
                list.add("Item $currentSize")
                currentSize++
            }
            adapter.notifyDataSetChanged()
            isLoading = false
        }, 2000)
    }

    private fun populateData() {
        var i = 0
        while (i < 10) {
            list.add("Item $i")
            i++
        }
    }

    @RequiresApi(Build.VERSION_CODES.M)
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if (Settings.System.canWrite(requireContext().applicationContext)) {
            Settings.System.putFloat(
                requireActivity().getContentResolver(),
                Settings.System.FONT_SCALE, 1.0.toFloat()
            )
        }
        super.onActivityResult(requestCode, resultCode, data)
    }

}
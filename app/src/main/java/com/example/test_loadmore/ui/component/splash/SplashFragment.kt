package com.example.test_loadmore.ui.component.splash

import android.content.Intent
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.os.Handler
import android.provider.Settings
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.test_loadmore.R
import com.example.test_loadmore.ui.base.BaseFragment
import com.example.test_loadmore.ui.component.splash.adapter.LoadMoreAdapter
import com.google.android.gms.ads.AdListener
import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.LoadAdError
import kotlinx.android.synthetic.main.splash_fragment.*


class SplashFragment : BaseFragment() {

    companion object {
        fun newInstance() = SplashFragment()
    }

    private lateinit var viewModel: SplashViewModel
    lateinit var adapter: LoadMoreAdapter
    var isLoading = false

    var list = ArrayList<String>()

    override fun observeViewModel() {

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.splash_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(SplashViewModel::class.java)

        populateData()

        adapter = LoadMoreAdapter(list)

        rcclvMain.layoutManager = LinearLayoutManager(requireContext())
        rcclvMain.adapter = adapter

        initScrollListener()

        val adRequest = AdRequest.Builder().build()
        adViewSplash.loadAd(adRequest)

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

        val handler = Handler()
        handler.postDelayed(Runnable { navigate() }, 2000)
    }

    private fun navigate() {
        view?.let { _view ->
            var birections = SplashFragmentDirections.actionSplashFragmentToMainFragment()
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
                    recyclerView.getLayoutManager() as LinearLayoutManager
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
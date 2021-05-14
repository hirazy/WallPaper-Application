package com.example.test_loadmore.ui.component.main

import android.R.attr.x
import android.R.attr.y
import android.annotation.SuppressLint
import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.annotation.RequiresApi
import androidx.fragment.app.viewModels
import androidx.palette.graphics.Palette
import androidx.viewpager2.widget.ViewPager2
import com.example.test_loadmore.KEY_SAVE_MODE
import com.example.test_loadmore.LIGHT_MODE
import com.example.test_loadmore.NIGHT_MODE
import com.example.test_loadmore.R
import com.example.test_loadmore.base.data.preference.PrefHelperImpl
import com.example.test_loadmore.databinding.FragmentMainBinding
import com.example.test_loadmore.ui.base.BaseFragment
import com.example.test_loadmore.ui.component.main.adapter.PagerAdapter
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class MainFragment : BaseFragment() {

    private lateinit var binding: FragmentMainBinding

    val viewModel: MainViewModel by viewModels()

    lateinit var adapter: PagerAdapter

    override fun observeViewModel() {

    }

    private fun getColor(imageView: ImageView): Int {
        val colorCode: Int = imageView.drawingCache.getPixel(x, y)
        return colorCode
    }

    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    fun changeColorStatus(image: ImageView) {

        image.buildDrawingCache()
        val bitmap = image.drawingCache

        Palette.from(bitmap)
            .generate { palette -> // toolbar.setBackgroundColor(palette!!.getVibrantColor(resources.getColor(R.color.primary)));
                // activity?.window?.statusBarColor = palette!!.getVibrantColor(resources.getColor(R.color.primary));
            }
    }


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentMainBinding.inflate(layoutInflater)
        val view = binding.root

        if (PrefHelperImpl(requireActivity().application).getData(KEY_SAVE_MODE).equals("")) {
            PrefHelperImpl(requireActivity().application).setData(KEY_SAVE_MODE, LIGHT_MODE)
        }

        if (PrefHelperImpl(requireActivity().application).getData(KEY_SAVE_MODE) == NIGHT_MODE) {

            requireContext().setTheme(R.style.darkTheme)
        } else requireContext().setTheme(R.style.AppTheme)

        super.onCreate(savedInstanceState)

        binding.btmNavMain.setOnNavigationItemSelectedListener {
            when (it.itemId) {
                R.id.itemBtmTop -> {
                    // Change Fragment to Top
                    binding.vpgMain.currentItem = 0
                }

                R.id.itemBtm4D -> {
                    // Change Fragment to 4D
                    binding.vpgMain.currentItem = 1
                }

                R.id.itemBtm4K -> {
                    // Change Fragment to Live
                    binding.vpgMain.currentItem = 2
                }

                R.id.itemBtm4K -> {
                    //Change Fragment to 4K
                    binding.vpgMain.currentItem = 3
                }

                R.id.itemBtmCategories -> {
                    // Change Fragment to Categories
                    binding.vpgMain.currentItem = 4
                }
            }
            true
        }

        setUpPageAdapter()


        return view
    }

    @SuppressLint("ClickableViewAccessibility")
    fun setUpPageAdapter() {

        adapter = PagerAdapter(requireActivity())

        binding.vpgMain.adapter = adapter

        binding.vpgMain.setOnTouchListener { arg0, arg1 -> true }

        binding.vpgMain.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {

            override fun onPageSelected(position: Int) {
                when (position) {
                    0 -> {
                        binding.btmNavMain.menu.findItem(R.id.itemBtmTop).isChecked = true
                    }
                    1 -> {
                        binding.btmNavMain.menu.findItem(R.id.itemBtm4D).isChecked = true
                    }
                    2 -> {

                        binding.btmNavMain.menu.findItem(R.id.itemBtmLive).isChecked = true
                    }
                    3 -> {
                        binding.btmNavMain.menu.findItem(R.id.itemBtm4K).isChecked = true
                    }
                    4 -> {
                        binding.btmNavMain.menu.findItem(R.id.itemBtmCategories).isChecked = true
                    }
                }
            }
        })

        binding.vpgMain.offscreenPageLimit = 5
    }


}
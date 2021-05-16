package com.example.test_loadmore.ui.component.main.top

import android.os.Build
import android.os.Bundle
import android.os.Handler
import android.util.Log
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.fragment.app.viewModels
import androidx.navigation.Navigation
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager.widget.ViewPager
import com.example.test_loadmore.base.OBase
import com.example.test_loadmore.data.Resource
import com.example.test_loadmore.data.dto.argument.ArgumentDetailImage
import com.example.test_loadmore.data.dto.argument.ArgumentViewAll
import com.example.test_loadmore.data.dto.categories.top.CategoryTop
import com.example.test_loadmore.data.dto.image.Image
import com.example.test_loadmore.databinding.TopFragmentBinding
import com.example.test_loadmore.ui.base.BaseFragment
import com.example.test_loadmore.ui.base.listeners.RecyclerItemListener
import com.example.test_loadmore.ui.component.adapter.ImageAdapter
import com.example.test_loadmore.ui.component.main.MainFragmentDirections
import com.example.test_loadmore.ui.component.main.top.adapter.CategoriesTopAdapter
import com.example.test_loadmore.ui.component.main.top.adapter.YouAdapter
import com.example.test_loadmore.utils.observe
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.app_bar_top.view.*


@AndroidEntryPoint
class TopFragment : BaseFragment() {

    lateinit var binding: TopFragmentBinding

    lateinit var adapterCategory: CategoriesTopAdapter
    lateinit var adapterYou: YouAdapter

    lateinit var adapterTrending: ImageAdapter

    var list = ArrayList<Image>()

    private val viewModel: TopViewModel by viewModels()
    var isLoading = false

    var init = false

    private var handler: Handler? = null

    companion object {
        fun newInstance() = TopFragment()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = TopFragmentBinding.inflate(layoutInflater)

        handler = Handler()

        binding.drawerLayout.btnTopMenu.setOnClickListener {
            binding.drawerLayout.openDrawer(Gravity.LEFT)
        }
        binding.drawerLayout.layoutSearch.setOnClickListener {
            view.let {

            }
        }
        binding.drawerLayout.swrTop.setOnRefreshListener {
            var handler = Handler()
            handler.postDelayed({
                init = false
                binding.drawerLayout.swrTop.isRefreshing = false
                viewModel.reloadTrending()
            }, 3000)

        }

        adapterYou = YouAdapter(object : RecyclerItemListener {
            override fun onItemSelected(index: Int, data: OBase) {
                var o = data as Image
                var birections = MainFragmentDirections.actionMainFragmentToDetailImageFragment(
                    ArgumentDetailImage(o.id)
                )
                view?.let { _view ->
                    Navigation.findNavController(_view).navigate(birections)
                }
            }

            override fun onOption(index: Int, data: OBase) {

            }
        })

        binding.drawerLayout.tvYouViewAll.setOnClickListener {
//            var birections = MainFragmentDirections.actionMainFragmentToViewAllFragment(
//
//            )
//            view?.let { _view ->
//                Navigation.findNavController(_view).navigate(birections)
//            }
        }

        binding.drawerLayout.rcclvForYou.layoutManager =
            LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
        binding.drawerLayout.rcclvForYou.adapter = adapterYou

        adapterTrending = ImageAdapter(object : RecyclerItemListener {
            override fun onItemSelected(index: Int, data: OBase) {
                var o = data as Image
                var birections = MainFragmentDirections.actionMainFragmentToDetailImageFragment(
                    ArgumentDetailImage(o.id)
                )
                view?.let { _view ->
                    Navigation.findNavController(_view).navigate(birections)
                }
            }

            override fun onOption(index: Int, data: OBase) {

            }
        })

        binding.drawerLayout.rcclvTrending.layoutManager = GridLayoutManager(requireContext(), 3)
        binding.drawerLayout.rcclvTrending.adapter = adapterTrending

        binding.drawerLayout.layoutSearch.setOnClickListener {
            var birections = MainFragmentDirections.actionMainFragmentToSearchFragment()
            view?.let { _view ->
                viewModel.listTrendingLiveData.value?.data?.size!! - 1
                Navigation.findNavController(_view).navigate(birections)
            }
        }

        binding.drawerLayout.rcclvTrending.addOnScrollListener(object :
            RecyclerView.OnScrollListener() {
            override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                super.onScrollStateChanged(recyclerView, newState)
            }

            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)
                val linearLayoutManager: LinearLayoutManager =
                    recyclerView.layoutManager as LinearLayoutManager

                if (!isLoading) {
                    var size = viewModel.listTrendingLiveData.value?.data?.size!! - 1
                    Log.e("loadMore", size.toString())
                    if (size != -1 && linearLayoutManager.findLastCompletelyVisibleItemPosition() == size && size < viewModel.end - viewModel.start) {
                        Log.e("loadMore", "viewModel")
                        loadMore()
                        isLoading = true
                    }
                }
            }
        })


        var view = binding.root
        return view
    }

    override fun observeViewModel() {
        observe(viewModel.listCategoryLiveData, ::handleCategory)
        observe(viewModel.listForYouLiveData, ::handleForYou)
        observe(viewModel.listTrendingLiveData, ::handleTrending)
    }

    private fun handleCategory(data: Resource<List<CategoryTop>>) {
        when (data) {
            is Resource.Success -> {

                // binding.drawerLayout.pbTopForYou.visibility = View.GONE
                Log.e("handleCategory", data.data?.get(0)!!.thumb.toString() + data.data.size)

                adapterCategory = CategoriesTopAdapter(data.data!!, requireContext()) {
                    var birections = MainFragmentDirections.actionMainFragmentToViewAllFragment(
                        ArgumentViewAll(it)
                    )
                    view?.let { _view ->
                        Navigation.findNavController(_view).navigate(birections)
                    }
                }

                binding.drawerLayout.vpgTopCategories.addOnPageChangeListener(object :
                    ViewPager.OnPageChangeListener {
                    override fun onPageScrolled(
                        position: Int,
                        positionOffset: Float,
                        positionOffsetPixels: Int
                    ) {
                    }

                    override fun onPageSelected(position: Int) {
                    }

                    override fun onPageScrollStateChanged(state: Int) {
                    }
                })
                binding.drawerLayout.vpgTopCategories.adapter = adapterCategory

            }

            is Resource.Loading -> {
                binding.drawerLayout.pbTopForYou.visibility = View.VISIBLE
            }

            is Resource.DataError -> {
                Toast.makeText(
                    requireContext(),
                    "Please check the Internet connection!",
                    Toast.LENGTH_SHORT
                ).show()
                binding.drawerLayout.pbTopForYou.visibility = View.GONE
            }
        }
    }

    private fun handleForYou(data: Resource<List<Image>>) {
        when (data) {
            is Resource.Success -> {
                binding.drawerLayout.pbTopForYou.visibility = View.GONE
                adapterYou.setData(data.data!!)
            }
            is Resource.Loading -> {

            }
            is Resource.DataError -> {

            }
        }
    }

    private fun handleTrending(data: Resource<List<Image>>) {
        Log.e("handleTrending", data.toString())
        when (data) {
            is Resource.Success -> {
                if (!init) {
                    binding.drawerLayout.pbTopTrending.visibility = View.GONE
                    list = ArrayList<Image>(data.data!!)
                    adapterTrending.setData(list)
                    init = true
                }
            }
            is Resource.Loading -> {

            }
            is Resource.DataError -> {


            }
        }
    }

    fun loadMore() {
        val handler = Handler()
        binding.drawerLayout.pbTop.visibility = View.VISIBLE

        handler.postDelayed({
            var ind = viewModel.listTrendingLiveData.value!!.data!!.size
            Log.e("postDelayed", ind.toString())
            viewModel.loadMore()
            Log.e("postDelayed", viewModel.listTrendingLiveData.value!!.data!!.size.toString())

            var listTmp = viewModel.listTrendingLiveData.value!!.data

            for (i in ind until listTmp!!.size) {

            }

            adapterTrending.notifyItemRangeInserted(
                ind,
                viewModel.listTrendingLiveData.value!!.data!!.size - ind
            )
            isLoading = false
            binding.drawerLayout.pbTop.visibility = View.GONE
        }, 2000)
    }

    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    fun changeColorStatus(image: ImageView) {
//
//        image.buildDrawingCache()
//        val bitmap = image.drawingCache
//
//        Palette.from(bitmap)
//            .generate { palette ->  requireActivity().toolbar.setBackgroundColor(palette!!.getVibrantColor(resources.getColor(R.color.primary)));
//                requireActivity().window.statusBarColor =
//                    palette!!.getVibrantColor(resources.getColor(R.color.transparent));
//            };
    }

    // To equal size
    // android:scaleType="centerInside"
    //   android:adjustViewBounds="true"

}
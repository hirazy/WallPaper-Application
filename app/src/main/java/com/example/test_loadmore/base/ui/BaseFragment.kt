package com.example.test_loadmore.base.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import androidx.fragment.app.setFragmentResult
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavDirections
import androidx.navigation.Navigation
import androidx.navigation.findNavController
import com.example.test_loadmore.utils.LayoutId
import com.example.test_loadmore.base.widget.DialogLoading


abstract class BaseFragment<T : ViewDataBinding, K : BaseViewModel> : Fragment() {
    lateinit var binding: T
    lateinit var viewModel: K

    companion object {
        val REQUEST_KEY = "data"
    }

    var cout = 1

    fun navigate(action: NavDirections) {
        view?.let { _view ->
            Navigation.findNavController(_view).navigate(action)
        }
    }

    fun goback(data: Any) {
        view?.let { _view ->
            setFragmentResult(REQUEST_KEY, bundleOf("data" to data))
            // Step 4. Go back to Fragment A
            view?.findNavController()?.navigateUp()
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        if (cout == 1) {
            binding = DataBindingUtil.inflate(
                inflater,
                javaClass.getAnnotation(LayoutId::class.java).value,
                container,
                false
            )

            viewModel = ViewModelProvider(this).get(getVModel())
            binding.lifecycleOwner = this
            binding.executePendingBindings()
        }
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        if (cout == 1) {

            dialog = DialogLoading(requireContext())

            this.setObserveLive(viewModel);

            initView()

            start()

            cout++

        }
    }


    abstract fun getVModel(): Class<K>

    lateinit var dialog: DialogLoading


    abstract fun initView()

    abstract fun start()


    fun setObserveLive(viewModel: BaseViewModel) {

    }


}



package com.example.test_loadmore.ui.component.detail.video

import android.media.MediaPlayer
import android.net.Uri
import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import android.provider.MediaStore
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.findNavController
import androidx.navigation.fragment.navArgs
import com.example.test_loadmore.BASE_URL
import com.example.test_loadmore.BASE_URL_VIDEO
import com.example.test_loadmore.R
import com.example.test_loadmore.databinding.VideoImageFragmentBinding
import com.example.test_loadmore.ui.base.BaseFragment
import com.example.test_loadmore.ui.component.detail.image.DetailImageFragmentArgs
import kotlinx.coroutines.*

class VideoImageFragment : BaseFragment() {

    val args by navArgs<VideoImageFragmentArgs>()

    lateinit var binding: VideoImageFragmentBinding

    private val  viewModel: VideoImageViewModel by viewModels()
    override fun observeViewModel() {

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        var urlVideo = BASE_URL_VIDEO + args.data!!.id +  ".mp4"

        binding = VideoImageFragmentBinding.inflate(layoutInflater)

        binding.videoViewImage.setVideoURI(Uri.parse(urlVideo))
        binding.videoViewImage.start()

        // var waitLoading: Job = startRepeatingJob(100)


        binding.videoViewImage.setOnPreparedListener {
            it.setOnBufferingUpdateListener { mediaPlayer: MediaPlayer, i: Int ->
                binding.linearProgress.progress = i
            }
            binding.linearProgress.visibility = View.GONE

            it.isLooping = true
        }

        binding.layoutDetailSwipeBack.setOnClickListener {
            view?.let { _view ->
                view?.findNavController()?.navigateUp()
            }
        }

        var view = binding.root

        return view
    }

    private fun startRepeatingJob(timeInterval: Long): Job {
        return CoroutineScope(Dispatchers.Default).launch {
            while (binding.videoViewImage.currentPosition < 100) {

                delay(timeInterval)
            }
        }
    }

}
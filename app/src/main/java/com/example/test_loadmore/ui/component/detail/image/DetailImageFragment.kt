package com.example.test_loadmore.ui.component.detail.image

import android.app.Dialog
import android.content.ActivityNotFoundException
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.os.Handler
import android.view.*
import android.widget.Button
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.navigation.Navigation
import androidx.navigation.findNavController
import androidx.navigation.fragment.navArgs
import com.dinuscxj.progressbar.CircleProgressBar
import com.downloader.Error
import com.downloader.OnDownloadListener
import com.downloader.PRDownloader
import com.example.test_loadmore.BASE_URL
import com.example.test_loadmore.EMAIL_REPORT
import com.example.test_loadmore.R
import com.example.test_loadmore.data.Resource
import com.example.test_loadmore.data.dto.argument.ArgumentDetailImage
import com.example.test_loadmore.data.dto.argument.ArgumentViewAll
import com.example.test_loadmore.databinding.DetailImageFragmentBinding
import com.example.test_loadmore.ui.base.BaseFragment
import com.example.test_loadmore.utils.FileUtil
import com.example.test_loadmore.utils.convertType
import com.example.test_loadmore.utils.observe
import com.kaopiz.kprogresshud.KProgressHUD
import com.squareup.picasso.Picasso
import dagger.hilt.android.AndroidEntryPoint
import java.io.File

@AndroidEntryPoint
class DetailImageFragment : BaseFragment() {

    val args by navArgs<DetailImageFragmentArgs>()

    lateinit var binding: DetailImageFragmentBinding

    lateinit var o: ArgumentDetailImage

    private var hud: KProgressHUD? = null

    private val viewModel: DetailImageViewModel by viewModels()

    val test: Int by lazy {
        1
    }

    override fun observeViewModel() {
        observe(viewModel.isFavourite, ::handleIsFavorite)
    }

    private fun handleIsFavorite(req: Resource<Boolean>){
        when(req){
            is Resource.Success ->{
                handleIsFavouriteUI(req)
                hud!!.dismiss()
            }

            is Resource.Loading ->{
                hud!!.show()
            }

            is Resource.DataError ->{
                hud!!.dismiss()
            }
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        hud = KProgressHUD.create(requireActivity())
            .setStyle(KProgressHUD.Style.SPIN_INDETERMINATE)


        PRDownloader.initialize(requireActivity().applicationContext);

        requireActivity().window.clearFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);

        o = args.data!!
        viewModel.fetchData(o)

        binding = DetailImageFragmentBinding.inflate(layoutInflater)
        Picasso.get().load(BASE_URL + "image/" + o.id.toString() + ".jpg").fit()
            .into(binding.imgDetailImage, object : com.squareup.picasso.Callback {
                override fun onSuccess() {

                    binding.pbDetailImage.visibility = View.GONE
                }

                override fun onError(e: Exception?) {
                }
            })

        binding.btnDetailCategory.labelText = convertType(o.type)

        binding.layoutDetailImageBack.setOnClickListener {
            view?.let { _view ->
                view?.findNavController()?.navigateUp()
            }
        }

        binding.btnDetailSetWpp.setOnClickListener {


            binding.btnMenuDetail.close(true)
        }

        binding.btnDetailShare.setOnClickListener {
            downLoadImage()

            var filePath = FileUtil(requireContext()).getRootFolder()
            var file = File(filePath + '/' + o.id + ".jpg")

            if (file.exists()) {
                val shareIntent = Intent(Intent.ACTION_SEND)
                shareIntent.type = "image/jpg"
                shareIntent.putExtra(Intent.EXTRA_STREAM, Uri.fromFile(file))
                startActivity(Intent.createChooser(shareIntent, "Share image using"))
            } else {
                Toast.makeText(requireContext(), "Cannot share!", Toast.LENGTH_SHORT).show()
            }
            binding.btnMenuDetail.close(true)

        }

        binding.btnDetailCategory.setOnClickListener {
            var birections =
                DetailImageFragmentDirections.actionDetailImageFragmentToViewAllFragment(
                    ArgumentViewAll(convertType(o.type))
                )
            view?.let { _view ->
                Navigation.findNavController(_view).navigate(birections)
            }
            binding.btnMenuDetail.close(true)
        }

        binding.btnDetailDownLoad.setOnClickListener {
            downLoadImage()
        }

        binding.btnDetailReport.setOnClickListener {
            var dialog = Dialog(requireContext())
            dialog.setCancelable(true)
            dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
            //dialog.window!!.setBackgroundDrawableResource(android.R.color.transparent)
            dialog.setContentView(R.layout.dialog_detail_report)

            var tvReportID = dialog.findViewById<TextView>(R.id.tvReportIDs)
            tvReportID.setText("Report Item ID: " + o.id)

            var sexualLayout = dialog.findViewById<LinearLayout>(R.id.tvReportSexually)
            sexualLayout.setOnClickListener {
                sendEmail("Please briefly explain why you found it Sexually Explicit")
            }


            var offensiveLayout = dialog.findViewById<LinearLayout>(R.id.tvReportOffensive)
            offensiveLayout.setOnClickListener {
                sendEmail("Please briefly explain why you found it Offensive")
            }

            var copyRightedLayout = dialog.findViewById<LinearLayout>(R.id.tvReportCopyRighted)
            copyRightedLayout.setOnClickListener {
                sendEmail("Please briefly explain why you found it Copyrighted")
            }

            var cancelLayout = dialog.findViewById<Button>(R.id.btnCancelReport)
            cancelLayout.setOnClickListener {
                dialog.dismiss()
            }
            dialog.show()
            binding.btnMenuDetail.close(true)
        }

        binding.btnDetailFavorite.setOnClickListener {
            if (viewModel.isFavourite.value?.data == true) {
                viewModel.removeFromFavorite()
            } else {
                viewModel.addFavorite()
            }
        }

        binding.btnDetailPreview.setOnClickListener {

        }
        val view = binding.root
        return view
    }

    private fun handleIsFavouriteUI(isFavorite: Resource<Boolean>){
        if(isFavorite.data == true){
            binding.imgDetailFavorite.setImageDrawable(resources.getDrawable(R.drawable.ic_detail_favorite))
        }
        else{
            binding.imgDetailFavorite.setImageDrawable(resources.getDrawable(R.drawable.ic_detail_unfavorite))
        }
    }

    private fun sendEmail(content: String) {

        val emailIntent = Intent(Intent.ACTION_SEND)
        emailIntent.data = Uri.parse("mailto:")
        emailIntent.setClassName(
            "com.google.android.gm",
            "com.google.android.gm.ConversationListActivity"
        );
        emailIntent.type = "text/plain"
        emailIntent.putExtra(Intent.EXTRA_EMAIL, EMAIL_REPORT)
        emailIntent.putExtra(Intent.EXTRA_SUBJECT, "Report Item ID: " + o.id)
        //  emailIntent.addCategory(Intent.CATEGORY_APP_EMAIL);
        emailIntent.putExtra(Intent.EXTRA_TEXT, content)

        try {
            // startActivity(Intent.createChooser(emailIntent, getString(R.string.open_email_app)))
        } catch (ex: ActivityNotFoundException) {
            Toast.makeText(
                requireContext(),
                "There is no email client installed.",
                Toast.LENGTH_SHORT
            ).show()
        }
    }

    private fun downLoadImage() {

        var filePath = FileUtil(requireContext()).getRootFolder()

        var file = File(filePath + '/' + o.id + ".jpg")

        if (file.exists()) {


        } else {
            var dialog = Dialog(requireContext())

            dialog.setCancelable(false)
            dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
            dialog.window!!.setBackgroundDrawableResource(android.R.color.transparent)
            dialog.setContentView(R.layout.dialog_detail_download)

            var pbDownload = dialog.findViewById<CircleProgressBar>(R.id.pbDetailDownload)

            PRDownloader.download(
                BASE_URL + "image/" + o.id.toString() + ".jpg",
                filePath, o.id.toString() + ".jpg"
            ).build().setOnStartOrResumeListener {
                dialog.show()
            }.setOnPauseListener {
                dialog.dismiss()
            }.setOnCancelListener {
                dialog.dismiss()
            }.setOnProgressListener {
                pbDownload.progress = ((it.currentBytes / it.totalBytes) * 100).toInt()
            }.start(object : OnDownloadListener {
                override fun onDownloadComplete() {
                    dialog.dismiss()
                }

                override fun onError(error: Error?) {
                    Toast.makeText(requireContext(), "Unable to download", Toast.LENGTH_SHORT)
                        .show()
                    dialog.dismiss()
                }
            }
            )
        }
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        binding.btnMenuDetail.close(true)

    }


}
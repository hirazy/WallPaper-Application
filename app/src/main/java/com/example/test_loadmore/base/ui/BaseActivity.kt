package com.example.test_loadmore.base.ui

import android.app.Dialog
import android.os.Bundle
import android.util.Log
import android.view.KeyEvent
import androidx.annotation.NonNull
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.google.gson.Gson
import com.example.test_loadmore.utils.LayoutId
import com.example.test_loadmore.base.widget.DialogError
import com.example.test_loadmore.base.widget.DialogLoading
import pub.devrel.easypermissions.EasyPermissions
import kotlin.jvm.Throws

abstract class BaseActivity<T : ViewDataBinding, K : BaseViewModel> : AppCompatActivity(),
    EasyPermissions.PermissionCallbacks {
    lateinit var binding: T
    lateinit var viewModel: K

    private lateinit var mProgressDialog: Dialog
    private var exitTime: Long = 0
    lateinit var viewModelFactory: ViewModelProvider.Factory

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = DataBindingUtil.setContentView(
            this,
            javaClass.getAnnotation(LayoutId::class.java).value
        )
        binding.lifecycleOwner = this
        initView()

        viewModel = ViewModelProvider(this).get(getVModel()) // init view model
        dialog = DialogLoading(this)
        dialogErr = DialogError(this)
        this.setObserveLive(viewModel);
        start()
    }

    lateinit var dialog: DialogLoading
    lateinit var dialogErr: DialogError

    abstract fun getVModel(): Class<K>


    @Throws(Exception::class)
    fun <T> gsonFromJson(json: String, classOfT: Class<T>): T {
        return try {
            Gson().fromJson(json, classOfT)
        } catch (e: Exception) {
            throw Exception()
        }
    }

    override fun onBackPressed() {
        val event = KeyEvent(KeyEvent.ACTION_DOWN, KeyEvent.KEYCODE_BACK)
        dispatchKeyEvent(event)
    }

    override fun onDestroy() {
        super.onDestroy()
    }


    abstract fun initView()


    abstract fun start()

    override fun dispatchKeyEvent(event: KeyEvent): Boolean {
        if (event.keyCode == KeyEvent.KEYCODE_BACK && event.action == KeyEvent.ACTION_DOWN && event.repeatCount == 0) {
            if (System.currentTimeMillis() - exitTime > 2000) {
                // toast("Press again to exit")
                //Log.e("Press again to exit")
                exitTime = System.currentTimeMillis()
            } else {
                finish()
            }
            return true
        }
        return super.dispatchKeyEvent(event)
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        @NonNull permissions: Array<String>,
        @NonNull grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        EasyPermissions.onRequestPermissionsResult(requestCode, permissions, grantResults, this)
    }

    override fun onPermissionsGranted(requestCode: Int, perms: List<String>) {
        Log.i("EasyPermissions", "Access to Success：$perms")
    }

    override fun onPermissionsDenied(requestCode: Int, perms: List<String>) {
        val sb = StringBuffer()
        for (str in perms) {
            sb.append(str)
            sb.append("\n")
        }
        sb.replace(sb.length - 2, sb.length, "")
        if (EasyPermissions.somePermissionPermanentlyDenied(this, perms)) {
//            AppSettingsDialog.Builder(this)
//                .setRationale(
//                    String.format(
//                        resources.getString(R.string.permission_tip),
//                        sb.toString()
//                    )
//                )
//                .setPositiveButton(R.string.dlg_yes)
//                .setNegativeButton(R.string.dlg_no)
//                .build()
//                .show()
        } else {
            finish()
        }
    }


    protected val TAG = BaseActivity::class.java.simpleName

    fun setObserveLive(viewModel: BaseViewModel) {
        viewModel.eventLoading.observe(this, Observer {
            if (it) {
                dialog.showLoading()
            } else {
                dialog.dismissLoading()
            }

        })

        viewModel.eventError.observe(this, Observer {
            if (it != null) {
                dialogErr.showDialog(it.toString())
            }
        })
    }


}



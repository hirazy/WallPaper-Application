package com.example.test_loadmore.ui.component.settings

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.test_loadmore.ui.base.BaseFragment

class SettingsActivity : BaseFragment() {
    override fun observeViewModel() {

    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return super.onCreateView(inflater, container, savedInstanceState)
    }


    //override fun onCreate(savedInstanceState: Bundle?) {
//
//        if(PrefHelperImpl(application).getData(KEY_SAVE_MODE).equals("")){
//            PrefHelperImpl(application).setData(KEY_SAVE_MODE, LIGHT_MODE)
//        }
//
//        if(PrefHelperImpl(application).getData(KEY_SAVE_MODE) == NIGHT_MODE){
//
//            setTheme(R.style.darkTheme)
//        }
//        else setTheme(R.style.AppTheme)
//
//        super.onCreate(savedInstanceState)
//        setContentView(R.layout.activity_settings)
//        mySwitch.isChecked = PrefHelperImpl(application).getData(KEY_SAVE_MODE) == NIGHT_MODE
//
//
//        mySwitch.setOnCheckedChangeListener { buttonView, isChecked ->
//            if(isChecked){
//                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
//                PrefHelperImpl(application).setData(KEY_SAVE_MODE, NIGHT_MODE)
//            }
//            else{
//                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
//                PrefHelperImpl(application).setData(KEY_SAVE_MODE, LIGHT_MODE)
//            }
//            restartApp()
//        }
    //   }

    fun restartApp() {
//        var intent = Intent(applicationContext, SettingsActivity::class.java)
//        startActivity(intent)
//        finish()
    }
}
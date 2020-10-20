package com.zhboy.keeplivedemo

import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.annotation.RequiresApi
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), View.OnClickListener {
    @RequiresApi(Build.VERSION_CODES.M)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        joinWhiteOsBtn.setOnClickListener(this)
        allowBackRunBtn.setOnClickListener(this)
    }

    @RequiresApi(Build.VERSION_CODES.M)
    override fun onClick(view: View?) {
        when (view?.id) {
            R.id.joinWhiteOsBtn -> {
                if (!KeepLiveUtils.isIgnoringBatteryOptimizations(this@MainActivity)) {
                    KeepLiveUtils.requestIgnoreBatteryOptimizations(this@MainActivity)
                }
            }
            R.id.allowBackRunBtn -> {
                KeepLiveUtils.goKeepLiveSetting(this@MainActivity)
            }
        }
    }
}
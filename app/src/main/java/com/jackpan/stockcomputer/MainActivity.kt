package com.jackpan.stockcomputer

import android.app.Activity
import android.os.Bundle
import android.widget.Toast
import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.AdView
import com.google.android.gms.ads.MobileAds
import com.wuhenzhizao.titlebar.widget.CommonTitleBar

class MainActivity : Activity() {
    lateinit var titleBar : CommonTitleBar
    lateinit var mAdView : AdView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        titleBar = findViewById(R.id.titlebar)
        setTitleBar()
        MobileAds.initialize(this) {}
        mAdView = findViewById(R.id.adView)
        val adRequest = AdRequest.Builder().build()
        mAdView.loadAd(adRequest)
    }

    fun setTitleBar(){
        titleBar.setListener { v, action, extra ->
            when(action){
                CommonTitleBar.ACTION_LEFT_TEXT -> Toast.makeText(this,"1",Toast.LENGTH_SHORT).show()
                CommonTitleBar.ACTION_RIGHT_TEXT -> Toast.makeText(this,"2",Toast.LENGTH_SHORT).show()

            }

        }

    }
}

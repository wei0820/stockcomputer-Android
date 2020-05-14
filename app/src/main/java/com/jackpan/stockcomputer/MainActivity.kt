package com.jackpan.stockcomputer

import Manager.FirebaseDatebaseManager
import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.widget.Toast

import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.AdView
import com.google.android.gms.ads.MobileAds
import com.wuhenzhizao.titlebar.widget.CommonTitleBar


class MainActivity : Activity() {
    lateinit var titleBar : CommonTitleBar
    lateinit var mAdView : AdView
    lateinit var MarqueeTextView :com.xiaweizi.marquee.MarqueeTextView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        titleBar = findViewById(R.id.titlebar)
        MarqueeTextView = findViewById(R.id.marquee1)
        setTitleBar()
        MobileAds.initialize(this) {}
        mAdView = findViewById(R.id.adView)
        val adRequest = AdRequest.Builder().build()
        mAdView.loadAd(adRequest)
        FirebaseDatebaseManager.getFirebaseDatebase(MarqueeTextView)
     

    }

    fun setTitleBar(){
        titleBar.setListener { v, action, extra ->
            when(action){
                CommonTitleBar.ACTION_LEFT_TEXT ->
                    startActivity(Intent(this,MemberCenterActivity::class.java))
                CommonTitleBar.ACTION_RIGHT_TEXT -> Toast.makeText(this,"2",Toast.LENGTH_SHORT).show()

            }

        }

    }
}

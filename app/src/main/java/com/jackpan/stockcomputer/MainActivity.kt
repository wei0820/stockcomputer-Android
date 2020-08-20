package com.jackpan.stockcomputer

import Manager.FirebaseDatebaseManager
import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.RelativeLayout
import android.widget.Toast

import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.AdView
import com.google.android.gms.ads.MobileAds
import com.wuhenzhizao.titlebar.widget.CommonTitleBar
import com.lw.banner.Banner


class MainActivity : Activity(), View.OnClickListener
    {

        lateinit var titleBar : CommonTitleBar
        lateinit var mAdView : AdView
        lateinit var MarqueeTextView :com.xiaweizi.marquee.MarqueeTextView
        lateinit var mXBanner : Banner
        lateinit var mBottomBar: com.roughike.bottombar.BottomBar

        lateinit var mLayout: RelativeLayout
        lateinit var mLayout2: RelativeLayout
        lateinit var mLayout3: RelativeLayout
        lateinit var mLayout4: RelativeLayout
        lateinit var mLayout5: RelativeLayout
        lateinit var mLayout6: RelativeLayout


        var mArray  = arrayListOf<String>()
            override fun onCreate(savedInstanceState: Bundle?) {
                super.onCreate(savedInstanceState)
                setContentView(R.layout.activity_main)
                initLayout()
                setTitleBar()
                setAd()
                FirebaseDatebaseManager.getFirebaseDatebase(MarqueeTextView)
                FirebaseDatebaseManager.getBannerData(mXBanner)

            }



    fun initLayout(){
        mLayout = findViewById(R.id.layout_1)
        mLayout2 = findViewById(R.id.layout_2)
        mLayout3 = findViewById(R.id.layout_3)
        mLayout4 = findViewById(R.id.layout_4)
        mLayout5 = findViewById(R.id.layout_5)
        mLayout6 = findViewById(R.id.layout_6)
        mLayout.setOnClickListener(this)
        mLayout2.setOnClickListener(this)
        mLayout3.setOnClickListener(this)
        mLayout4.setOnClickListener(this)
        mLayout5.setOnClickListener(this)
        mLayout6.setOnClickListener(this)

        titleBar = findViewById(R.id.titlebar)
        mXBanner = findViewById(R.id.banner)
        MarqueeTextView = findViewById(R.id.marquee1)
        mBottomBar = findViewById(R.id.bottomBar)
        UIManager.SetBottomBar(mBottomBar,this)

    }
        fun setAd(){
            MobileAds.initialize(this) {}
            mAdView = findViewById(R.id.adView)
            val adRequest = AdRequest.Builder().build()
            mAdView.loadAd(adRequest)
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
        override fun onClick(p0: View?) {
            when(p0!!.id){
                R.id.layout_1 ->startActivity(Intent(this,TomorrowActivity::class.java
                ))
                R.id.layout_2 ->startActivity(Intent(this,FinancingActivity::class.java))
                R.id.layout_3 ->startActivity(Intent(this,MarginTradingActivity::class.java))
                R.id.layout_4 ->""
                R.id.layout_5 ->startActivity(Intent(this,StockTrackActivity::class.java))
                R.id.layout_6 ->startActivity(Intent(this,SelectPageActivity::class.java))

            }


        }


    }

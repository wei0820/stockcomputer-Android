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
import android.R.attr.banner
import android.R.attr.banner
import android.util.Log
import com.google.android.gms.tasks.OnCompleteListener
import com.google.firebase.iid.FirebaseInstanceId
import com.lw.banner.Banner


        class MainActivity : Activity() {
            lateinit var titleBar : CommonTitleBar
            lateinit var mAdView : AdView
            lateinit var MarqueeTextView :com.xiaweizi.marquee.MarqueeTextView
            lateinit var mXBanner : Banner
            var mArray  = arrayListOf<String>()
            override fun onCreate(savedInstanceState: Bundle?) {
                super.onCreate(savedInstanceState)
                setContentView(R.layout.activity_main)
                titleBar = findViewById(R.id.titlebar)
                mXBanner = findViewById(R.id.banner)
                MarqueeTextView = findViewById(R.id.marquee1)
                setTitleBar()
                MobileAds.initialize(this) {}
                mAdView = findViewById(R.id.adView)
                val adRequest = AdRequest.Builder().build()
                mAdView.loadAd(adRequest)
                FirebaseDatebaseManager.getFirebaseDatebase(MarqueeTextView)
                FirebaseDatebaseManager.getBannerData(mXBanner)


                FirebaseInstanceId.getInstance().instanceId
                        .addOnCompleteListener(OnCompleteListener { task ->
                            if (!task.isSuccessful) {
                                Log.d("Jack", "getInstanceId failed", task.exception)
                                return@OnCompleteListener
                            }

                            // Get new Instance ID token
                            val token = task.result?.token

                            // Log and toast
                            Log.d("Jack",  task.result?.token.toString())
                        })

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

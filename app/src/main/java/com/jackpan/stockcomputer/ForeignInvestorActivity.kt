package com.jackpan.stockcomputer

import android.os.Bundle
import com.felix.bottomnavygation.BottomNav
import com.felix.bottomnavygation.ItemNav
import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.AdView
import com.google.android.gms.ads.MobileAds
import android.widget.Toast



class ForeignInvestorActivity : BaseActivity() {
    lateinit var mAdView : AdView
    lateinit var mBottom : BottomNav

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_foreign_investor)
        initLayout()
    }
    fun initLayout(){
        MobileAds.initialize(this) {}
        mAdView = findViewById(R.id.adView)
        val adRequest = AdRequest.Builder().build()
        mAdView.loadAd(adRequest)

        mBottom = findViewById(R.id.bottomNav)
        mBottom.addItemNav(ItemNav(this,R.mipmap.icon,"外資買超前50名"))
        mBottom.addItemNav(ItemNav(this,R.mipmap.icon,"外資賣超前50名"))
        mBottom.build()
        var listener = object : BottomNav.OnTabSelectedListener {
            override fun onTabSelected(position: Int) {
            }

            override fun onTabLongSelected(position: Int) {
            }
        }
        mBottom.setTabSelectedListener(listener)


    }
}

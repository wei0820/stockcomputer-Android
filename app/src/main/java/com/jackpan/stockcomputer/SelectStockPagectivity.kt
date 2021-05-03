package com.jackpan.stockcomputer

import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.AdView
import com.google.android.gms.ads.MobileAds
import kotlinx.android.synthetic.main.activity_select_stock_pagectivity.*
import java.util.ArrayList
class SelectStockPagectivity : BaseActivity() {
    var stockPageList = ArrayList<String>()
    lateinit var mAdView: AdView


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_select_stock_pagectivity)
        getList()
        setAd()
        recycler_view.layoutManager = LinearLayoutManager(this)

        recycler_view.adapter = RecycleViewAdapter(stockPageList)



    }
    fun getList(){
        stockPageList.add("股利排行")
        stockPageList.add("現金殖利率")
        stockPageList.add("PER排行")
        stockPageList.add("獲利排行")
        stockPageList.add("由虧轉盈")
        stockPageList.add("獲利創新高")
        stockPageList.add("年度ROE")
        stockPageList.add("年度ROA")
        stockPageList.add("年度EPS")

    }
    fun setAd() {
        MobileAds.initialize(this) {}
        mAdView = findViewById(R.id.adView)
        val adRequest = AdRequest.Builder().build()
        mAdView.loadAd(adRequest)


    }
}
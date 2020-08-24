package com.jackpan.stockcomputer

import android.content.Intent
import android.os.Bundle
import android.widget.ListView
import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.AdView
import com.google.android.gms.ads.MobileAds
import com.facebook.internal.Utility.arrayList
import android.widget.ArrayAdapter
import android.widget.ListAdapter


class SelectPageActivity : BaseActivity() {
    lateinit var mAdView : AdView
    var mListview: ListView? = null
    var arrayList:ArrayList<String> = ArrayList()
    var adapter: ListAdapter? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_select_page)
        setAd()
        arrayList.add("法人籌碼")
        arrayList.add("外資買賣超前50名")
        arrayList.add("投信買賣超前50名")
        arrayList.add("自營商買賣超前50名")
        arrayList.add("八大官股賣賣超")
        arrayList.add("融資券借券排行")


        mListview = findViewById(R.id.listview)
        adapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, arrayList)
        mListview!!.setAdapter(adapter);
        mListview!!.setOnItemClickListener { adapterView, view, i, l ->
            when(i){
                0 -> startActivity(Intent(this,ThreeActivity::class.java))
                1 -> startActivity(Intent(this,ForeignInvestorActivity::class.java))
                2 -> startActivity(Intent(this,TrustActivity::class.java))
                3 -> startActivity(Intent(this,DealerActivity::class.java))
                4 -> startActivity(Intent(this,BrokerActivity::class.java))



            }


        }



    }
    fun setAd(){
        MobileAds.initialize(this) {}
        mAdView = findViewById(R.id.adView)
        val adRequest = AdRequest.Builder().build()
        mAdView.loadAd(adRequest)
    }
}

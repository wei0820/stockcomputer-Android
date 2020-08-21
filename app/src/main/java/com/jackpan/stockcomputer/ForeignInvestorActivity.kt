package com.jackpan.stockcomputer

import Data.StockData
import android.app.ProgressDialog
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import com.felix.bottomnavygation.BottomNav
import com.felix.bottomnavygation.ItemNav
import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.AdView
import com.google.android.gms.ads.MobileAds
import org.jsoup.Jsoup
import java.io.IOException


class ForeignInvestorActivity : BaseActivity() {
    lateinit var mAdView : AdView
    lateinit var mBottom : BottomNav
    var mListview: ListView? = null
    var arrayList:ArrayList<String> = ArrayList()
    var mAdapter: MyAdapter? = null
    lateinit var mProgressDialog : ProgressDialog

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_foreign_investor)
        initLayout()
        getForeignBuy()
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
                when(position){
                    0 -> getForeignBuy()
                    1 -> getForeignSell()
                }
            }

            override fun onTabLongSelected(position: Int) {
            }
        }
        mBottom.setTabSelectedListener(listener)
        mListview = findViewById(R.id.listview)

    }
    private fun getForeignBuy() {
        mProgressDialog = ProgressDialog(this)

        mProgressDialog.setMessage("讀取中")
        mProgressDialog.setCancelable(false)
        mProgressDialog.show()
        arrayList.clear()
        object : Thread() {
            override fun run() {
                super.run()
                try {
                    val doc = Jsoup.connect("https://histock.tw/stock/three.aspx?s=a").get()
                    for (element in doc.select("div.tb-outline.outline1>ul.stock-list>li")) {
                        for (td in element.select("span.w58.name")) {
                            if(!td.text().isEmpty()){
                                arrayList.add(td.text())

                            }
                            runOnUiThread {
                                mAdapter = MyAdapter(arrayList)

                                mListview!!.adapter = mAdapter
                                mAdapter!!.notifyDataSetChanged()
                                mProgressDialog.dismiss()


                            }


                        }

                    }

                } catch (e: IOException) {
                    e.printStackTrace()
                }

            }
        }.start()
    }
    private fun getForeignSell() {
        mProgressDialog = ProgressDialog(this)

        mProgressDialog.setMessage("讀取中")
        mProgressDialog.setCancelable(false)
        mProgressDialog.show()
        arrayList.clear()

        object : Thread() {
            override fun run() {
                super.run()
                try {
                    val doc = Jsoup.connect("https://histock.tw/stock/three.aspx?s=a").get()
                    for (element in doc.select("div.tb-outline.outline2>ul.stock-list>li")) {
                        for (td in element.select("span.w58.name")) {
                            if(!td.text().isEmpty()){
                                Log.d("Jack",td.text())
                                arrayList.add(td.text())
                            }
                            runOnUiThread {

                                mAdapter = MyAdapter(arrayList)
                                mListview!!.adapter = mAdapter
                                mAdapter!!.notifyDataSetChanged()
                                mProgressDialog.dismiss()


                            }


                        }

                    }

                } catch (e: IOException) {
                    e.printStackTrace()
                }

            }
        }.start()
    }

    inner class MyAdapter(var mDatas: ArrayList<String>?) : BaseAdapter() {

        fun updateData(datas: ArrayList<String>) {
            mDatas = datas
            notifyDataSetChanged()
        }

        override fun getCount(): Int {
            return mDatas!!.size
        }

        override fun getItem(position: Int): Any {
            return mDatas!![position]
        }

        override fun getItemId(position: Int): Long {
            return position.toLong()
        }

        override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
            var convertView = convertView
            if (convertView == null)
                convertView = LayoutInflater.from(this@ForeignInvestorActivity).inflate(
                        R.layout.mylayout, null)
            val data = mDatas!![position]
            val textname = convertView!!.findViewById(R.id.name) as TextView
            val list = convertView!!.findViewById(R.id.txtengname) as TextView
            textname.text = (position+1).toString() +"."+ data


            return convertView
        }

    }

}

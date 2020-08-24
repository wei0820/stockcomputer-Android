package com.jackpan.stockcomputer

import android.app.ProgressDialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.ListView
import android.widget.TextView
import com.felix.bottomnavygation.BottomNav
import com.felix.bottomnavygation.ItemNav
import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.AdView
import com.google.android.gms.ads.MobileAds
import org.jsoup.Jsoup
import java.io.IOException

class TrustActivity : BaseActivity() {
    lateinit var mAdView : AdView
    lateinit var mBottom : BottomNav
    var mListview: ListView? = null
    var arrayList:ArrayList<String> = ArrayList()
    var mAdapter: MyAdapter? = null
    lateinit var mProgressDialog : ProgressDialog
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_trust)
        initLayout()
        getTrustBuy()
    }
    fun initLayout(){
        MobileAds.initialize(this) {}
        mAdView = findViewById(R.id.adView)
        val adRequest = AdRequest.Builder().build()
        mAdView.loadAd(adRequest)

        mBottom = findViewById(R.id.bottomNav)
        mBottom.addItemNav(ItemNav(this,R.mipmap.icon,"投信買超前50名"))
        mBottom.addItemNav(ItemNav(this,R.mipmap.icon,"投信賣超前50名"))
        mBottom.build()
        var listener = object : BottomNav.OnTabSelectedListener {
            override fun onTabSelected(position: Int) {
                when(position){
                    0 -> getTrustBuy()

                    1 -> getTrustSell()
                }
            }

            override fun onTabLongSelected(position: Int) {
            }
        }
        mBottom.setTabSelectedListener(listener)
        mListview = findViewById(R.id.listview)

    }
    private fun getTrustBuy() {
        mProgressDialog = ProgressDialog(this)

        mProgressDialog.setMessage("讀取中")
        mProgressDialog.setCancelable(false)
        mProgressDialog.show()
        arrayList.clear()
        object : Thread() {
            override fun run() {
                super.run()
                try {
                    val doc = Jsoup.connect("https://histock.tw/stock/three.aspx?s=b").get()
                    for (element in doc.select("div.tb-outline.outline1>ul.stock-list>li")) {
                        for (td in element.select("span.w58.name")) {

                            runOnUiThread {
                                if(!td.text().isEmpty()){
                                    arrayList.add(td.text())

                                }
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
    private fun getTrustSell() {
        mProgressDialog = ProgressDialog(this)

        mProgressDialog.setMessage("讀取中")
        mProgressDialog.setCancelable(false)
        mProgressDialog.show()
        arrayList.clear()

        object : Thread() {
            override fun run() {
                super.run()
                try {
                    val doc = Jsoup.connect("https://histock.tw/stock/three.aspx?s=b").get()
                    for (element in doc.select("div.tb-outline.outline2>ul.stock-list>li")) {
                        for (td in element.select("span.w58.name")) {

                            runOnUiThread {
                                if(!td.text().isEmpty()){
                                    arrayList.add(td.text())
                                }

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
                convertView = LayoutInflater.from(this@TrustActivity).inflate(
                        R.layout.mylayout, null)
            val data = mDatas!![position]
            val textname = convertView!!.findViewById(R.id.name) as TextView
            val list = convertView!!.findViewById(R.id.txtengname) as TextView
            textname.text = (position+1).toString() +"."+ data


            return convertView
        }

    }
}

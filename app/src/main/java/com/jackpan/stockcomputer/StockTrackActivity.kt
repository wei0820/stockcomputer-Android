package com.jackpan.stockcomputer

import Data.StockData
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.ArrayAdapter
import android.widget.ListAdapter
import android.widget.ListView
import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.AdView
import com.google.android.gms.ads.MobileAds
import org.jsoup.Jsoup
import java.io.IOException
import android.widget.TextView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter





class StockTrackActivity : BaseActivity() {
    lateinit var mAdView : AdView
    var mListview: ListView? = null
    var arrayList:ArrayList<StockData> = ArrayList()
    var mAdapter: MyAdapter? = null
    var split : List<String>? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_stock_track)
        setAd()



        mListview = findViewById(R.id.listview)

        get()



        mListview!!.setOnItemClickListener { adapterView, view, i, l ->


        }
    }

    fun setAd(){
        MobileAds.initialize(this) {}
        mAdView = findViewById(R.id.adView)
        val adRequest = AdRequest.Builder().build()
        mAdView.loadAd(adRequest)
    }
    private fun get() {
        object : Thread() {
            override fun run() {
                super.run()
                try {
                    val doc = Jsoup.connect("https://histock.tw/app/table.aspx").get()
                    for (element in doc.select("table.tb-stock.tb-link>tbody")) {
                        for (td in element.select("tr")) {
                            var stockData = StockData()

                            Log.d("Jack",td.text())
                            stockData.setDate(td.text())
//                            stockData.setDate(split!![1])
                            arrayList.add(stockData)

                        }

                        Log.d("Jack",arrayList.size.toString())
                        arrayList.removeAt(0)
                        runOnUiThread {
                            mAdapter = MyAdapter(arrayList)

                            mListview!!.adapter = mAdapter
                            mAdapter!!.notifyDataSetChanged()
                        }


                    }

                } catch (e: IOException) {
                    e.printStackTrace()
                }

            }
        }.start()
    }

    inner class MyAdapter(private var mDatas: ArrayList<StockData>?) : BaseAdapter() {
        fun updateData(datas: ArrayList<StockData>) {
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
                convertView = LayoutInflater.from(this@StockTrackActivity).inflate(
                        R.layout.mylayout, null)
            val data = mDatas!![position]
            val textname = convertView!!.findViewById(R.id.name) as TextView
            val list = convertView!!.findViewById(R.id.txtengname) as TextView
            textname.text = data.date.split(" ")[0]
            list.text = data.date.split(" ")[1]


            return convertView
        }

    }
}

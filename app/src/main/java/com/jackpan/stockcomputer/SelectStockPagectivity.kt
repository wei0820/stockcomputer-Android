package com.jackpan.stockcomputer

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.LinearLayoutManager
import com.jackpan.stockcomputer.databinding.ActivityStockEpsListBinding
import kotlinx.android.synthetic.main.activity_select_stock_pagectivity.*
import java.util.ArrayList
class SelectStockPagectivity : AppCompatActivity() {
    var stockPageList = ArrayList<String>()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_select_stock_pagectivity)
        getList()
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
}
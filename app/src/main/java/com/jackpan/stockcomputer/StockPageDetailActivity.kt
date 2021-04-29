package com.jackpan.stockcomputer

import android.os.Bundle
import android.util.Log

class StockPageDetailActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_stock_page_detail)
        getData()

    }

    fun getData(){
        val json = intent.getStringExtra("json")
        Log.d("Jack",json)

    }
}
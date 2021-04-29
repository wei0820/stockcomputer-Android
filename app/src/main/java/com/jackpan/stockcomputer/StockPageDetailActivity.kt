package com.jackpan.stockcomputer

import android.os.Bundle
import android.util.Log
import kotlinx.android.synthetic.main.activity_stock_page_detail.*

class StockPageDetailActivity : BaseActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_stock_page_detail)
        getData()

    }

    fun getData(){
        val json = intent.getStringExtra("json")
        detailtext.text = json.substring(1,json.length-1).replace(",","\n")
            .replace("\"", " ");



    }
}
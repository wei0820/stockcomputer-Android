package com.jackpan.stockcomputer

import android.os.Bundle
import android.util.Log
import android.widget.TextView

class StockTrackDetailActivity : BaseActivity() {
    lateinit var mTextView: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_stock_track_detail)
        mTextView = findViewById(R.id.text)
        getData()
    }

    fun getData(){
        var  detail : String = getIntent().getStringExtra("detail")

        mTextView.text = detail
    }
}

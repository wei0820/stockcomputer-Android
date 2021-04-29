package com.jackpan.stockcomputer

import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.gson.Gson
import com.jackpan.stockcomputer.model.StockEpsListData
import com.jackpan.stockcomputer.viewmodel.StockEpsListViewModel
import kotlinx.android.synthetic.main.activity_stock_eps_list.*


class StockEpsListActivity : BaseActivity() {
    val  stockEpsListViewModel  : StockEpsListViewModel by viewModels()
    val  mData = mutableListOf<StockEpsListData>()
     val adapter = StockSelectAsapter(this,R.layout.base_recycler,mData)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_stock_eps_list)
        initObsever()
        stockEpsListViewModel.getStockEpsListData()
        adapter.setOnItemClickListener {
            val petint = Intent(this, StockPageDetailActivity::class.java)
            petint.putExtra("json", Gson().toJson(adapter.mData.get(it)))
            startActivity(petint)
        }
    }


    fun initObsever(){
        stockEpsListViewModel.also {
            it.stockEpsList.observe(this, Observer {
                it.forEach {
                    mData.add(it)

                    epsrecycleview.layoutManager = LinearLayoutManager(this)
                    epsrecycleview.adapter = adapter
                }
            })
        }

    }
}
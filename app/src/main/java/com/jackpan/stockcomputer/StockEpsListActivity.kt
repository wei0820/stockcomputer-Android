package com.jackpan.stockcomputer

import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.recyclerview.widget.LinearLayoutManager
import com.jackpan.stockcomputer.databinding.ActivityStockEpsListBinding
import com.jackpan.stockcomputer.model.StockEpsListData
import com.jackpan.stockcomputer.viewmodel.StockEpsListViewModel
import dagger.hilt.android.AndroidEntryPoint
import retrofit2.Retrofit
import javax.inject.Inject
import kotlinx.android.synthetic.main.activity_stock_eps_list.*

class StockEpsListActivity : BaseActivity() {
    val  stockEpsListViewModel  : StockEpsListViewModel by viewModels()
    val  mData = mutableListOf<StockEpsListData>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_stock_eps_list)
        initObsever()
        stockEpsListViewModel.getStockEpsListData()

    }


    fun initObsever(){
        stockEpsListViewModel.also {
            it.stockEpsList.observe(this, Observer {
                it.forEach {
                    mData.add(it)

                    val adapter = EpsAsapter(this,R.layout.base_recycler,mData)
                    epsrecycleview.layoutManager = LinearLayoutManager(this)
                    epsrecycleview.adapter = adapter
                }
            })
        }

    }
}
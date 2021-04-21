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
import com.jackpan.stockcomputer.databinding.ActivityStockEpsListBinding
import com.jackpan.stockcomputer.viewmodel.StockEpsListViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class StockEpsListActivity : AppCompatActivity() {
    val  stockEpsListViewModel  : StockEpsListViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = DataBindingUtil.setContentView<ActivityStockEpsListBinding>(this, R.layout.activity_stock_eps_list)
        binding.lifecycleOwner = this
        binding.stockEpsListViewModel = stockEpsListViewModel
        initObsever()
        stockEpsListViewModel.getStockEpsListData()

    }


    fun initObsever(){
        stockEpsListViewModel.also {
            it.stockEpsList.observe(this, Observer {
                Log.d("Jack",it.get(0).name)
            })
        }

    }
}
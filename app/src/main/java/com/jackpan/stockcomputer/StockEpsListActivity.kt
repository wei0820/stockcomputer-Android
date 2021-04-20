package com.jackpan.stockcomputer

import android.os.Bundle
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.jackpan.stockcomputer.databinding.ActivityStockEpsListBinding

class StockEpsListActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = DataBindingUtil.setContentView<ActivityStockEpsListBinding>(this, R.layout.activity_stock_eps_list)
    }
}
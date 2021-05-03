package com.jackpan.stockcomputer

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import java.util.ArrayList
import kotlinx.android.synthetic.main.item_recycler.view.*
import javax.inject.Inject

class RecycleViewAdapter constructor(var item: ArrayList<String>) : RecyclerView.Adapter<RecycleViewAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_recycler, parent, false))



    }



    override fun getItemCount(): Int {
        return item.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(item.get(position), position)
    }

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        @Inject lateinit var app :MyApp
        val stockname = view.stock_name
        val layout = view.stocklayout
        fun bind(item: String, position: Int) {
            stockname.text = item
            layout.setOnClickListener {
                Toast.makeText(it.context, item, Toast.LENGTH_SHORT).show()
                when (position) {
                    0 -> it.context.startActivity(Intent(it.context,StockEpsListActivity::class.java))
                    2 -> it.context.startActivity(Intent(it.context,StockEpsListActivity::class.java))
                    3 -> it.context.startActivity(Intent(it.context,StockEpsListActivity::class.java))
                    4 -> it.context.startActivity(Intent(it.context,StockEpsListActivity::class.java))
                    5 -> it.context.startActivity(Intent(it.context,StockEpsListActivity::class.java))
                    6 -> it.context.startActivity(Intent(it.context,StockEpsListActivity::class.java))
                    7 -> it.context.startActivity(Intent(it.context,StockEpsListActivity::class.java))
                    8 -> it.context.startActivity(Intent(it.context,StockEpsListActivity::class.java))

                }
            }
        }

    }


}
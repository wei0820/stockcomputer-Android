package com.jackpan.stockcomputer

import android.content.Context
import android.widget.TextView
import com.jackpan.stockcomputer.model.StockEpsListData

class EpsAsapter (context: Context,layout :Int ,mData :MutableList<StockEpsListData>) :BaseStockAdapter<StockEpsListData>(context,layout,mData){
    override fun convert(holder: BaseHolder, position: Int) {

        var rankTextView = holder.getView<TextView>(R.id.stockrank)
        rankTextView.text = this.mData[position].rank
        var numberText  = holder.getView<TextView>(R.id.stocknumber)
        numberText.text = this.mData[position].codename
        var nameText  = holder.getView<TextView>(R.id.stockname)
        nameText.text = this.mData[position].name
    }
    



}
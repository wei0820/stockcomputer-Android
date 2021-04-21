package com.jackpan.stockcomputer.viewmodel

import android.app.Activity
import android.app.Application
import android.util.Log
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.google.firebase.database.*
import com.google.gson.Gson
import com.jackpan.stockcomputer.model.StockEpsListData
import kotlinx.coroutines.launch
import manager.FirebaseDatebaseManager

class StockEpsListViewModel @ViewModelInject  constructor(application: Application, val  activity: Activity):AndroidViewModel(application){
    val stockEpsList = MutableLiveData<List<StockEpsListData>>()
      var mDatabase : DatabaseReference? = null

    fun getStockEpsListData() = viewModelScope.launch {
        mDatabase =
            FirebaseDatabase.getInstance().reference.child("stockepslist")
        val postListener: ValueEventListener = object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                // Get Post object and use the values to update the UI
                var list = ArrayList<StockEpsListData>()
                for (datas in dataSnapshot.children) {
                    val gson = Gson()
                    val stockEpsListData = gson.fromJson(
                        datas.value.toString(),
                        StockEpsListData::class.java
                    )
                    list.add(stockEpsListData)
                }
                stockEpsList.postValue(list)


                // ...
            }

            override fun onCancelled(databaseError: DatabaseError) {
                // Getting Post failed, log a message
                // ...
            }
        }
    mDatabase!!.addValueEventListener(postListener)
    }

}
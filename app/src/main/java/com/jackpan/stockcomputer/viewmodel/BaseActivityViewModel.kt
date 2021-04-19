package com.jackpan.stockcomputer.viewmodel

import android.app.Application
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.AndroidViewModel
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class BaseActivityViewModel @ViewModelInject constructor(application: Application) : AndroidViewModel(application){



    fun checkLoginSatae(){
        val user = Firebase.auth.currentUser
        if(user!=null){


        }else{

        }
    }

}
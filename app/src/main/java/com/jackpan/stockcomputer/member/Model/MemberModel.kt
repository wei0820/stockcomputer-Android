package com.jackpan.stockcomputer.member.Model
import android.app.Application
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.jackpan.stockcomputer.Model.PhoneLogin
import kotlinx.coroutines.launch


class MemberModel @ViewModelInject constructor(application: Application) : AndroidViewModel(application){

    val login  = MutableLiveData<PhoneLogin>()



    fun userPhoneLogin() = viewModelScope.launch {



    }

}
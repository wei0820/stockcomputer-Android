package com.jackpan.stockcomputer.member.Model
import android.app.Activity
import android.app.Application
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.google.firebase.FirebaseException
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.PhoneAuthCredential
import com.google.firebase.auth.PhoneAuthOptions
import com.google.firebase.auth.PhoneAuthProvider
import com.jackpan.stockcomputer.Model.PhoneLogin
import kotlinx.coroutines.launch
import java.util.concurrent.TimeUnit


class MemberModel @ViewModelInject constructor(application: Application) : AndroidViewModel(application){

    val login  = MutableLiveData<PhoneLogin>()



    fun userPhoneLogin(auth:FirebaseAuth,phoneNumber :String,activity: Activity) = viewModelScope.launch {
        val options = PhoneAuthOptions.newBuilder(auth)
            .setPhoneNumber(phoneNumber)       // Phone number to verify
            .setTimeout(60L, TimeUnit.SECONDS) // Timeout and unit
            .setActivity(activity)                 // Activity (for callback binding)
            .setCallbacks(object  :PhoneAuthProvider.OnVerificationStateChangedCallbacks(){
                override fun onVerificationCompleted(p0: PhoneAuthCredential) {
                }

                override fun onVerificationFailed(p0: FirebaseException) {
                }

            })          // OnVerificationStateChangedCallbacks
            .build()
        PhoneAuthProvider.verifyPhoneNumber(options)





    }

}
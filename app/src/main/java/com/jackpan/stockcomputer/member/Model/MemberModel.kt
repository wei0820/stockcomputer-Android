package com.jackpan.stockcomputer.member.Model
import android.app.Activity
import android.app.Application
import android.content.ContentValues.TAG
import android.util.Log
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.google.firebase.FirebaseException
import com.google.firebase.auth.*
import com.jackpan.stockcomputer.Model.GetSMSCode
import kotlinx.coroutines.launch
import java.util.concurrent.TimeUnit
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException
import com.google.firebase.auth.PhoneAuthCredential
import com.google.firebase.auth.PhoneAuthOptions
import com.google.firebase.auth.PhoneAuthProvider
import com.jackpan.stockcomputer.Model.UserData

class MemberModel @ViewModelInject constructor(application: Application) : AndroidViewModel(application){

    val getSMSCode  = MutableLiveData<GetSMSCode>()
    val useData  = MutableLiveData<UserData>()
    var verificationIdString : String = ""




    fun getPhoneSMScode(auth:FirebaseAuth, phoneNumber :String, activity: Activity) = viewModelScope.launch {

        val options = PhoneAuthOptions.newBuilder(auth)
            .setPhoneNumber(phoneNumber)       // Phone number to verify
            .setTimeout(60L, TimeUnit.SECONDS) // Timeout and unit
            .setActivity(activity)                 // Activity (for callback binding)
            .setCallbacks(object  :PhoneAuthProvider.OnVerificationStateChangedCallbacks(){
                override fun onVerificationCompleted(credential: PhoneAuthCredential) {
                    Log.d("Jack", "onVerificationCompleted:$credential")

                }

                override fun onVerificationFailed(e: FirebaseException) {
                    getSMSCode.postValue(GetSMSCode(false))


                }
                override fun onCodeSent(
                    verificationId: String,
                    token: PhoneAuthProvider.ForceResendingToken
                ) {
                    // The SMS verification code has been sent to the provided phone number, we
                    // now need to ask the user to enter the code and then construct a credential
                    // by combining the code with a verification ID.
                    Log.d("Jack", "onCodeSent:$verificationId")
                    getSMSCode.postValue(GetSMSCode(true))
                    verificationIdString =  verificationId


                }

            })          // OnVerificationStateChangedCallbacks
            .build()
        PhoneAuthProvider.verifyPhoneNumber(options)
    }


    fun verifyPhoneNumberWithCode(auth:FirebaseAuth,activity: Activity, code: String){
        val credential = PhoneAuthProvider.getCredential(verificationIdString, code)
        auth.signInWithCredential(credential)
            .addOnCompleteListener(activity) { task ->
                if (task.isSuccessful) {
                    // Sign in success, update UI with the signed-in user's information
                    val user = task.result?.user
                } else {
                    // Sign in failed, display a message and update the UI
                    if (task.exception is FirebaseAuthInvalidCredentialsException) {
                        // The verification code entered was invalid
                    }
                    // Update UI
                }
            }


    }



}
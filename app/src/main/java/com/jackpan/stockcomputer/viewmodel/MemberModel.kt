package com.jackpan.stockcomputer.viewmodel
import android.app.Activity
import android.app.Application
import android.util.Log
import android.widget.Toast
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.google.firebase.FirebaseException
import com.jackpan.stockcomputer.model.GetSMSCode
import kotlinx.coroutines.launch
import java.util.concurrent.TimeUnit
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException
import com.google.firebase.auth.PhoneAuthCredential
import com.google.firebase.auth.PhoneAuthOptions
import com.google.firebase.auth.PhoneAuthProvider
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import com.jackpan.stockcomputer.model.DeleteUserData
import com.jackpan.stockcomputer.model.UserData
import manager.TimeManager
import manager.checkIsNull

class MemberModel @ViewModelInject constructor(application: Application, private val activity: Activity) : AndroidViewModel(application){

    val getSMSCode  = MutableLiveData<GetSMSCode>()
    val useData  = MutableLiveData<UserData>()
    var verificationIdString : String = ""
    val deleteUserData = MutableLiveData<DeleteUserData>()
    lateinit var auth: FirebaseAuth
    var optCode : String = ""
    var phoneNumber : String = ""
    var isCheck : Boolean = false


    fun getPhoneSMScode(phoneNumber :String) = viewModelScope.launch {

        auth = FirebaseAuth.getInstance()
        auth.setLanguageCode("zh-TW")

        val options = PhoneAuthOptions.newBuilder(auth)
            .setPhoneNumber( phoneNumber.replaceFirst("0","+886"))       // Phone number to verify
            .setTimeout(60L, TimeUnit.SECONDS) // Timeout and unit
            .setActivity(activity)                 // Activity (for callback binding)
            .setCallbacks(object  :PhoneAuthProvider.OnVerificationStateChangedCallbacks(){
                override fun onVerificationCompleted(credential: PhoneAuthCredential) {

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
                    getSMSCode.postValue(GetSMSCode(true))
                    verificationIdString =  verificationId


                }

            })
            .build()
        PhoneAuthProvider.verifyPhoneNumber(options)
    }


    fun verifyPhoneNumberWithCode(code: String){
        val credential = PhoneAuthProvider.getCredential(verificationIdString, code)
        auth.signInWithCredential(credential)
            .addOnCompleteListener(activity) { task ->
                if (task.isSuccessful) {
                    // Sign in success, update UI with the signed-in user's information
                    val user = task.result?.user
                    var _user = UserData(user?.phoneNumber, TimeManager.getDate(user?.metadata?.creationTimestamp), TimeManager.getDate(user?.metadata?.lastSignInTimestamp), user?.uid)
                    useData.postValue(_user)

                } else {
                    // Sign in failed, display a message and update the UI
                    if (task.exception is FirebaseAuthInvalidCredentialsException) {
                        // The verification code entered was invalid
                    }
                    // Update UI
                }
            }


    }

    fun  deleteUser(isCheck : Boolean){
        if (isCheck){

            Firebase.auth.currentUser.delete().addOnCompleteListener {
                if (it.isSuccessful){
                    deleteUserData.postValue(DeleteUserData(it.isSuccessful))
                }


            }
        }else{
            Toast.makeText(activity,"請先勾選同意刪除",Toast.LENGTH_SHORT).show()

        }

    }
    fun checkUserLogin(){
        val user = Firebase.auth.currentUser
        if (user!=null){
            Log.d("Jack",user.metadata.creationTimestamp.toString())
            useData.postValue(UserData(user?.phoneNumber, TimeManager.getDate(user?.metadata?.creationTimestamp), TimeManager.getDate(user?.metadata?.lastSignInTimestamp), user?.uid))

        }


    }



}
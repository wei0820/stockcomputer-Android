package com.jackpan.stockcomputer

import android.app.AlertDialog

import android.os.Bundle
import android.text.InputType
import android.view.View
import com.jackpan.stockcomputer.member.viewmodel.MemberModel
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import com.google.firebase.auth.FirebaseAuth
import com.jackpan.stockcomputer.databinding.ActivityMemberCenterBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.activity_member_center.*


@AndroidEntryPoint
class MemberCenterActivity : AppCompatActivity() {
    private lateinit var auth: FirebaseAuth
    private val memberModel : MemberModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_member_center)
        val binding = DataBindingUtil.setContentView<ActivityMemberCenterBinding>(this, R.layout.activity_member_center)
        binding.memberViewModel = memberModel
        binding.lifecycleOwner = this;
        initLayout()
        auth = FirebaseAuth.getInstance()
        auth.setLanguageCode("zh-TW")

        sendbtn.setOnClickListener {
            if (phoneedt.text!!.isNotEmpty()){
                var phoneText  = phoneedt.text.toString().replaceFirst("0","+886")

                memberModel.getPhoneSMScode(auth,phoneText,this)
                memberModel.apply {

                    getSMSCode.observe(this@MemberCenterActivity, Observer {

                        if(it.smsCode){
                            AlertDialog.Builder(this@MemberCenterActivity)
                                .setMessage("請輸入該驗證碼進行驗證")
                                .setTitle("驗證碼已送出")
                                .setPositiveButton("Ok",null)
                                .show()

                            sendsmscodelayout.visibility = View.VISIBLE
                        }

                    })


                }
                sendbtn2.setOnClickListener {
                    memberModel.verifyPhoneNumberWithCode(auth,this,smsedt.text.toString())
                    memberModel.also {
                        it.useData.observe(this, Observer {
                            getsmscodelayout.visibility = View.GONE
                            sendsmscodelayout.visibility = View.GONE
                            isloginlayout.visibility = View.VISIBLE
                        })
                    }

                }

            }
        }


    }
    fun initLayout(){
        phoneedt.inputType = InputType.TYPE_CLASS_PHONE
        phoneedt.maxLines = 10



    }
}

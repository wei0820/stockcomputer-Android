package com.jackpan.stockcomputer

import android.app.AlertDialog

import android.os.Bundle
import android.text.InputType
import android.view.View
import com.jackpan.stockcomputer.viewmodel.MemberModel
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.observe
import com.google.firebase.auth.FirebaseAuth
import com.jackpan.stockcomputer.databinding.ActivityMemberCenterBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.activity_member_center.*


@AndroidEntryPoint
class MemberCenterActivity : AppCompatActivity() {
    private val memberModel : MemberModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = DataBindingUtil.setContentView<ActivityMemberCenterBinding>(this, R.layout.activity_member_center)
        binding.memberViewModel = memberModel
        binding.lifecycleOwner = this;
        initLayout()
        initObserve()
    }
    fun initLayout(){
        phoneedt.inputType = InputType.TYPE_CLASS_PHONE
        phoneedt.maxLines = 10
    }

    fun initObserve(){
        memberModel.also {
            it.useData.observe(this, Observer {
                getsmscodelayout.visibility = View.GONE
                sendsmscodelayout.visibility = View.GONE
                isloginlayout.visibility = View.VISIBLE
            })

            it.getSMSCode.observe(this@MemberCenterActivity, Observer {

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
    }

    override fun onResume() {
        super.onResume()
        memberModel.checkUserLogin()
    }
}

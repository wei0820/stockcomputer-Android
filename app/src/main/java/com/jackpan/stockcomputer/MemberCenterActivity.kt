package com.jackpan.stockcomputer

import android.app.Activity

import android.os.Bundle
import com.jackpan.stockcomputer.member.Model.MemberModel
import kotlinx.android.synthetic.main.activity_member_center.*
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.ktx.Firebase
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class MemberCenterActivity : AppCompatActivity() {
    private lateinit var auth: FirebaseAuth

    val memberModel : MemberModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_member_center)
        auth = FirebaseAuth.getInstance()

        memberModel.userPhoneLogin(auth,"+886911325323",this)
        memberModel.apply {

            login.observe(this@MemberCenterActivity, Observer {
                if (it.uid =="success")






            })


        }

    }
}

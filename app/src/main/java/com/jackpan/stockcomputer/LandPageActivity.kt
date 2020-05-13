package com.jackpan.stockcomputer

import android.app.Activity
import android.os.Bundle
import android.content.Intent
import java.util.*


class LandPageActivity : Activity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_land_page)
        getHomeActivity()


    }

    private fun getHomeActivity() {
        val timer = Timer()
        val task = object : TimerTask() {
            override fun run() {
                val intent = Intent(this@LandPageActivity, MainActivity::class.java)
                startActivity(intent)
                finish()
                overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out)
            }
        }
        timer.schedule(task, 2500)
    }
}

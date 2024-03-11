package com.jackpan.stockcomputer

import android.app.Application


class MyApp : Application() {

    override fun onCreate() {
        super.onCreate()
//        Instabug.Builder(this, "9e18a84c1cf580bd2975dba47b97c60a")
//                .setInvocationEvents(
//                        InstabugInvocationEvent.SHAKE,
//                        InstabugInvocationEvent.FLOATING_BUTTON)
//                .build();

        getFCMToken()
    }


    companion object{

    }


    private fun getFCMToken() {
//        FirebaseInstanceId.getInstance().instanceId
//            .addOnCompleteListener(OnCompleteListener { task ->
//                if (!task.isSuccessful) {
//                    return@OnCompleteListener
//                }
//                val token = task.result.token
//
//                // Log and toast
//            })
    }

}
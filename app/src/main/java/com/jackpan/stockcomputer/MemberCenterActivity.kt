package com.jackpan.stockcomputer

import Manager.FirebaseDatebaseManager
import android.app.Activity
import android.os.Bundle
import com.facebook.CallbackManager
import android.content.Intent
import android.util.Log
import android.view.View
import android.widget.Button
import com.facebook.AccessToken
import com.facebook.FacebookCallback
import com.facebook.FacebookException
import com.facebook.login.LoginManager
import com.facebook.login.LoginResult
import com.google.firebase.auth.FacebookAuthProvider
import com.google.firebase.auth.FirebaseAuth
import java.util.*
import com.google.firebase.auth.FirebaseUser







class MemberCenterActivity : Activity() {
    lateinit var btnLoginFacebook : Button
    var callbackManager: CallbackManager? = null
    private lateinit var auth: FirebaseAuth// ...

    private val firebaseAuth: FirebaseAuth? = null
    private var firebaseAuthListener: FirebaseAuth.AuthStateListener? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_member_center)
        callbackManager = CallbackManager.Factory.create();
        btnLoginFacebook = findViewById(R.id.btnLoginFacebook)
        btnLoginFacebook.setOnClickListener(View.OnClickListener {
            // Login
            callbackManager = CallbackManager.Factory.create()
            LoginManager.getInstance().logInWithReadPermissions(this, Arrays.asList("public_profile", "email"))
            LoginManager.getInstance().registerCallback(callbackManager,
                    object : FacebookCallback<LoginResult> {
                        override fun onSuccess(loginResult: LoginResult) {
                            Log.d("Jack", "Facebook token: " + loginResult.accessToken.token)
                            handleFacebookAccessToken(loginResult.accessToken)
                        }

                        override fun onCancel() {
                            Log.d("MainActivity", "Facebook onCancel.")

                        }

                        override fun onError(error: FacebookException) {
                            Log.d("MainActivity", "Facebook onError.")

                        }
                    })
        })
        auth = FirebaseAuth.getInstance()



    }
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent) {
        super.onActivityResult(requestCode, resultCode, data)
        callbackManager!!.onActivityResult(requestCode, resultCode, data)
    }
    private fun handleFacebookAccessToken(token: AccessToken) {


        val credential = FacebookAuthProvider.getCredential(token.token)
        auth.signInWithCredential(credential)
                .addOnCompleteListener(this) { task ->
                    if (task.isSuccessful) {
                        // Sign in success, update UI with the signed-in user's information
                        val user = auth.currentUser
                        Log.d("Jack",user!!.uid )

                        FirebaseDatebaseManager.getMemberData(user!!.uid)


                    } else {
                        // If sign in fails, display a message to the user.
                    }

                    // ...
                }
    }

    override fun onResume() {
        super.onResume()
        checkMemberLoginState()
    }

    protected override fun onStart() {
        super.onStart()
        firebaseAuthListener?.let { auth.addAuthStateListener(it) }
    }

    protected override fun onStop() {
        super.onStop()
        firebaseAuthListener?.let { auth.removeAuthStateListener(it) }
    }


    fun checkMemberLoginState(){
        val currentUser = auth.currentUser
        if (currentUser != null) {
            btnLoginFacebook.visibility = View.GONE

            Log.d("Jack", currentUser.displayName)
            Log.d("Jack", currentUser.uid)


        }else{
            btnLoginFacebook.visibility = View.VISIBLE

        }
    }

}

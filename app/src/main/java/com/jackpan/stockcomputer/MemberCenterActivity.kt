package com.jackpan.stockcomputer

import Manager.FirebaseDatebaseManager
import android.app.Activity
import android.app.AlertDialog
import android.content.DialogInterface
import android.os.Bundle
import com.facebook.CallbackManager
import android.content.Intent
import android.util.Log
import android.view.View
import android.widget.*
import com.bumptech.glide.Glide
import com.facebook.AccessToken
import com.facebook.FacebookCallback
import com.facebook.FacebookException
import com.facebook.login.LoginManager
import com.facebook.login.LoginResult
import com.google.firebase.auth.FacebookAuthProvider
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import java.util.*







class MemberCenterActivity : Activity() {
    lateinit var btnLoginFacebook : Button
    var callbackManager: CallbackManager? = null
    private lateinit var auth: FirebaseAuth// ...
    private var firebaseAuthListener: FirebaseAuth.AuthStateListener? = null
    lateinit var mImageView: ImageView

    lateinit var mIDtext :TextView
    lateinit var mNameTextView: TextView
    lateinit var mPointTextView: TextView
    lateinit var mLastTimeTextView: TextView
    lateinit var mCheckTextView: TextView
    lateinit var mWatchImageButton: ImageButton
    lateinit var mLogOutButton: Button
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
        mImageView = findViewById(R.id.image)
        mIDtext = findViewById(R.id.idtext)
        mNameTextView = findViewById(R.id.nametext)
        mPointTextView = findViewById(R.id.pointtext)
        mLastTimeTextView = findViewById(R.id.lasttext)
        mCheckTextView = findViewById(R.id.checktext)
        mPointTextView.setOnClickListener {
            Toast.makeText(this,"用於一些需要的地方！",Toast.LENGTH_SHORT).show()
        }
        mWatchImageButton  = findViewById(R.id.watch)
        mWatchImageButton.setOnClickListener {
            AlertDialog.Builder(this)
                    .setMessage("Your BMI is")
                    .setTitle("看影片獲取")
                    .setPositiveButton("OK", DialogInterface.OnClickListener { dialogInterface, i ->

                    })
                    .setNeutralButton("Cancel", null)
                    .show()
        }
        mLogOutButton = findViewById(R.id.logoutbtn)
        mLogOutButton.setOnClickListener {
            signOut()
        }

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
                        UpdateUI(user)



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
        UpdateUI(currentUser)

    }
    fun  signOut() {
        auth.signOut()
        LoginManager.getInstance().logOut()
    }

    fun UpdateUI(currentUser :FirebaseUser?){

        if (currentUser != null) {
            btnLoginFacebook.visibility = View.GONE
            FirebaseDatebaseManager.getMemberData(currentUser.uid,mPointTextView,mLastTimeTextView,mCheckTextView)
            mIDtext.text =  "會員ID:" + currentUser.uid
            mNameTextView.text = "會員姓名:" + currentUser.displayName
            Glide.with(this)
                    .load(currentUser.photoUrl)
                    .into(mImageView)

            FirebaseDatebaseManager.updateMemberPoint(currentUser.uid)
        }else{
            btnLoginFacebook.visibility = View.VISIBLE

        }
    }

}

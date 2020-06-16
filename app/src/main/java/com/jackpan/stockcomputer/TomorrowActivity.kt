package com.jackpan.stockcomputer
import android.os.Bundle
import android.view.View
import android.view.View.OnClickListener
import android.widget.*
import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.AdView
import com.google.android.gms.ads.MobileAds
import com.libizo.CustomEditText

class TomorrowActivity : BaseActivity(), OnClickListener{
    override fun onClick(p0: View?) {
        when(p0!!.id){
            R.id.button ->{
                if(mEdt1.text!!.isEmpty() || mEdt2.text!!.isEmpty()||
                        mEdt3.text!!.isEmpty()||mEdt4.text!!.isEmpty()
                        || mEditText.text!!.isEmpty()){

                    setToast("請檢查後再按計算")

                }else{
                    var buypriceDouble :Double = mEdt1.text.toString().toDouble()
                    var sellPirceDouble : Double = mEdt2.text.toString().toDouble()
                    var buyNumDouble :Double = mEdt3.text.toString().toDouble()
                    var sellNumDouble :Double = mEdt4.text.toString().toDouble()
                    var handPrice : Double = mEditText.text.toString().toInt() * 0.01
                    



                }


            }
        }
    }

    lateinit var mAdView : AdView
    lateinit var mEdt1 : CustomEditText
    lateinit var mEdt2 : CustomEditText
    lateinit var mEdt3 : CustomEditText
    lateinit var mEdt4 : CustomEditText
    lateinit var mEditText: CustomEditText
    lateinit var mButton: Button
    lateinit var mTextView: TextView
    lateinit var mTextView2: TextView
    lateinit var mTextView3: TextView
    lateinit var mTextView4: TextView
//    val discount = arrayListOf("沒折扣", "95折", "9折", "85折", "8折",
//            "75折", "7折", "65折", "6折", "55折", "5折", "45折", "35折", "3折", "25折", "2折"
//            , "15折", "1折"," 免手續費")




    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_tomorrow)
        setAd()
        initLayout()
    }
    fun initLayout(){
        mEdt1 = findViewById(R.id.edt1)
        mEdt2 = findViewById(R.id.edt2)
        mEdt3 = findViewById(R.id.edt3)
        mEdt4 = findViewById(R.id.edt4)
        mEditText = findViewById(R.id.spinner)
        mButton = findViewById(R.id.button)
        mButton.setOnClickListener(this)
        mTextView = findViewById(R.id.text_1)
        mTextView2 = findViewById(R.id.text_2)
        mTextView3 = findViewById(R.id.text_3)
        mTextView4 = findViewById(R.id.text_4)



    }
    fun setAd(){
        MobileAds.initialize(this) {}
        mAdView = findViewById(R.id.adView)
        val adRequest = AdRequest.Builder().build()
        mAdView.loadAd(adRequest)
    }
}

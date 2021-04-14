package com.jackpan.stockcomputer
import android.content.Context
import android.os.Bundle
import android.view.View
import android.view.View.OnClickListener
import android.view.inputmethod.InputMethodManager
import android.widget.*
import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.AdView
import com.google.android.gms.ads.MobileAds
import com.libizo.CustomEditText
import java.text.DecimalFormat

class TomorrowActivity : BaseActivity(), OnClickListener{
    override fun onClick(p0: View?) {
        when(p0!!.id){
            R.id.button ->{
                if(mEdt1.text!!.isEmpty() || mEdt2.text!!.isEmpty()||
                        mEdt3.text!!.isEmpty()||mEdt4.text!!.isEmpty()
                        || mEditText.text!!.isEmpty()){

                    setToast("請檢查後再按計算")

                }else{
                    //取得 買入價
                    var buypriceDouble :Double = mEdt1.text.toString().toDouble()
                    //取得 賣出價
                    var sellPirceDouble : Double = mEdt2.text.toString().toDouble()
                    //取得 買入張數

                    var buyNumDouble :Double = mEdt3.text.toString().toDouble() *1000
                    //取得 賣出張數

                    var sellNumDouble :Double = mEdt4.text.toString().toDouble() *1000
                    //取得手續折扣
                    var handPrice : Double = mEditText.text.toString().toDouble()
                    //取得手續費率
                    var handRate : Double =  0.001425

                    //取得 買入總價錢
                    var buyAllPrcieDoube : Double = (buypriceDouble * buyNumDouble) +(buypriceDouble * buyNumDouble*handPrice*handRate)
                    //取得 賣出總價錢
                    var sellAllPriceDouble :Double =( sellPirceDouble * sellNumDouble) - ( sellPirceDouble * sellNumDouble*handPrice*handRate)-
                            ( sellPirceDouble * sellNumDouble*0.003)



                    mTextView.text = df.format(buyAllPrcieDoube)
                    mTextView2.text = df.format(sellAllPriceDouble)
                    mTextView3.text = df.format( (sellAllPriceDouble - buyAllPrcieDoube))
                    mTextView4.text = df.format((((sellAllPriceDouble - buyAllPrcieDoube)/buyAllPrcieDoube) *100)) + "%"


                    closeKeybord()







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
    var df = DecimalFormat("##.00")


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_tomorrow)
        setAd()
        initLayout()
    }
    fun initLayout(){
        mEdt1 = findViewById(R.id.phoneedt)
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
    fun closeKeybord(){
        val inputManager: InputMethodManager = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        inputManager.hideSoftInputFromWindow(currentFocus!!.windowToken, InputMethodManager.SHOW_FORCED)

    }
}

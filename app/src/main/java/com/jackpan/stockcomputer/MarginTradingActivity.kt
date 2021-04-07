package com.jackpan.stockcomputer

import Manager.DateManager
import android.content.Context
import android.os.Bundle
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.Button
import android.widget.TextView
import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.AdView
import com.google.android.gms.ads.MobileAds
import com.libizo.CustomEditText
import com.tsongkha.spinnerdatepicker.SpinnerDatePickerDialogBuilder
import java.text.DecimalFormat

class MarginTradingActivity  : BaseActivity() , View.OnClickListener {
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
                    var handMoney : Double = (buypriceDouble * buyNumDouble) * 0.001425
                    var changePrice : Double =   (buypriceDouble * buyNumDouble)  * 0.003
                    var borrowPrice : Double  = (buypriceDouble * buyNumDouble)  * 0.0008

                    var guaranteePrice : Int = (buypriceDouble * buyNumDouble).toInt() - handMoney.toInt() - changePrice.toInt() - borrowPrice.toInt()

                    var returnMoney : Int =  (sellNumDouble * sellPirceDouble).toInt() + (sellNumDouble * sellPirceDouble * 0.001425 * handPrice).toInt()


                    //取得 保證金
                    var buyAllPrcieDoube : Double = ((buypriceDouble * buyNumDouble)* 0.9)


                    var day : Int = (DateManager.dateDiff(startDate,endDate))


                    var  interestPrice :Double =  (((buyAllPrcieDoube + guaranteePrice) * day *0.02)/365 )

                    //保證金


                    mTextView5.text =  Math.round(buyAllPrcieDoube).toString()
                    mTextView.text = Math.round(interestPrice).toString()


                    mTextView3.text =  returnMoney.toString()
                    closeKeybord()

                    mDateTextView.text = "總天數:" + DateManager.dateDiff(startDate,endDate)



                    mTextView6.text = guaranteePrice.toString()

                }


            }
            R.id.startbtn ->
                SpinnerDatePickerDialogBuilder()
                    .context(this)
                    .callback { view, year, monthOfYear, dayOfMonth ->
                        mStartTextView.text = year.toString()+"-"+(monthOfYear+1).toString()+"-"+
                                dayOfMonth.toString()
                        startDate =  year.toString()+"-"+monthOfYear.toString()+"-"+
                                dayOfMonth.toString()
                    }
                    .spinnerTheme(R.style.DatePickerSpinner)
                    .defaultDate(DateManager.getYear(), DateManager.getMonth(), DateManager.getDay())
                    .build()
                    .show()

            R.id.endtbtn ->
                SpinnerDatePickerDialogBuilder()
                    .context(this)
                    .callback { view, year, monthOfYear, dayOfMonth ->


                        mEndTextView.text = year.toString()+"-"+(monthOfYear+1).toString()+"-"+
                                dayOfMonth.toString()

                        endDate = year.toString()+"-"+monthOfYear.toString()+"-"+
                                dayOfMonth.toString()


                    }
                    .spinnerTheme(R.style.DatePickerSpinner)
                    .defaultDate(DateManager.getYear(), DateManager.getMonth(), DateManager.getDay())
                    .build()
                    .show()
        }

    }


    lateinit var mStartButton: Button
    lateinit var mEndButton: Button
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
    lateinit var mTextView5: TextView
    lateinit var mTextView6: TextView

    lateinit var mStartTextView: TextView
    lateinit var mEndTextView: TextView
    var startDate : String = ""
    var endDate : String = ""
    lateinit var mDateTextView: TextView
    lateinit var mloandMoney : CustomEditText
    var df = DecimalFormat("##.00")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_margin_trading)
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
        mTextView5 = findViewById(R.id.text_5)
        mTextView6 = findViewById(R.id.text_6)

        mStartButton = findViewById(R.id.startbtn)
        mEndButton = findViewById(R.id.endtbtn)
        mStartButton.setOnClickListener(this)
        mEndButton.setOnClickListener(this)

        mStartTextView = findViewById(R.id.starttext)
        mEndTextView = findViewById(R.id.endtext)
        mDateTextView = findViewById(R.id.datetext)
        mloandMoney = findViewById(R.id.edt)
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

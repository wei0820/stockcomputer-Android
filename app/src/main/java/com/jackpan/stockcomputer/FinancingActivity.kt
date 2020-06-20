package com.jackpan.stockcomputer

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.Button
import android.widget.TextView
import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.AdView
import com.google.android.gms.ads.MobileAds
import com.libizo.CustomEditText
import com.tsongkha.spinnerdatepicker.SpinnerDatePickerDialogBuilder
import kotlinx.android.synthetic.main.activity_financing.*
import java.text.DecimalFormat
import java.text.SimpleDateFormat

class FinancingActivity : BaseActivity() , View.OnClickListener {
    override fun onClick(p0: View?) {
        when(p0!!.id){
            R.id.button ->{
                if(mEdt1.text!!.isEmpty() || mEdt2.text!!.isEmpty()||
                        mEdt3.text!!.isEmpty()||mEdt4.text!!.isEmpty()
                        || mEditText.text!!.isEmpty()||mloandMoney.text!!.isEmpty()){

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
                    var handPrice : Double = mEditText.text.toString().toInt() * 0.1
                    //取得手續費率
                    var handRate : Double =  0.001425
                    var loandMoneyDouble : Double = edt.text.toString().toDouble() * 0.1

                    //取得 買入總價錢
                    var buyAllPrcieDoube : Double = (buypriceDouble * buyNumDouble) +(buypriceDouble * buyNumDouble*handPrice*handRate)
                    //取得 賣出總價錢
                    var sellAllPriceDouble :Double =( sellPirceDouble * sellNumDouble) + ( sellPirceDouble * sellNumDouble*handPrice*handRate)+
                            ( sellPirceDouble * sellNumDouble*0.003 * 0.5)


                    var  interest :Double =  ( (buypriceDouble *  buyNumDouble) *  loandMoneyDouble) * 0.0645 *(( DateManager.dateDiff(startDate,endDate)/365))

                    mTextView.text = df.format( buyAllPrcieDoube - ((buypriceDouble *  buyNumDouble) *  loandMoneyDouble))
                    mTextView2.text =sellAllPriceDouble.toString()
                    mTextView3.text =  (sellAllPriceDouble - buyAllPrcieDoube).toString()
                    mTextView4.text = (((sellAllPriceDouble - buyAllPrcieDoube)/buyAllPrcieDoube) *100).toString() + "%"
                    closeKeybord()

                    mDateTextView.text = "總天數:" + DateManager.dateDiff(startDate,endDate)
                    mTextView5.text = df.format( (buypriceDouble *  buyNumDouble) *  loandMoneyDouble)
                    mTextView6.text = df.format(interest)

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
    lateinit var mloandMoney :CustomEditText
    var simpleDateFormat: SimpleDateFormat? = null
    var df = DecimalFormat("##.00")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_financing)
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

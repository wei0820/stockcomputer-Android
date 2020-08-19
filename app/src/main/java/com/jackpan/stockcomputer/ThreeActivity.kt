package com.jackpan.stockcomputer

import android.app.ProgressDialog
import android.os.Bundle
import android.widget.TextView
import org.jsoup.Jsoup
import java.io.IOException

class ThreeActivity : BaseActivity() {
    lateinit var mTextView: TextView
    lateinit var mTextView2: TextView
    lateinit var mTextView3: TextView
    lateinit var mTextView4: TextView
    lateinit var mTextView5: TextView
    lateinit var mTextView11: TextView
    lateinit var mTextView22: TextView
    lateinit var mTextView33: TextView
    lateinit var mTextView44: TextView
    lateinit var mProgressDialog : ProgressDialog



    var arrayList:ArrayList<String> = ArrayList()



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_three)
        mProgressDialog = ProgressDialog(this)

        mProgressDialog.setMessage("讀取中")
        mProgressDialog.setCancelable(false)
        mProgressDialog.show()
        initLayout()
        getThree()


    }
    fun initLayout(){
        mTextView = findViewById(R.id.text_1)
        mTextView2 = findViewById(R.id.text_2)
        mTextView3 = findViewById(R.id.text_3)
        mTextView4 = findViewById(R.id.text_4)
        mTextView5 = findViewById(R.id.text_5)
        mTextView11 = findViewById(R.id.text_11)
        mTextView22 = findViewById(R.id.text_22)
        mTextView33 = findViewById(R.id.text_33)
        mTextView44 = findViewById(R.id.text_44)




    }
    private fun getThree() {
        object : Thread() {
            override fun run() {
                super.run()
                try {
                    val doc = Jsoup.connect("https://histock.tw/stock/three.aspx").get()
                    for (element in doc.select("div.grid-item>div.grid-body.p5>table.gvTB>tbody")) {
                        for (td in element.select("tr").get(1).select("td")) {
                            arrayList.add(td.text())

                            runOnUiThread {
                                if (arrayList.size != 0){
                                    mTextView.text = "日期:"+ arrayList.get(0)
                                    mTextView2.text = "外資:"+ arrayList.get(1)
                                    mTextView3.text = "投信:"+ arrayList.get(2)
                                    mTextView4.text = "自營商:"+ arrayList.get(3)
                                    mTextView5.text = "合計:"+ arrayList.get(6)
                                    mTextView11.text = "日期:"+ arrayList.get(7)
                                    mTextView22.text = "外資:"+ arrayList.get(8)
                                    mTextView33.text = "投信:"+  arrayList.get(9)
                                    mTextView44.text = "自營商:"+ arrayList.get(10)

                                    mProgressDialog.dismiss()


                                }
                            }

                        }

                    }

                } catch (e: IOException) {
                    e.printStackTrace()
                }

            }
        }.start()
    }
}

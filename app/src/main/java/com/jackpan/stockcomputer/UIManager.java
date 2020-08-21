package com.jackpan.stockcomputer;

import android.app.Activity;
import android.content.Intent;
import android.widget.TextView;

import com.roughike.bottombar.OnTabReselectListener;

import java.text.DecimalFormat;

public class UIManager {

    public static void SetBottomBar(com.roughike.bottombar.BottomBar bottomBar, final Activity activity){
        bottomBar.setOnTabReselectListener(new OnTabReselectListener() {
            @Override
            public void onTabReSelected(int tabId) {
                switch (tabId){
                    case  R.id.tab_favorites:
                        activity.startActivity(new Intent(activity,TodaySellActivity.class));
                        break;
                    case R.id.tab_nearby:

                        break;
                    case R.id.tab_friends:

                        break;
                }
            }


        });
    }

    public  static  void setDecimalTextView(TextView textView,String s){
        DecimalFormat df = new DecimalFormat("##.00");
        textView.setText(df.format(s));



    }



}

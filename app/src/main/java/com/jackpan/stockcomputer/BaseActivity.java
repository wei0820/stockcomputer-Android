package com.jackpan.stockcomputer;

import android.app.Activity;
import android.os.Bundle;
import android.widget.Toast;

import androidx.annotation.Nullable;


public class BaseActivity extends Activity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);



    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    public void setToast(String s){
        Toast.makeText(this,s,Toast.LENGTH_SHORT).show();
    }
}

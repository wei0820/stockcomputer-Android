package com.jackpan.stockcomputer;

import android.os.Bundle;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.analytics.FirebaseAnalytics;


public class BaseActivity extends AppCompatActivity {
    private FirebaseAnalytics mFirebaseAnalytics;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mFirebaseAnalytics = FirebaseAnalytics.getInstance(this);



    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    public void setToast(String s){
        Toast.makeText(this,s,Toast.LENGTH_SHORT).show();
    }
}

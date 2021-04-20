package com.jackpan.stockcomputer;

import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.analytics.FirebaseAnalytics;

import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class BaseActivity extends AppCompatActivity {
    private FirebaseAnalytics mFirebaseAnalytics;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
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

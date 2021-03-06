package com.jackpan.stockcomputer;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;

public class MySharedPrefernces {

    public static final String  NAME = "MySharedPrefernces";
    public static final String KEY_MEMBER_POINT= "memberpoint";
    public static final String KEY_LASTLOGINTIME= "lastlogintime";
    public static final String KEY_WATCHADTIME= "watchadtime";



    public static void saveMemberPoint(Context context, Long ppint) {
        SharedPreferences sp = context.getSharedPreferences(NAME, Activity.MODE_PRIVATE);
        sp.edit().putLong(KEY_MEMBER_POINT, ppint).apply();
    }

    public static Long getMemberPoint(Context context) {
        SharedPreferences sp = context.getSharedPreferences(NAME, Activity.MODE_PRIVATE);
        return sp.getLong(KEY_MEMBER_POINT,0);
    }

    public static void saveLastLoginTime(Context context, Long ppint) {
        SharedPreferences sp = context.getSharedPreferences(NAME, Activity.MODE_PRIVATE);
        sp.edit().putLong(KEY_LASTLOGINTIME, ppint).apply();
    }

    public static Long getLastLoginTime(Context context) {
        SharedPreferences sp = context.getSharedPreferences(NAME, Activity.MODE_PRIVATE);
        return sp.getLong(KEY_LASTLOGINTIME,0);
    }

    public static void saveWatchADTime(Context context, Long ppint) {
        SharedPreferences sp = context.getSharedPreferences(NAME, Activity.MODE_PRIVATE);
        sp.edit().putLong(KEY_WATCHADTIME, ppint).apply();
    }

    public static Long getWatchADTime(Context context) {
        SharedPreferences sp = context.getSharedPreferences(NAME, Activity.MODE_PRIVATE);
        return sp.getLong(KEY_WATCHADTIME,0);
    }




}

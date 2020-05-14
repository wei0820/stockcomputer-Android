package Manager;

import android.content.Context;
import android.graphics.Color;
import android.net.Uri;
import android.util.Log;
import android.widget.ImageView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;


import java.util.ArrayList;

import Data.Banner;
import Data.Stockcomupter;

public class FirebaseDatebaseManager {
    private static  DatabaseReference mDatabase;// ...

    public static void getFirebaseDatebase(final  com.xiaweizi.marquee.MarqueeTextView textView){
        mDatabase = FirebaseDatabase.getInstance().getReference().child("stockcomuper").child("stockcomuper").child("stockcomuper");
        ValueEventListener postListener = new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // Get Post object and use the values to update the UI
                Stockcomupter banner = dataSnapshot.getValue(Stockcomupter.class);
                Log.d("Jack",banner.announcement);
                textView.setText(banner.announcement);
                textView.startScroll();
                // ...
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                // Getting Post failed, log a message
                // ...
            }
        };
        mDatabase.addValueEventListener(postListener);


    }

    public static void getBannerData(final  com.lw.banner.Banner bannerView){
        final ArrayList<String> stringArrayList=new ArrayList<>();

        mDatabase = FirebaseDatabase.getInstance().getReference().child("banner").child("banner").child("banner");
        ValueEventListener postListener = new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // Get Post object and use the values to update the UI
                Banner banner = dataSnapshot.getValue(Banner.class);
                stringArrayList.add(banner.page1);
                stringArrayList.add(banner.page2);
                stringArrayList.add(banner.page3);
                stringArrayList.add(banner.page4);
                stringArrayList.add(banner.page5);
                bannerView.setImages(stringArrayList)
                        .setImageLoader(new GlideImageLoader())
                        .isAutoPlay(true)
                        .init();

                // ...
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                // Getting Post failed, log a message
                // ...
            }
        };
        mDatabase.addValueEventListener(postListener);






    }

}

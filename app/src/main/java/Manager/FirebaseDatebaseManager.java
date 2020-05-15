package Manager;

import android.util.Log;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;


import java.util.ArrayList;

import Data.Banner;
import Data.MemberData;
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
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                // Getting Post failed, log a message
                // ...
            }
        };
        mDatabase.addValueEventListener(postListener);






    }


    public static void getMemberData(String id){
        final ArrayList<String> stringArrayList=new ArrayList<>();

        mDatabase = FirebaseDatabase.getInstance().getReference().child("MemberList").child(id).child(id);
        ValueEventListener postListener = new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // Get Post object and use the values to update the U
                Log.d("Jack",dataSnapshot.toString());

                MemberData memberData = dataSnapshot.getValue(MemberData.class);
                Log.d("Jack",memberData.name);



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

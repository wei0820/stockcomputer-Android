package manager;

import android.util.Log;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.gson.Gson;
import com.jackpan.stockcomputer.model.StockEpsListData;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

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


    public static void getMemberData(String id, final TextView point, final  TextView lasttime, final  TextView check){
        final ArrayList<String> stringArrayList=new ArrayList<>();

        mDatabase = FirebaseDatabase.getInstance().getReference().child("MemberList").child(id).child(id);
        ValueEventListener postListener = new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // Get Post object and use the values to update the U

                MemberData memberData = dataSnapshot.getValue(MemberData.class);
                point.setText("會員點數:"+memberData.point);
                lasttime.setText("上次登入:"+TimeManager.getDate(memberData.lastlogintime));
                check.setText("上次看影片時間:"+ TimeManager.getDate(memberData.watchadtime));


            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                // Getting Post failed, log a message
                // ...
            }
        };
        mDatabase.addValueEventListener(postListener);






    }

    public static  void updateMemberPoint(String id){
        mDatabase = FirebaseDatabase.getInstance().getReference().child("MemberList").child(id).child(id);
        Map<String, Object> childUpdates = new HashMap<>();
        childUpdates.put("point",100);
        mDatabase.updateChildren(childUpdates);

    }

    public static  void updateMemberVersion (String id){
        mDatabase = FirebaseDatabase.getInstance().getReference().child("MemberList").child(id).child(id);
        Map<String, Object> childUpdates = new HashMap<>();
        childUpdates.put("version",100);
        mDatabase.updateChildren(childUpdates);

    }

    public static void getStockEpsList(){
        mDatabase = FirebaseDatabase.getInstance().getReference().child("stockepslist");
        ValueEventListener postListener = new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // Get Post object and use the values to update the UI



                for(DataSnapshot datas: dataSnapshot.getChildren()){
                    Gson gson = new Gson();
                    StockEpsListData stockEpsListData = gson.fromJson(datas.getValue().toString(),StockEpsListData.class);
                    Log.d("Jack",stockEpsListData.rank+","+stockEpsListData.name);


                }

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

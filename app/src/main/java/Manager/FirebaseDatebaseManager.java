package Manager;

import android.util.Log;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import Data.Stockcomupter;

public class FirebaseDatebaseManager {
    private static  DatabaseReference mDatabase;// ...

    public static void getFirebaseDatebase(){
        mDatabase = FirebaseDatabase.getInstance().getReference().child("stockcomuper").child("stockcomuper").child("stockcomuper");
        ValueEventListener postListener = new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // Get Post object and use the values to update the UI
                Stockcomupter banner = dataSnapshot.getValue(Stockcomupter.class);
                Log.d("Jack",banner.announcement);
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

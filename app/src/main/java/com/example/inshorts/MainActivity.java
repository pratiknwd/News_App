package com.example.inshorts;


import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;

import android.os.Bundle;
import android.util.Log;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity_CommTag";
    //now create list of type slider items
    List<SliderItems> sliderItems = new ArrayList<>();

    ArrayList<String> titles = new ArrayList<>();
    ArrayList<String> desc = new ArrayList<>();
    ArrayList<String> images = new ArrayList<>();
    ArrayList<String> newslinks = new ArrayList<>();
    ArrayList<String> heads = new ArrayList<>();

    DatabaseReference mRef;
    VerticalViewPager verticalViewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
        verticalViewPager = findViewById(R.id.verticalViewPager);

        mRef = FirebaseDatabase.getInstance().getReference("news");

        getNewsFromRealtimeDB();


    }

    private void getNewsFromRealtimeDB() {
        mRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                for (DataSnapshot ds : snapshot.getChildren()) {
                    //add data to array list
                    titles.add(ds.child("title").getValue(String.class));
                    desc.add(ds.child("desc").getValue(String.class));
                    images.add(ds.child("imagelink").getValue(String.class));
                    newslinks.add(ds.child("newslink").getValue(String.class));
                    heads.add(ds.child("head").getValue(String.class));
                }
                for (int i = 0; i < images.size(); i++) {
                    //here we add slider items with the images that are store in images array list....
                    sliderItems.add(new SliderItems(images.get(i)));

                    //we change int to string because now we retrieve image link and save to array list...istead of drwable image

                }
                verticalViewPager.setAdapter(new ViewPagerAdapter(MainActivity.this, sliderItems, titles, desc, newslinks, heads, verticalViewPager));

                //now add all array list in adapter
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Log.e(TAG, "onCancelled: error getting data from firebase");
            }
        });
    }

}
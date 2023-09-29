package com.example.inshorts;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    //now create list of type slider items
    List<SliderItems> sliderItems = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        VerticalViewPager verticalViewPager = (VerticalViewPager) findViewById(R.id.verticalViewPager);

        sliderItems.add(new SliderItems(R.drawable.ic_launcher_background));
        sliderItems.add(new SliderItems(R.drawable.ic_launcher_background));
        sliderItems.add(new SliderItems(R.drawable.ic_launcher_background));

        verticalViewPager.setAdapter(new ViewPagerAdapter(MainActivity.this,sliderItems));

    }
}
package com.example.tog_s;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;


import android.content.Intent;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if(getSupportActionBar() != null){
            getSupportActionBar().hide();
        }

        Auth auth = new Auth();
        auth.test();

        Intent i =new Intent(getApplicationContext(),IntroSlider.class);
        startActivity(i);

//        ViewPager viewPager ;
//        Slider_Adapter adapter ;
//
//        viewPager= findViewById(R.id.view1);
//        adapter = new Slider_Adapter(this);
//        viewPager.setAdapter(adapter);
    }
}
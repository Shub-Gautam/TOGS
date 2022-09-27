package com.example.tog_s;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;

import com.github.appintro.AppIntro;
import com.github.appintro.AppIntroFragment;


public class IntroSlider extends AppIntro {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        addSlide(AppIntroFragment.createInstance("C++", "C++ Self Paced Course",
                R.drawable.img1, R.color.white, R.color.black,R.color.black));

        addSlide(AppIntroFragment.createInstance("DSA", "Data Structures and Algorithms", R.drawable.img2,
                R.color.white, R.color.purple_200,R.color.black));

        addSlide(AppIntroFragment.createInstance("Java", "Java Self Paced Course", R.drawable.img3,
                R.color.white, R.color.purple_200,R.color.black));
    }

    @Override
    protected void onDonePressed(@Nullable Fragment currentFragment) {
        Intent myIntent = new Intent(getApplicationContext(),RegLog.class);
        startActivity(myIntent);
    }

    @Override
    protected void onSkipPressed(@Nullable Fragment currentFragment) {
        Intent myIntent = new Intent(getApplicationContext(),RegLog.class);
        startActivity(myIntent);
    }
}

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

        addSlide(AppIntroFragment.createInstance("Scan & Go", "Just scan QR code to mark attendence",
                R.drawable.img1, R.color.white,R.color.black,R.color.black));

        addSlide(AppIntroFragment.createInstance("Raise Funds", "Donate & keep track of your payments online",
                R.drawable.img3,
                R.color.slide2, R.color.black,R.color.black));

        addSlide(AppIntroFragment.createInstance("Interact with your NGOs", "All your solutions in one app", R.drawable.img2,
                R.color.slide1, R.color.black,R.color.black));

        setColorTransitionsEnabled(true);
        int color = R.color.black;
        setColorDoneText(getResources().getColor(color));
        setColorSkipButton(getResources().getColor(color));
        setNextArrowColor(getResources().getColor(color));

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

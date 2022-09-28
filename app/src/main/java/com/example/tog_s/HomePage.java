package com.example.tog_s;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.coordinatorlayout.widget.CoordinatorLayout;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.TextView;

import com.google.android.material.snackbar.Snackbar;

public class HomePage extends AppCompatActivity {
    ConstraintLayout coordinatorLayout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_page);

        SharedPreferences sh = getSharedPreferences("MySharedPref", Context.MODE_PRIVATE);
        String token = sh.getString("token", "");

        coordinatorLayout = findViewById(R.id.homepagesnack);
        Snackbar s = Snackbar.make(coordinatorLayout,"Logged in Successfully",Snackbar.LENGTH_LONG);
        s.show();

        TextView user_FullName = (TextView) findViewById(R.id.textView10);
        TextView user_Position = (TextView) findViewById(R.id.textView10);


    }
}
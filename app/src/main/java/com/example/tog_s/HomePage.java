package com.example.tog_s;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.coordinatorlayout.widget.CoordinatorLayout;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.material.snackbar.Snackbar;

import java.io.InputStream;
import java.net.URL;

public class HomePage extends AppCompatActivity {
    ConstraintLayout coordinatorLayout;
    public static TextView user_Position ;
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
        user_Position = (TextView) findViewById(R.id.textView12);

        ImageView digitalCard = (ImageView) findViewById(R.id.imageView3);
        ImageView classes = (ImageView) findViewById(R.id.imageView4);
        ImageView menu = (ImageView) findViewById(R.id.imageView2);


        digitalCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent myIntent = new Intent(getApplicationContext(),DigitalCard.class);
                startActivity(myIntent);
            }
        });

        menu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent myIntent = new Intent(getApplicationContext(),menu.class);
                startActivity(myIntent);
            }
        });

        classes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent myIntent = new Intent(getApplicationContext(),Events.class);
                startActivity(myIntent);
            }
        });



        Auth auth = new Auth();
        auth.updateNamePos(token,getApplicationContext(),user_FullName,user_Position,coordinatorLayout);

    }
}
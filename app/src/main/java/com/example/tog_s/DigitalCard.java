package com.example.tog_s;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.TextView;

public class DigitalCard extends AppCompatActivity {

    ConstraintLayout constraintLayout ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_digital_card);

        SharedPreferences sh = getSharedPreferences("MySharedPref", Context.MODE_PRIVATE);
        String qrlink = sh.getString("qrCode", "");
        String token = sh.getString("token", "");

        TextView userFullName = (TextView) findViewById(R.id.textView3);
        TextView userPosition = (TextView) findViewById(R.id.textView13);

        constraintLayout = findViewById(R.id.digitalcardid);

        Auth auth = new Auth();
        auth.updateNamePos(token,getApplicationContext(),userFullName,userPosition,constraintLayout);

//        Update QR Code here

    }
}
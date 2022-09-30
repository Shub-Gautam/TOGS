package com.example.tog_s;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class menu extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        Button organizeBtn = (Button) findViewById(R.id.menubtn1);
        Button donateBtn = (Button) findViewById(R.id.menubtn3);
        Button donationHistory = (Button) findViewById(R.id.menubtn4);

        organizeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent schedule = new Intent(getApplicationContext(),CreateEvent.class);
                startActivity(schedule);
            }
        });

        donationHistory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

        donateBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent2 = new Intent(getApplicationContext(),Donate.class);
                startActivity(intent2);
            }
        });
    }
}
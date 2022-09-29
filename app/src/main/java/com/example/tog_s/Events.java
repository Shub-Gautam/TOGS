package com.example.tog_s;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Events extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_events);

        Button goToCreateEventBtn = (Button) findViewById(R.id.OrganizeEventBtn);
        Button searchEvent = (Button) findViewById(R.id.SearchEventBtn);

        goToCreateEventBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(),CreateEvent.class);
                startActivity(intent);
            }
        });

        searchEvent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent myIntent = new Intent(getApplicationContext(),QRScanner.class);
                startActivity(myIntent);
            }
        });

    }
}
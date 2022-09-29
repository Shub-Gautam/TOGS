package com.example.tog_s;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class CreateEvent extends AppCompatActivity {

    ConstraintLayout constraintLayout ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_event);

        EditText title = (EditText) findViewById(R.id.textInputEditTextCreateEventTitle);
        EditText description = (EditText) findViewById(R.id.textInputEditTextCreateEventDescription);
        EditText timings = (EditText) findViewById(R.id.textInputEditTextCreateEventTimings);
        EditText duration = (EditText) findViewById(R.id.textInputEditTextCreateEventDuration);
        EditText eventType = (EditText) findViewById(R.id.textInputEditTextCreateEventEventType);
        EditText venue = (EditText) findViewById(R.id.textInputEditTextCreateEventVenue);

        SharedPreferences sh = getSharedPreferences("MySharedPref", Context.MODE_PRIVATE);
        String token = sh.getString("token", "");

        Button finalCreateEvent  = (Button) findViewById(R.id.CreateEvent2);

        finalCreateEvent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Auth auth = new Auth();
                Intent myIntent = new Intent(getApplicationContext(),Events.class);
                auth.createEvent(token,title.getText().toString(),description.getText().toString(),timings.getText().toString(),duration.getText().toString(),eventType.getText().toString(),venue.getText().toString(),getApplicationContext(),constraintLayout,view,myIntent);
            }
        });

    }
}
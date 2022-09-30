package com.example.tog_s;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

public class CreateEvent extends AppCompatActivity implements AdapterView.OnItemClickListener {

    ConstraintLayout constraintLayout ;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_event);

        SharedPreferences sh = getSharedPreferences("MySharedPref", Context.MODE_PRIVATE);
        String token = sh.getString("token", "");

        Button finalCreateEvent  = (Button) findViewById(R.id.CreateEvent2);

        finalCreateEvent.setOnClickListener(new View.OnClickListener() {


            EditText title = (EditText) findViewById(R.id.textInputEditTextCreateEventTitle);
            EditText description = (EditText) findViewById(R.id.textInputEditTextCreateEventDescription);
            EditText timings = (EditText) findViewById(R.id.textInputEditTextCreateEventTimings);
            EditText duration = (EditText) findViewById(R.id.textInputEditTextCreateEventDuration);
            EditText venue = (EditText) findViewById(R.id.textInputEditTextCreateEventVenue);
            Spinner spinnerLanguages=findViewById(R.id.spinner);
            Spinner spin = (Spinner) findViewById(R.id.spinner);


            String eventType ;


            @Override
            public void onClick(View view) {

                String[] users = getResources().getStringArray(R.array.demo) ;

                ArrayAdapter adapter = new ArrayAdapter(getApplicationContext(), android.R.layout.simple_spinner_item, users);
                adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                spin.setAdapter(adapter);

                spin.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                        eventType= spin.getItemAtPosition(i).toString();
                    }
                });


                View view1 = findViewById(R.id.menuid);
                Auth auth = new Auth();
                Intent myIntent = new Intent(getApplicationContext(),HomePage.class);
                auth.createEvent(description,token,title.getText().toString(),description.getText().toString(),timings.getText().toString(),duration.getText().toString(),eventType,venue.getText().toString(),getApplicationContext(),constraintLayout,view,myIntent);
            }
        });

    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

    }
}
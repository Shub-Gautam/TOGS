package com.example.tog_s;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class EventDetail extends AppCompatActivity {

//    View view ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event_detail);

        TextView title = (TextView) findViewById(R.id.textView23);
        TextView description = (TextView) findViewById(R.id.textView16);
        TextView timings = (TextView) findViewById(R.id.textView20);
        TextView venue = (TextView) findViewById(R.id.textView17);
        TextView duration = (TextView) findViewById(R.id.textView27);
        TextView eventType = (TextView) findViewById(R.id.textView24);

        Button dynamicBtn = (Button) findViewById(R.id.dynamicbtn);
        Button viewAttendence = (Button) findViewById(R.id.dynamicbtn9);

        SharedPreferences sh = getSharedPreferences("MySharedPref", Context.MODE_PRIVATE);
        String token = sh.getString("token", "");
        String role = sh.getString("rolemain", "");

        Intent myIntent = getIntent();
        String eventId = myIntent.getStringExtra("eventId");

//        String eventId = "63359a86565ef2399319745c";
//        view = findViewById(R.id.eventdetails);
        Auth auth = new Auth();

        if(role.equals("a")){

            auth.getEventDetails(token,dynamicBtn,eventId,title,description,timings,venue,duration,eventType,findViewById(R.id.eventdetails));

            dynamicBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent scan = new Intent(getApplicationContext(),QRScanner.class);
                    scan.putExtra("eventId",eventId);
                    scan.putExtra("token",token);
                    startActivity(scan);
                }
            });


            viewAttendence.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent scan = new Intent(getApplicationContext(),Attendance.class);
                    scan.putExtra("eventId",eventId);
                    scan.putExtra("token",token);
                    startActivity(scan);
                }
            });
        }else{

            dynamicBtn.setVisibility(View.GONE);
//            viewAttendence.setVisibility(View.GONE);
        }






    }
}
package com.example.tog_s;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import com.example.tog_s.card.CardAdapter;
import com.example.tog_s.card.EventData;

import java.util.ArrayList;
import java.util.List;

public class Events extends AppCompatActivity implements CardAdapter.onEventListner {
   final List<EventData> eventsData = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_events);


        RecyclerView recyclerView = findViewById(R.id.recycler);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
//        Button goToCreateEventBtn = (Button) findViewById(R.id.OrganizeEventBtn);
//        Button searchEvent = (Button) findViewById(R.id.SearchEventBtn);

//        goToCreateEventBtn.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Intent intent = new Intent(getApplicationContext(),CreateEvent.class);
//                startActivity(intent);
//            }
//        });
//
//        searchEvent.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Intent myIntent = new Intent(getApplicationContext(),QRScanner.class);
//                startActivity(myIntent);
//            }
//        });

        SharedPreferences sh = getSharedPreferences("MySharedPref", Context.MODE_PRIVATE);
        String token = sh.getString("token", "");



        Auth auth = new Auth();
        auth.getAdminEvents(token,findViewById(R.id.events),recyclerView,eventsData,this);

    }

    @Override
    public void onNoteClick(int position) {
        Intent intent = new Intent(this,EventDetail.class);
        intent.putExtra("eventId",eventsData.get(position).getId());
        startActivity(intent);
    }
}
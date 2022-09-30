package com.example.tog_s;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import com.example.tog_s.card.EventData;

import java.util.ArrayList;
import java.util.List;

public class Attendance extends AppCompatActivity {
    final List<com.example.tog_s.response.Attendance> eventsData = new ArrayList<>();



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_attendance);

        RecyclerView recyclerView = findViewById(R.id.recyclerAttendance);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        SharedPreferences sh = getSharedPreferences("MySharedPref", Context.MODE_PRIVATE);
        String token = sh.getString("token", "");

        Intent m = getIntent();
        String eventId =m.getStringExtra("eventId");
        Auth auth = new Auth();
        auth.getAttendace(token,eventId,findViewById(R.id.attendanceid),getApplicationContext(),recyclerView);
    }
}
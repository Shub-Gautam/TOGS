package com.example.tog_s;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class RegLog extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reg_log);

        Button joinNow = (Button) findViewById(R.id.joinbtn);
        joinNow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent registerIntent = new Intent(getApplicationContext(),Register.class);
                startActivity(registerIntent);
            }
        });

        TextView alreadyMember = (TextView) findViewById(R.id.alreadymember);
        alreadyMember.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent registerIntent = new Intent(getApplicationContext(),login.class);
                startActivity(registerIntent);
            }
        });

        if(getSupportActionBar()!=null){
            getSupportActionBar().hide();
        }
    }
}
package com.example.tog_s;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class HomePage extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_page);

        TextView valu = (TextView) findViewById(R.id.textView3);

        Intent currentIntent = getIntent();
        int error = currentIntent.getIntExtra("LoginError",-1);
        if(error==1){
            valu.setText(error);
        }else{
            valu.setText(error);
        }
    }
}
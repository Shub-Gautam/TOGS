package com.example.tog_s;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class Donate extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_donate);

        Button donate = (Button) findViewById(R.id.loginbtn3);
        EditText amount = (EditText) findViewById(R.id.textInputEditTextAmount);
        EditText message = (EditText) findViewById(R.id.textInputEditTextMessage);

        SharedPreferences sh = getSharedPreferences("MySharedPref", Context.MODE_PRIVATE);
        String token = sh.getString("token", "");

        donate.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                int amt = Integer.parseInt(amount.getText().toString());
                String msg = message.getText().toString();
                Auth auth = new Auth();
                Intent intent = new Intent(getApplicationContext(),menu.class);
                onBackPressed();
                auth.donateFunds(token,findViewById(R.id.donatefund),amt,msg,getApplicationContext(),intent);
            }
        });
    }
}
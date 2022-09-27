package com.example.tog_s;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.EditText;

import com.google.android.material.snackbar.Snackbar;

public class login extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        EditText username = (EditText) findViewById(R.id.textInputEditText);
        EditText password = (EditText) findViewById(R.id.textInputEditText2);

        Auth auth = new Auth();
        int res = auth.getUser(username.getText().toString(),password.getText().toString());

        Intent myIntent = new Intent(getApplicationContext(),HomePage.class);

        if(res==1) {
            myIntent.putExtra("LoginError",res);
            startActivity(myIntent);
        }
        else{
            myIntent.putExtra("LoginError",res);
            startActivity(myIntent);
        }

    }
}
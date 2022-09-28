package com.example.tog_s;

import static java.lang.Thread.sleep;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.android.material.snackbar.Snackbar;

public class login extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        EditText username = (EditText) findViewById(R.id.textInputEditText);
        EditText password = (EditText) findViewById(R.id.textInputEditText2);

        Button loginBtn = (Button) findViewById(R.id.loginbtn);

        loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                    Auth auth = new Auth();
                Intent myIntent = new Intent(getApplicationContext(),HomePage.class);

                auth.getUser(username.getText().toString(),password.getText().toString(),username,getApplicationContext(),myIntent,view);

            }
        });


    }
}
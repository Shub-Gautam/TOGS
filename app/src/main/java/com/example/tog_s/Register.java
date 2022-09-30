package com.example.tog_s;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class Register extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        Button registerBtn = (Button) findViewById(R.id.registerbtn);
        registerBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                EditText email = (EditText) findViewById(R.id.textInputEditTextRegisteremail);
                EditText password = (EditText) findViewById(R.id.editTextTextPassword2);

                Intent myIntent = new Intent(getApplicationContext(),RegisterSecond.class);
                myIntent.putExtra("email",email.getText().toString().trim());
                myIntent.putExtra("password",password.getText().toString().trim());

                startActivity(myIntent);
            }
        });
    }
}
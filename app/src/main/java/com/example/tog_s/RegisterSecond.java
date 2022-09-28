package com.example.tog_s;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class RegisterSecond extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_second);

        Intent myInt = getIntent();
        String email = myInt.getStringExtra("email");
        String password = myInt.getStringExtra("password");


        EditText name = (EditText) findViewById(R.id.textInputEditTextRegisterName);
        EditText username = (EditText) findViewById(R.id.textInputEditTextRegisterUsername);
        EditText phone = (EditText) findViewById(R.id.textInputEditTextRegisterPhone);
        EditText dob = (EditText) findViewById(R.id.textInputEditTextRegisterDOB);
        EditText gender = (EditText) findViewById(R.id.textInputEditTextRegisterGender);

        Button SignUp = (Button) findViewById(R.id.signUpNow);

        SignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(getApplicationContext(),HomePage.class);

                Auth auth = new Auth();
                auth.registerUser(name,email,name.getText().toString(),username.getText().toString(),password,phone.getText().toString(),dob.getText().toString(),gender.getText().toString(),getApplicationContext(),intent,view);
            }
        });




    }
}
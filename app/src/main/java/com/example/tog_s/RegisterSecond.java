package com.example.tog_s;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import com.google.android.material.datepicker.MaterialDatePicker;
import com.google.android.material.datepicker.MaterialPickerOnPositiveButtonClickListener;
import com.google.android.material.textfield.TextInputLayout;


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
        TextInputLayout dobview = (TextInputLayout) findViewById(R.id.outlinedTextFieldRegister14);


        MaterialDatePicker.Builder materialDateBuilder = MaterialDatePicker.Builder.datePicker();
        materialDateBuilder.setTitleText("SELECT A DATE");
        final MaterialDatePicker materialDatePicker = materialDateBuilder.build();

//        dob.setOnFocusChangeListener(new View.OnFocusChangeListener() {
//            @Override
//            public void onFocusChange(View view, boolean b) {
//                materialDatePicker.show(getSupportFragmentManager(), "MATERIAL_DATE_PICKER");
//            }
//        });

//        dobview.

//        dob.add


//        dob.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                materialDatePicker.show(getSupportFragmentManager(), "MATERIAL_DATE_PICKER");
//            }
//        });

//        materialDatePicker.addOnPositiveButtonClickListener(new MaterialPickerOnPositiveButtonClickListener() {
//            @Override
//            public void onPositiveButtonClick(Object selection) {
//                dob.setText(materialDatePicker.getHeaderText());
//            }
//        });
//
//        materialDatePicker.addOnNegativeButtonClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                dob.setText("");
//            }
//        });






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
package com.example.tog_s;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.material.snackbar.Snackbar;
import com.squareup.picasso.Picasso;
import com.squareup.picasso.Picasso;

import java.io.InputStream;
import java.net.URL;


public class DigitalCard extends AppCompatActivity {

    ConstraintLayout constraintLayout ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_digital_card);

        SharedPreferences sh = getSharedPreferences("MySharedPref", Context.MODE_PRIVATE);
        String qrlink = sh.getString("qrCode", "");
        String token = sh.getString("token", "");

        TextView userFullName = (TextView) findViewById(R.id.textView3);
        TextView userPosition = (TextView) findViewById(R.id.textView13);

        constraintLayout = findViewById(R.id.digitalcardid);

        Auth auth = new Auth();
        auth.updateNamePos(token,getApplicationContext(),userFullName,userPosition,constraintLayout);

//        Update QR Code here
        try{
            Snackbar snackbar2 = Snackbar.make(constraintLayout,"Wating for QR Code",Snackbar.LENGTH_LONG);
            snackbar2.show();

            ImageView i = (ImageView) findViewById(R.id.imageView166);
            Picasso.get().load(qrlink).into(i);
//            Bitmap bitmap = BitmapFactory.decodeStream((InputStream)new URL(qrlink).getContent());
//            i.setImageBitmap(bitmap);
            Snackbar snackbar = Snackbar.make(constraintLayout,"QR Fetched",Snackbar.LENGTH_LONG);
            snackbar.show();
        }catch(Exception e){
            Snackbar snackbar = Snackbar.make(constraintLayout,"Error while fetching QR Code",Snackbar.LENGTH_LONG);
            snackbar.show();
        }

    }
}
package com.example.tog_s;

import static android.content.Context.MODE_PRIVATE;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

import androidx.annotation.NonNull;

import com.example.tog_s.response.RegisterResponse;
import com.google.android.material.snackbar.Snackbar;
import com.google.gson.JsonObject;
import com.google.gson.*;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.Map;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Headers;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class Auth {

    private final OkHttpClient client = new OkHttpClient().newBuilder().build();
    public static final MediaType JSON = MediaType.get("application/json; charset=utf-8");
    public void getUser(String username, String password, EditText et, Context context, Intent myIntent,View view) {

        try {
            String url = "https://attend-shubh.herokuapp.com/api/v1/auth/login";
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("email", username);
            jsonObject.put("password", password);
            RequestBody body = RequestBody.create(jsonObject.toString(), JSON);

            Request.Builder builder = new Request.Builder();
            Request request = builder
                    .url(url)
                    .post(body)
                    .build();

            Call call = client.newCall(request);
            Gson gson = new Gson();
            call.enqueue(new Callback() {
                @Override
                public void onFailure(@NonNull Call call, @NonNull IOException e) {
                    Snackbar snackbar = Snackbar.make(view,"Unable to send Request",Snackbar.LENGTH_LONG);
                    snackbar.show();
                }

                @Override
                public void onResponse(@NonNull Call call, @NonNull Response response) throws IOException {
                    try{
                        LoginResponse json = gson.fromJson(response.body().string(), LoginResponse.class);
                        SharedPreferences sharedPreferences = context.getSharedPreferences("MySharedPref",MODE_PRIVATE);
                        SharedPreferences.Editor myEdit = sharedPreferences.edit();
                        myEdit.putString("token",json.getData().getToken());
                        myEdit.commit();
                        myIntent.putExtra("token",json.getData().getToken());
                        myIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        context.startActivity(myIntent);
                    }catch(Exception e){
                        Snackbar snackbar = Snackbar.make(view,"Something Went Wrong",Snackbar.LENGTH_LONG);
                        snackbar.show();
                    }
                }
            });
        }catch(Exception e){
            e.printStackTrace();
        }
    }

    public void registerUser(EditText et, String email, String name, String username, String password, String phone, String dob, String gender, Context context, Intent myIntent, View view){
                try{

                    String url = "https://attend-shubh.herokuapp.com/api/v1/auth/add";
                    JSONObject jsonObject = new JSONObject();
                    jsonObject.put("email", email);
                    jsonObject.put("password", password);
                    jsonObject.put("name", name);
                    jsonObject.put("username", username);
                    jsonObject.put("phone", phone);
                    jsonObject.put("dob", dob);
                    jsonObject.put("gender", gender);
                    RequestBody body = RequestBody.create(jsonObject.toString(), JSON);

                    Request.Builder builder = new Request.Builder();
                    Request request = builder
                            .url(url)
                            .post(body)
                            .build();

                    Call call = client.newCall(request);
                    Gson gson = new Gson();
                    call.enqueue(new Callback() {
                        @Override
                        public void onFailure(@NonNull Call call, @NonNull IOException e) {
                            Snackbar snackbar = Snackbar.make(view,"Unable to send Request",Snackbar.LENGTH_LONG);
                            snackbar.show();
                        }

                        @Override
                        public void onResponse(@NonNull Call call, @NonNull Response response) throws IOException {
                            try{
                                LoginResponse data = gson.fromJson(response.body().string(), LoginResponse.class);
                                SharedPreferences sharedPreferences = context.getSharedPreferences("MySharedPref",MODE_PRIVATE);
                                SharedPreferences.Editor myEdit = sharedPreferences.edit();
                                myEdit.putString("token",data.getData().getToken());
                                myEdit.commit();
                                myIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                                context.startActivity(myIntent);
//                                et.setText(data.toString());
                            }catch(Exception e){
                                Snackbar snackbar = Snackbar.make(view,e.toString(),Snackbar.LENGTH_LONG);
                                snackbar.show();
                            }
                        }
                    });

                }catch (Exception e){

                }
    }
}

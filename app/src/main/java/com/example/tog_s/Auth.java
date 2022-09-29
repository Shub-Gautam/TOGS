package com.example.tog_s;

import static android.content.Context.MODE_PRIVATE;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;

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

    public void updateNamePos(String token, Context context, TextView userFullName, TextView userPosition, View view){
        try{

            userPosition.setText("Loading..");
            userFullName.setText("Loading..");

            String url = "https://attend-shubh.herokuapp.com/api/v1/auth/u";
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("token", token);
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
                        ResponseGetUser data = gson.fromJson(response.body().string(), ResponseGetUser.class);
                        Snackbar snackbar = Snackbar.make(view,data.getMessage(),Snackbar.LENGTH_LONG);
                        snackbar.show();
                        SharedPreferences sharedPreferences = context.getSharedPreferences("MySharedPref",MODE_PRIVATE);
                        SharedPreferences.Editor myEdit = sharedPreferences.edit();
                        myEdit.putString("qrCode",data.getData().getUser().getQrlink());
                        myEdit.commit();
                        userFullName.setText(data.getData().getUser().getName());
                        String role = data.getData().getUser().getRole();
                        String rol ;
                        if(role.equals("s")) rol = "Student" ;
                        else if(role.equals("a")) rol = "Admin";
                        else if(role.equals("v")) rol = "Volunteer";
                        else rol = "Not assigned yet";
                        userPosition.setText(rol);
                    }catch(Exception e){
                        Snackbar snackbar = Snackbar.make(view,e.toString(),Snackbar.LENGTH_LONG);
                        snackbar.show();
                    }
                }
            });

        }catch (Exception e){
            Snackbar snackbar = Snackbar.make(view,e.toString(),Snackbar.LENGTH_LONG);
            snackbar.show();
        }
    }

    public void test(){
        try{

            String url = "https://attend-shubh.herokuapp.com/test";
            Request.Builder builder = new Request.Builder();
            Request request = builder
                    .url(url)
                    .build();

            Call call = client.newCall(request);
            call.enqueue(new Callback() {
                @Override
                public void onFailure(@NonNull Call call, @NonNull IOException e) {
                }

                @Override
                public void onResponse(@NonNull Call call, @NonNull Response response) throws IOException {
                }
            });

        }catch (Exception e){

        }
    }

    public void createEvent(String token, String title, String description, String timings, String duration, String eventType,String venue, Context applicationContext, ConstraintLayout constraintLayout,View view,Intent myIntent) {

        try{

        String url = "https://attend-shubh.herokuapp.com/api/v1/class/add/event";
        JSONObject jsonObject = new JSONObject();
            jsonObject.put("token",token);
            jsonObject.put("Title", title);
            jsonObject.put("Description", description);
            jsonObject.put("Timings", timings);
            jsonObject.put("Duration", duration);
            jsonObject.put("Venue", venue);
            jsonObject.put("EventType", eventType);
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
                    ResponseGetUser data = gson.fromJson(response.body().string(), ResponseGetUser.class);
                    Snackbar snackbar = Snackbar.make(view,data.getMessage(),Snackbar.LENGTH_LONG);
                    snackbar.show();
//                    SharedPreferences sharedPreferences = applicationContext.getSharedPreferences("MySharedPref",MODE_PRIVATE);
//                    SharedPreferences.Editor myEdit = sharedPreferences.edit();
//                    myEdit.putString("qrCode",data.getData().getUser().getQrlink());
//                    myEdit.commit();
//                    userFullName.setText(data.getData().getUser().getName());
//                    String role = data.getData().getUser().getRole();
//                    String rol ;
//                    if(role.equals("s")) rol = "Student" ;
//                    else if(role.equals("a")) rol = "Admin";
//                    else if(role.equals("v")) rol = "Volunteer";
//                    else rol = "Not assigned yet";
//                    userPosition.setText(rol);
                    myIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    applicationContext.startActivity(myIntent);
                }catch(Exception e){
                    Snackbar snackbar = Snackbar.make(view,e.toString(),Snackbar.LENGTH_LONG);
                    snackbar.show();
                }
            }
        });

    }catch (Exception e){
        Snackbar snackbar = Snackbar.make(view,e.toString(),Snackbar.LENGTH_LONG);
        snackbar.show();
    }

    }

    public void markAttendance(String token, String studentId,String eventId, Context applicationContext, ConstraintLayout constraintLayout,View view,Intent myIntent) {

        try{

            String url = "https://attend-shubh.herokuapp.com/api/v1/class/add/student";
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("token",token);
            jsonObject.put("studentId", studentId);
            jsonObject.put("eventId", eventId);
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
                        ResponseGetUser data = gson.fromJson(response.body().string(), ResponseGetUser.class);
                        Snackbar snackbar = Snackbar.make(view,data.getMessage(),Snackbar.LENGTH_LONG);
                        snackbar.show();
//                    SharedPreferences sharedPreferences = applicationContext.getSharedPreferences("MySharedPref",MODE_PRIVATE);
//                    SharedPreferences.Editor myEdit = sharedPreferences.edit();
//                    myEdit.putString("qrCode",data.getData().getUser().getQrlink());
//                    myEdit.commit();
//                    userFullName.setText(data.getData().getUser().getName());
//                    String role = data.getData().getUser().getRole();
//                    String rol ;
//                    if(role.equals("s")) rol = "Student" ;
//                    else if(role.equals("a")) rol = "Admin";
//                    else if(role.equals("v")) rol = "Volunteer";
//                    else rol = "Not assigned yet";
//                    userPosition.setText(rol);
                        myIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        applicationContext.startActivity(myIntent);
                    }catch(Exception e){
                        Snackbar snackbar = Snackbar.make(view,e.toString(),Snackbar.LENGTH_LONG);
                        snackbar.show();
                    }
                }
            });

        }catch (Exception e){
            Snackbar snackbar = Snackbar.make(view,e.toString(),Snackbar.LENGTH_LONG);
            snackbar.show();
        }

    }

}

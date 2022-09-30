package com.example.tog_s;

import static android.content.Context.MODE_PRIVATE;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.example.tog_s.card.CardAdapter;
import com.example.tog_s.card.EventData;
import com.example.tog_s.card.EventResponse;
import com.example.tog_s.response.OneEventResponse;
import com.google.android.material.snackbar.Snackbar;
import com.google.gson.*;

import org.json.JSONObject;

import java.io.IOException;
import java.util.List;

import okhttp3.Call;
import okhttp3.Callback;
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

    public void createEvent(EditText et ,String token, String title, String description, String timings, String duration, String eventType,String venue, Context applicationContext, ConstraintLayout constraintLayout,View view,Intent myIntent) {

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
                    myIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    applicationContext.startActivity(myIntent);
                    Snackbar snackbar = Snackbar.make(view,data.getMessage(),Snackbar.LENGTH_LONG);
                    snackbar.show();
                }catch(Exception e){
                    et.setText(e.toString());
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

    public void markAttendance(String token, String studentId, String eventId, Context applicationContext, View view, Intent da) {

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
                        da.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        applicationContext.startActivity(da);
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

    public void getEventDetails(String token, Button dynamicBtn, String eventId, TextView title, TextView description, TextView timings, TextView venue, TextView duration, TextView eventType, View view){

        title.setText("Loading..");
        description.setText("Loading..");
        timings.setText("Loading..");
        venue.setText("Loading..");
        duration.setText("Loading..");
        eventType.setText("Loading..");


        try{
            String url = "https://attend-shubh.herokuapp.com/api/v1/class/fetch/event";
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("token",token);
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
                        OneEventResponse data = gson.fromJson(response.body().string(), OneEventResponse.class);
                        Snackbar snackbar = Snackbar.make(view,data.getMessage(),Snackbar.LENGTH_LONG);
                        snackbar.show();



                        title.setText(data.getData().getEventData().getTitle());
                        description.setText(data.getData().getEventData().getDescription());
                        timings.setText(data.getData().getEventData().getTimings());
                        venue.setText(data.getData().getEventData().getVenue());
                        duration.setText(data.getData().getEventData().getDuration());
                        eventType.setText(data.getData().getEventData().getEventType());

//                        myIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//                        applicationContext.startActivity(myIntent);
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

    public void getAdminEvents(String token, View view, RecyclerView recview, List<EventData> eventsData, CardAdapter.onEventListner ct){

        try{
            String url = "https://attend-shubh.herokuapp.com/api/v1/class/fetch/events";
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("token",token);
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
                        EventResponse data = gson.fromJson(response.body().string(), EventResponse.class);
                        Snackbar snackbar = Snackbar.make(view,data.getMessage(),Snackbar.LENGTH_LONG);
                        snackbar.show();
                        List<EventData> ed = data.getData().getEventData();
                        for (int i = 0; i < data.getData().getEventData().size(); i++) {
                            eventsData.add(data.getData().getEventData().get(i));
                        }
//                        eventDataList = data.getData().getEventData();
                        CardAdapter cardAdapter = new CardAdapter(data.getData().getEventData(),ct);
                        recview.setAdapter(cardAdapter);
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

    public void donateFunds(String token, View view,int amount, String note,Context getApplication,Intent myIntent){

        try{
            String url = "https://attend-shubh.herokuapp.com/api/v1/fund/donate";
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("token",token);
            jsonObject.put("amount",amount);
            jsonObject.put("note",note);
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
//                        EventResponse data = gson.fromJson(response.body().string(), EventResponse.class);
//                        Snackbar snackbar = Snackbar.make(view,"Donation successfull",Snackbar.LENGTH_LONG);
//                        snackbar.show();


//                        myIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//                        getApplication.startActivity(myIntent);


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

    public void getFunds(String token, View view){

        try{
            String url = "https://attend-shubh.herokuapp.com/api/v1/fund/fetch";
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("token",token);
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
//                        EventResponse data = gson.fromJson(response.body().string(), EventResponse.class);
//                        Snackbar snackbar = Snackbar.make(view,data.getMessage(),Snackbar.LENGTH_LONG);
//                        snackbar.show();
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

    public void getAttendace(String token,String eventId, View view){

        try{
            String url = "https://attend-shubh.herokuapp.com/api/v1/class/attendance/";
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("token",token);
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
//                        EventResponse data = gson.fromJson(response.body().string(), EventResponse.class);
//                        Snackbar snackbar = Snackbar.make(view,data.getMessage(),Snackbar.LENGTH_LONG);
//                        snackbar.show();
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

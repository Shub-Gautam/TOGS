package com.example.tog_s;

import java.io.IOException;

import okhttp3.Headers;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class Auth {

    private final OkHttpClient client = new OkHttpClient();

    public int getUser(String username, String password) {
        try{
            Request request = new Request.Builder()
                    .url("https:localhost:9072/api/v1/auth/user")
                    .build();
            try (Response response = client.newCall(request).execute()) {
                if (!response.isSuccessful()) return 0 ;
                System.out.println(response.body().string());
            }
        }catch(Exception e){
            return 0 ;
        }
        return 1 ;
    }

}

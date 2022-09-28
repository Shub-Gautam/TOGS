package com.example.tog_s.response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Data2 {

    @SerializedName("userID")
    @Expose
    private String userID;
    @SerializedName("token")
    @Expose
    private String token;

    public String getUserID() {
        return userID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(Data2.class.getName()).append('@').append(Integer.toHexString(System.identityHashCode(this))).append('[');
        sb.append("userID");
        sb.append('=');
        sb.append(((this.userID == null)?"<null>":this.userID));
        sb.append(',');
        sb.append("token");
        sb.append('=');
        sb.append(((this.token == null)?"<null>":this.token));
        sb.append(',');
        if (sb.charAt((sb.length()- 1)) == ',') {
            sb.setCharAt((sb.length()- 1), ']');
        } else {
            sb.append(']');
        }
        return sb.toString();
    }
}



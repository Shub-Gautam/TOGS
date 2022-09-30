package com.example.tog_s.response;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class DataA {

    @SerializedName("Attendence")
    @Expose
    private List<Attendance> attendence = null;

    public List<Attendance> getAttendence() {
        return attendence;
    }

    public void setAttendence(List<Attendance> attendence) {
        this.attendence = attendence;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(DataA.class.getName()).append('@').append(Integer.toHexString(System.identityHashCode(this))).append('[');
        sb.append("attendence");
        sb.append('=');
        sb.append(((this.attendence == null)?"<null>":this.attendence));
        sb.append(',');
        if (sb.charAt((sb.length()- 1)) == ',') {
            sb.setCharAt((sb.length()- 1), ']');
        } else {
            sb.append(']');
        }
        return sb.toString();
    }

}

package com.example.tog_s.response;

import com.example.tog_s.card.DataE;
import com.example.tog_s.card.EventData;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class DataOne {
    @SerializedName("EventData")
    @Expose
    private EventData eventData;

    public EventData getEventData() {
        return eventData;
    }

    public void setEventData(EventData eventData) {
        this.eventData = eventData;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(DataOne.class.getName()).append('@').append(Integer.toHexString(System.identityHashCode(this))).append('[');
        sb.append("eventData");
        sb.append('=');
        sb.append(((this.eventData == null)?"<null>":this.eventData));
        sb.append(',');
        if (sb.charAt((sb.length()- 1)) == ',') {
            sb.setCharAt((sb.length()- 1), ']');
        } else {
            sb.append(']');
        }
        return sb.toString();
    }
}

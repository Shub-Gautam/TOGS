package com.example.tog_s.card;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class EventData {

@SerializedName("_id")
@Expose
private String id;
@SerializedName("Title")
@Expose
private String title;
@SerializedName("Venue")
@Expose
private String venue;
@SerializedName("Description")
@Expose
private String description;
@SerializedName("Organizer")
@Expose
private String organizer;
@SerializedName("Duration")
@Expose
private String duration;
@SerializedName("EventType")
@Expose
private String eventType;
@SerializedName("Timings")
@Expose
private String timings;
@SerializedName("createdAt")
@Expose
private String createdAt;
@SerializedName("updatedAt")
@Expose
private String updatedAt;
@SerializedName("__v")
@Expose
private Integer v;

public String getId() {
return id;
}

public void setId(String id) {
this.id = id;
}

public String getTitle() {
return title;
}

public void setTitle(String title) {
this.title = title;
}

public String getVenue() {
return venue;
}

public void setVenue(String venue) {
this.venue = venue;
}

public String getDescription() {
return description;
}

public void setDescription(String description) {
this.description = description;
}

public String getOrganizer() {
return organizer;
}

public void setOrganizer(String organizer) {
this.organizer = organizer;
}

public String getDuration() {
return duration;
}

public void setDuration(String duration) {
this.duration = duration;
}

public String getEventType() {
return eventType;
}

public void setEventType(String eventType) {
this.eventType = eventType;
}

public String getTimings() {
return timings;
}

public void setTimings(String timings) {
this.timings = timings;
}

public String getCreatedAt() {
return createdAt;
}

public void setCreatedAt(String createdAt) {
this.createdAt = createdAt;
}

public String getUpdatedAt() {
return updatedAt;
}

public void setUpdatedAt(String updatedAt) {
this.updatedAt = updatedAt;
}

public Integer getV() {
return v;
}

public void setV(Integer v) {
this.v = v;
}

@Override
public String toString() {
StringBuilder sb = new StringBuilder();
sb.append(EventData.class.getName()).append('@').append(Integer.toHexString(System.identityHashCode(this))).append('[');
sb.append("id");
sb.append('=');
sb.append(((this.id == null)?"<null>":this.id));
sb.append(',');
sb.append("title");
sb.append('=');
sb.append(((this.title == null)?"<null>":this.title));
sb.append(',');
sb.append("venue");
sb.append('=');
sb.append(((this.venue == null)?"<null>":this.venue));
sb.append(',');
sb.append("description");
sb.append('=');
sb.append(((this.description == null)?"<null>":this.description));
sb.append(',');
sb.append("organizer");
sb.append('=');
sb.append(((this.organizer == null)?"<null>":this.organizer));
sb.append(',');
sb.append("duration");
sb.append('=');
sb.append(((this.duration == null)?"<null>":this.duration));
sb.append(',');
sb.append("eventType");
sb.append('=');
sb.append(((this.eventType == null)?"<null>":this.eventType));
sb.append(',');
sb.append("timings");
sb.append('=');
sb.append(((this.timings == null)?"<null>":this.timings));
sb.append(',');
sb.append("createdAt");
sb.append('=');
sb.append(((this.createdAt == null)?"<null>":this.createdAt));
sb.append(',');
sb.append("updatedAt");
sb.append('=');
sb.append(((this.updatedAt == null)?"<null>":this.updatedAt));
sb.append(',');
sb.append("v");
sb.append('=');
sb.append(((this.v == null)?"<null>":this.v));
sb.append(',');
if (sb.charAt((sb.length()- 1)) == ',') {
sb.setCharAt((sb.length()- 1), ']');
} else {
sb.append(']');
}
return sb.toString();
}

}
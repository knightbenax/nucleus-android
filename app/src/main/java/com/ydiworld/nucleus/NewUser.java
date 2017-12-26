package com.ydiworld.nucleus;

/**
 * Created by sammy on 12/24/17.
 */

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class NewUser {

    @SerializedName("success")
    @Expose
    private Boolean success;

    @SerializedName("reason")
    @Expose
    private String reason;

    @SerializedName("tribe")
    @Expose
    private String tribe;

    @SerializedName("last_insert_id")
    @Expose
    private String last_insert_id;

    @SerializedName("events")
    @Expose
    private List<Event> events = null;
    @SerializedName("officials")
    @Expose
    private List<Official> officials = null;

    public Boolean getStatus() {
        return success;
    }

    public String getReason() {
        return reason;
    }

    public String getTribe() {
        return tribe;
    }

    public String getID() {
        return last_insert_id;
    }

    public void setSuccess(Boolean success) {
        this.success = success;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public List<Event> getEvents() {
        return events;
    }

    public void setEvents(List<Event> events) {
        this.events = events;
    }

    public List<Official> getOfficials() {
        return officials;
    }

    public void setOfficials(List<Official> officials) {
        this.officials = officials;
    }

}

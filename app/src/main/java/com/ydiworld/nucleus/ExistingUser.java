package com.ydiworld.nucleus;

/**
 * Created by sammy on 12/24/17.
 */

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ExistingUser {

    @SerializedName("success")
    @Expose
    private Boolean success;

    @SerializedName("reason")
    @Expose
    private String reason;

    @SerializedName("participant")
    @Expose
    private Participant participant;

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

    public void setSuccess(Boolean success) {
        this.success = success;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public Participant getParticipant() {
        return participant;
    }

    public void setParticipant(Participant participant) {
        this.participant = participant;
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

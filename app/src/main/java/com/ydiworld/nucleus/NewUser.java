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
    @SerializedName("participant")
    @Expose
    private Participant participant;
    @SerializedName("events")
    @Expose
    private List<Event> events = null;
    @SerializedName("officials")
    @Expose
    private List<Official> officials = null;

    public Boolean getSuccess() {
        return success;
    }

    public void setSuccess(Boolean success) {
        this.success = success;
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

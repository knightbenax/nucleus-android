package com.ydiworld.nucleus;

/**
 * Created by sammy on 12/24/17.
 */

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Content {

    @SerializedName("time")
    @Expose
    private String time;
    @SerializedName("event")
    @Expose
    private String event;

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getEvent() {
        return event;
    }

    public void setEvent(String event) {
        this.event = event;
    }

}

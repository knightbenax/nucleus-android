package com.ydiworld.nucleus;

/**
 * Created by sammy on 12/24/17.
 */

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Event {

    @SerializedName("date")
    @Expose
    private String date;
    @SerializedName("content")
    @Expose
    private List<Content> content = null;

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public List<Content> getContent() {
        return content;
    }

    public void setContent(List<Content> content) {
        this.content = content;
    }

}
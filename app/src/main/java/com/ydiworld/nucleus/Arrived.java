package com.ydiworld.nucleus;

/**
 * Created by sammy on 12/24/17.
 */

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Arrived {

    @SerializedName("success")
    @Expose
    private Boolean success;

    public Boolean getStatus() {
        return success;
    }

    public void setSuccess(Boolean success) {
        this.success = success;
    }



}

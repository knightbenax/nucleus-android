package com.ydiworld.nucleus;

/**
 * Created by sammy on 12/24/17.
 */

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Participant {

    @SerializedName("ID")
    @Expose
    private Integer iD;
    @SerializedName("Fullname")
    @Expose
    private String fullname;
    @SerializedName("Phone")
    @Expose
    private String phone;
    @SerializedName("Email")
    @Expose
    private String email;
    @SerializedName("Hear about Camp")
    @Expose
    private String hearAboutCamp;
    @SerializedName("Career")
    @Expose
    private String career;
    @SerializedName("First time at Camp")
    @Expose
    private String firstTimeAtCamp;
    @SerializedName("Date Registered")
    @Expose
    private String dateRegistered;
    @SerializedName("Tribe")
    @Expose
    private String tribe;
    @SerializedName("Gender")
    @Expose
    private String gender;
    @SerializedName("Status")
    @Expose
    private String status;
    @SerializedName("Reg Mode")
    @Expose
    private String regMode;
    @SerializedName("created_at")
    @Expose
    private String createdAt;
    @SerializedName("updated_at")
    @Expose
    private String updatedAt;

    public Integer getID() {
        return iD;
    }

    public void setID(Integer iD) {
        this.iD = iD;
    }

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getHearAboutCamp() {
        return hearAboutCamp;
    }

    public void setHearAboutCamp(String hearAboutCamp) {
        this.hearAboutCamp = hearAboutCamp;
    }

    public String getCareer() {
        return career;
    }

    public void setCareer(String career) {
        this.career = career;
    }

    public String getFirstTimeAtCamp() {
        return firstTimeAtCamp;
    }

    public void setFirstTimeAtCamp(String firstTimeAtCamp) {
        this.firstTimeAtCamp = firstTimeAtCamp;
    }

    public String getDateRegistered() {
        return dateRegistered;
    }

    public void setDateRegistered(String dateRegistered) {
        this.dateRegistered = dateRegistered;
    }

    public String getTribe() {
        return tribe;
    }

    public void setTribe(String tribe) {
        this.tribe = tribe;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getRegMode() {
        return regMode;
    }

    public void setRegMode(String regMode) {
        this.regMode = regMode;
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

    @Override
    public String toString() {
        return "Participant{" +
                "iD=" + iD +
                ", fullname='" + fullname + '\'' +
                ", phone='" + phone + '\'' +
                ", email='" + email + '\'' +
                ", hearAboutCamp='" + hearAboutCamp + '\'' +
                ", career='" + career + '\'' +
                ", firstTimeAtCamp='" + firstTimeAtCamp + '\'' +
                ", dateRegistered='" + dateRegistered + '\'' +
                ", tribe='" + tribe + '\'' +
                ", gender='" + gender + '\'' +
                ", status='" + status + '\'' +
                ", regMode='" + regMode + '\'' +
                ", createdAt='" + createdAt + '\'' +
                ", updatedAt='" + updatedAt + '\'' +
                '}';
    }
}


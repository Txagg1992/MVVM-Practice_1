package com.curiousapps.mvvm_practice.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class TypiCode {

    @SerializedName("id")
    @Expose
    private int id;
    @SerializedName("username")
    @Expose
    private String userName;

    public TypiCode(int id, String userName) {
        this.id = id;
        this.userName = userName;
    }

    public TypiCode() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    @Override
    public String toString() {
        return "TypiCode{" +
                "id=" + id +
                ", userName='" + userName + '\'' +
                '}';
    }
}

package com.example.managerapp.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ListUserModel {
    @SerializedName("email")
    @Expose
    public String email;
    @SerializedName("username")
    @Expose
    public String username;

    @SerializedName("status")
    @Expose
    public Boolean status;

    public ListUserModel(String email,String username, Boolean status){
        this.email = email;
        this.username = username;
        this.status = status;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }
}

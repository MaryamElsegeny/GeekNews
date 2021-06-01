package com.example.geeknews.models;

import com.google.gson.annotations.SerializedName;

public class NotficationModel {
    @SerializedName("registration_id")
    private String deviceToken ;
    private String type ;

    public NotficationModel(String deviceToken, String type) {
        this.deviceToken = deviceToken;
        this.type = type;
    }

    public String getDeviceToken() {
        return deviceToken;
    }

    public void setDeviceToken(String deviceToken) {
        this.deviceToken = deviceToken;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}

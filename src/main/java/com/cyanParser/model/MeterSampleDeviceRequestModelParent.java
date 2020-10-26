package com.cyanParser.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class MeterSampleDeviceRequestModelParent {
    @SerializedName("request")
    @Expose
    private MeterSampleDeviceRequestModel request;

    public MeterSampleDeviceRequestModel getRequest() {
        return request;
    }

    public void setRequest(MeterSampleDeviceRequestModel request) {
        this.request = request;
    }
}
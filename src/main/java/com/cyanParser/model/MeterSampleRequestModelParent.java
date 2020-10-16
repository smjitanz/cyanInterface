package com.cyanParser.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class MeterSampleRequestModelParent {
    @SerializedName("request")
    @Expose
    private MeterSampleRequestModel request;

    public MeterSampleRequestModel getRequest() {
        return request;
    }

    public void setRequest(MeterSampleRequestModel request) {
        this.request = request;
    }
}

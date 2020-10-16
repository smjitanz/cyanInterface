package com.cyanParser.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class MeterSampleRequestModel {
    @SerializedName("startId")
    @Expose
    private String startId;
    @SerializedName("count")
    @Expose
    private String count;

    public String getStartId() {
        return startId;
    }

    public void setStartId(String startId) {
        this.startId = startId;
    }

    public String getCount() {
        return count;
    }

    public void setCount(String count) {
        this.count = count;
    }

}


package com.cyanParser.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class MeterSampleDeviceRequestModel {
    @SerializedName("startId")
    @Expose
    private String startId;
    @SerializedName("count")
    @Expose
    private String count;

    public String getDevice() {
        return device;
    }

    public void setDevice(String device) {
        this.device = device;
    }

    @SerializedName("device")
    @Expose
    private String device;

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

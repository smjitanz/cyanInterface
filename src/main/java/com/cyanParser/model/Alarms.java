package com.cyanParser.model;

import java.util.ArrayList;

public class Alarms {

    private String meterId;
    private String type;
    private String alarmTime;
    private String previousNodeId;
    ArrayList<String> alarmActive = new ArrayList<String>();

    public Alarms(String meterId, String type, String alarmTime, String previousNodeId, ArrayList<String> alarmActive) {
        this.meterId = meterId;
        this.type = type;
        this.alarmTime = alarmTime;
        this.previousNodeId = previousNodeId;
        this.alarmActive = alarmActive;
    }

    public String getMeterId() {
        return meterId;
    }

    public void setMeterId(String meterId) {
        this.meterId = meterId;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getAlarmTime() {
        return alarmTime;
    }

    public void setAlarmTime(String alarmTime) {
        this.alarmTime = alarmTime;
    }

    public String getPreviousNodeId() {
        return previousNodeId;
    }

    public void setPreviousNodeId(String previousNodeId) {
        this.previousNodeId = previousNodeId;
    }

    public ArrayList<String> getAlarmActive() {
        return alarmActive;
    }

    public void setAlarmActive(ArrayList<String> alarmActive) {
        this.alarmActive = alarmActive;
    }

}

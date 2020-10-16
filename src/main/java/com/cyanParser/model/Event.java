package com.cyanParser.model;

import java.util.ArrayList;

public class Event {
   private String meterId;
    private String type;
    private String eventTime;
    private String previousNodeId;

    // Getter Methods

    public String getMeterId() {
        return meterId;
    }

    public String getType() {
        return type;
    }

    public String getEventTime() {
        return eventTime;
    }

    public String getPreviousNodeId() {
        return previousNodeId;
    }

    // Setter Methods

    public void setMeterId(String meterId) {
        this.meterId = meterId;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setEventTime(String eventTime) {
        this.eventTime = eventTime;
    }

    public void setPreviousNodeId(String previousNodeId) {
        this.previousNodeId = previousNodeId;
    }
}

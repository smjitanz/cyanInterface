package com.cyanParser.model;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

public class ListAlarm {
    public List<Alarms> events = new CopyOnWriteArrayList<Alarms>();
}

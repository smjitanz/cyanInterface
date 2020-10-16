package com.cyanParser.model;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

public class ListEvent {
    public List<Event> events = new CopyOnWriteArrayList<Event>();
}

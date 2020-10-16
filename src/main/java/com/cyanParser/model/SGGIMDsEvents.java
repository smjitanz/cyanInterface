package com.cyanParser.model;


import javax.xml.bind.annotation.*;
import java.util.ArrayList;

@XmlRootElement(name = "SGGIMDsEvents")
@XmlAccessorType(XmlAccessType.FIELD)
public class SGGIMDsEvents {
    @XmlElement(name = "D1-DeviceEventSeeder")
    ArrayList<DeviceEventSeeder> events = new ArrayList<DeviceEventSeeder>();

    public ArrayList<D1InitialLoadIMD> getDIMD() {
        return DIMD;
    }

    public void setDIMD(ArrayList<D1InitialLoadIMD> DIMD) {
        this.DIMD = DIMD;
    }

    @XmlElement(name = "D1-InitialLoadIMD")
    ArrayList<D1InitialLoadIMD> DIMD = new ArrayList<D1InitialLoadIMD>();

    @XmlAttribute(name = "xmlns")
    private String value="http://oracle.com/SGGIMDsEvents";


    //Getter
    public ArrayList<DeviceEventSeeder> getEvents() {
        return events;
    }

    public String getValue() {
        return value;
    }


    //Setter
    public void setValue(String value) {
        this.value = value;
    }

    public void setEvents(ArrayList<DeviceEventSeeder> events) {
        this.events = events;
    }
}

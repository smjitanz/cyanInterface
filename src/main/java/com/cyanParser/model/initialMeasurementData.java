package com.cyanParser.model;


import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.ArrayList;

@XmlRootElement(name="initialMeasurementData")
@XmlAccessorType(XmlAccessType.FIELD)
public class initialMeasurementData {
    @XmlElement(name = "preVEE")
    ArrayList<preVEE> preVEES = new ArrayList<preVEE>();

    public initialMeasurementData() {
        //preVEES.add(new preVEE());
    }

    public void addpreVEE(com.cyanParser.model.preVEE preVEE){
        this.preVEES.add(preVEE);
    }


    public ArrayList<preVEE> getPreVEES() {
        return preVEES;
    }

    @Override
    public String toString() {
        return "initialMeasurementData{" +
                "preVEES=" + preVEES +
                '}';
    }

    public void setPreVEES(ArrayList<preVEE> preVEES) {
        this.preVEES = preVEES;
    }
}

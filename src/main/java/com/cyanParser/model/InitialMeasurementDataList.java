package com.cyanParser.model;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.ArrayList;

@XmlRootElement(name="initialMeasurementDataList")
@XmlAccessorType(XmlAccessType.FIELD)
public  class InitialMeasurementDataList {
    @XmlElement(name = "initialMeasurementData")
    ArrayList<initialMeasurementData> initMeasurementData = new ArrayList<initialMeasurementData>();

    public InitialMeasurementDataList() {

    }

    public void addIMDData(initialMeasurementData imdData){
        initMeasurementData.add(imdData);
    }

    public InitialMeasurementDataList(ArrayList<initialMeasurementData> preVEES) {
        this.initMeasurementData = preVEES;
    }



    public ArrayList<initialMeasurementData> getInitMeasurementData() {
        return initMeasurementData;
    }

    public void setInitMeasurementData(ArrayList<initialMeasurementData> initMeasurementData) {
        this.initMeasurementData = initMeasurementData;
    }

    @Override
    public String toString() {
        return "InitialMeasurementData{" +
                "preVEES=" + initMeasurementData +
                '}';
    }
}

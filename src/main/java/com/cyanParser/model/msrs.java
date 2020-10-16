package com.cyanParser.model;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.ArrayList;

@XmlRootElement(name="msrs")
@XmlAccessorType(XmlAccessType.FIELD)
public class msrs {

    @XmlElement(name = "mL")
    ArrayList<mL> mL = new ArrayList<mL>();

    public msrs(ArrayList<com.cyanParser.model.mL> mL) {
        this.mL = mL;
    }

    public msrs() {
    }

    @Override
    public String toString() {
        return "msrs{" +
                "mL=" + mL +
                '}';
    }

    public ArrayList<com.cyanParser.model.mL> getmL() {
        return mL;
    }

    public void setmL(ArrayList<com.cyanParser.model.mL> mL) {
        this.mL = mL;
    }

    public void addML(com.cyanParser.model.mL mL){
        this.mL.add(mL);

    }
}

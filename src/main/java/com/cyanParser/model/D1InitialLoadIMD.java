package com.cyanParser.model;


import javax.xml.bind.annotation.*;
import java.util.ArrayList;

@XmlRootElement(name="D1-InitialLoadIMD")
@XmlAccessorType(XmlAccessType.FIELD)
public class D1InitialLoadIMD {

    @XmlAttribute(name = "xmlns")
    private String value="";
    @XmlAttribute(name = "dateTimeTagFormat")
    private String format="xsd";

    public preVEE getPreVEES() {
        return preVEES;
    }

    public void setPreVEES(preVEE preVEES) {
        this.preVEES = preVEES;
    }

    @XmlElement(name = "preVEE")
    preVEE preVEES;

    public String getServiceProviderExternalId() {
        return serviceProviderExternalId;
    }

    public void setServiceProviderExternalId(String serviceProviderExternalId) {
        this.serviceProviderExternalId = serviceProviderExternalId;
    }

    @XmlElement(name = "serviceProviderExternalId")
    String serviceProviderExternalId;

    public D1InitialLoadIMD() {
    }
    public D1InitialLoadIMD(preVEE preVEES ) {
        this.preVEES=preVEES;
    }
}



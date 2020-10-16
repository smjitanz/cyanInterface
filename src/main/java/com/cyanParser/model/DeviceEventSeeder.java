package com.cyanParser.model;


import javax.xml.bind.annotation.*;

@XmlRootElement(name="D1-DeviceEventSeeder")
@XmlAccessorType(XmlAccessType.FIELD)
public class DeviceEventSeeder {

    @XmlAttribute(name = "xmlns")
    private String value="";
    @XmlAttribute(name = "dateTimeTagFormat")
    private String format="xsd";

    @XmlElement(name = "externalSourceIdentifier")
    private String externalSourceIdentifier="";
    @XmlElement(name = "externalSenderId")
    private String externalSenderId;
    @XmlElement(name = "deviceIdentifierNumber")
    private String deviceIdentifierNumber;
    @XmlElement(name = "externalEventName")
    private String externalEventName;
    @XmlElement(name = "eventDateTime")
    private String eventDateTime;


// Getter Methods

    public String getExternalSourceIdentifier() { return externalSourceIdentifier; }
    public String getExternalSenderId() { return externalSenderId; }
    public String getDeviceIdentifierNumber() { return deviceIdentifierNumber; }
    public String getExternalEventName() { return externalEventName; }
    public String getEventDateTime() { return eventDateTime; }
    public String getValue() {
        return value;
    }
    public String getFormat() {
        return format;
    }


    // Setter Methods
    public void setValue(String value) {
        this.value = value;
    }
    public void setFormat(String format) {
        this.format = format;
    }

    public void setExternalSourceIdentifier(String externalSourceIdentifier) {
        this.externalSourceIdentifier = externalSourceIdentifier;
    }
    public void setExternalSenderId(String externalSenderId) { this.externalSenderId = externalSenderId; }
    public void setDeviceIdentifierNumber(String deviceIdentifierNumber) { this.deviceIdentifierNumber = deviceIdentifierNumber; }
    public void setExternalEventName(String externalEventName) { this.externalEventName = externalEventName; }
    public void setEventDateTime(String eventDateTime) { this.eventDateTime = eventDateTime; }
}



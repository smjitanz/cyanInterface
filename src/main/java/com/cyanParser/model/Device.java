package com.cyanParser.model;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.ArrayList;

@XmlRootElement(name="device")
@XmlAccessorType(XmlAccessType.FIELD)
public class Device{
	@XmlElement(name = "headEnd")
	private String headEnd;
	@XmlElement(name = "headEndExternalId")
	private String headEndExternalId;
	@XmlElement(name = "deviceId")
	private String deviceId= "";
	@XmlElement(name = "deviceIdentifierNumber")
	private String deviceIdentifierNumber;
	@XmlElement(name = "initialMeasurementDataList")
	com.cyanParser.model.InitialMeasurementDataList InitialMeasurementDataList = new InitialMeasurementDataList();

	@Override
	public String toString() {
		return "Device{" +
				"headEnd='" + headEnd + '\'' +
				", headEndExternalId='" + headEndExternalId + '\'' +
				", deviceId='" + deviceId + '\'' +
				", deviceIdentifierNumber='" + deviceIdentifierNumber + '\'' +
				", InitialMeasurementDataList=" + InitialMeasurementDataList +
				'}';
	}

	public Device(String headEnd, String headEndExternalId, String deviceId, String deviceIdentifierNumber, com.cyanParser.model.InitialMeasurementDataList initialMeasurementDataList) {
		this.headEnd = headEnd;
		this.headEndExternalId = headEndExternalId;
		this.deviceId = deviceId;
		this.deviceIdentifierNumber = deviceIdentifierNumber;
		InitialMeasurementDataList = initialMeasurementDataList;
	}

	public com.cyanParser.model.InitialMeasurementDataList getInitialMeasurementDataList() {
		return InitialMeasurementDataList;
	}

	public void setInitialMeasurementDataList(com.cyanParser.model.InitialMeasurementDataList initialMeasurementDataList) {
		InitialMeasurementDataList = initialMeasurementDataList;
	}

	public Device() {
	}

// Getter Methods

	public String getHeadEnd() { return headEnd; }
	public String getHeadEndExternalId() { return headEndExternalId; }
	public String getDeviceId() { return deviceId; }
	public String getDeviceIdentifierNumber() { return deviceIdentifierNumber; }


	// Setter Methods

	public void setHeadEnd(String headEnd) {
		this.headEnd = headEnd;
	}
	public void setHeadEndExternalId(String headEndExternalId) { this.headEndExternalId = headEndExternalId; }
	public void setDeviceId(String deviceId) { this.deviceId = deviceId; }
	public void setDeviceIdentifierNumber(String deviceIdentifierNumber) { this.deviceIdentifierNumber = deviceIdentifierNumber; }

}

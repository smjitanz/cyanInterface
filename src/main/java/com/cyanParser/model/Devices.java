package com.cyanParser.model;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.ArrayList;


@XmlRootElement(name = "deviceList")
@XmlAccessorType(XmlAccessType.FIELD)
public class Devices{
	@XmlElement(name = "device")
	ArrayList<Device> devices = new ArrayList<Device>();

	@Override
	public String toString() {
		return "Devices{" +
				"devices=" + devices +
				'}';
	}

	public Devices() {
		this.devices = devices;
	}
	public Devices(ArrayList<Device> devices) {
		this.devices = devices;
	}

	public ArrayList<Device> getDevices() {
		return devices;
	}

	public void setDevices(ArrayList<Device> devices) {
		this.devices = devices;
	}
}


package com.cyanParser.model;

import java.util.ArrayList;

public  class Usage {
	private String createTime;
	private String deviceId;
	private String formattedProfileObisCode;
	private float meterSampleId;
	ArrayList<Intervals> registerValues = new ArrayList <Intervals> ();

	public ArrayList<Intervals> getRegisterValues() {
		return registerValues;
	}

	public void setRegisterValues(ArrayList<Intervals> registerValues) {
		this.registerValues = registerValues;
	}
// Getter Methods

	public String getCreateTime() {
		return createTime;
	}

	public String getDeviceId() {
		return deviceId;
	}

	public String getFormattedProfileObisCode() {
		return formattedProfileObisCode;
	}

	public float getMeterSampleId() {
		return meterSampleId;
	}

	// Setter Methods

	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}

	public void setDeviceId(String deviceId) {
		this.deviceId = deviceId;
	}

	public void setFormattedProfileObisCode(String formattedProfileObisCode) {
		this.formattedProfileObisCode = formattedProfileObisCode;
	}

	public void setMeterSampleId(float meterSampleId) {
		this.meterSampleId = meterSampleId;
	}
}

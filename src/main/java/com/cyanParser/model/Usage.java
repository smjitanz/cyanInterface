package com.cyanParser.model;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Locale;

public  class Usage {
	private String sampleTime;
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

	public String getSampleTime() {
		return sampleTime;
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

	public void setSampleTime(String sampleTime) {
		this.sampleTime = sampleTime;
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

	public static Comparator<Usage> deviceNo = new Comparator<Usage>() {
		public int compare(Usage u1, Usage u2) {
			String firstDevice = u1.getDeviceId();
			String secondDevice = u2.getDeviceId();

			return firstDevice.compareTo(secondDevice);
		}};
	public static Comparator<Usage> obisCode = new Comparator<Usage>() {
		public int compare(Usage u1, Usage u2) {
			String firstDevice = u1.getFormattedProfileObisCode();
			String secondDevice = u2.getFormattedProfileObisCode();

			return firstDevice.compareTo(secondDevice);
		}};


	public static Comparator<Usage> sampleDateTime = new Comparator<Usage>() {
		public int compare(Usage u1, Usage u2) {
			DateTimeFormatter formatter = DateTimeFormatter.ISO_OFFSET_DATE_TIME;
			LocalDateTime firstDate = LocalDateTime.parse(u1.getSampleTime(), formatter);
			LocalDateTime secondDate = LocalDateTime.parse(u2.getSampleTime(), formatter);

			return firstDate.compareTo(secondDate);

		}};

}

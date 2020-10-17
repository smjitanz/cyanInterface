package com.cyanParser.model;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name="mL")
@XmlAccessorType(XmlAccessType.FIELD)
public class mL{
	@XmlElement(name = "s")
	private int s;
	@XmlElement(name = "q")
	private String q=null;
	@XmlElement(name = "r")
	private String r=null;

	public String getR() {
		return r;
	}

	public void setR(String r) {
		this.r = r;
		this.q=null;
	}


	public mL() {
	}

	public mL(int s, String q) {
		this.s = s;
		this.q = q;
	}

// Getter Methods

	public int getS() { return s; }
	public String getQ() { return q; }

	// Setter Methods

	public void setS(int s) {
		this.s = s;
	}
	public void setQ(String q) { this.q = q; this.r=null; }

	@Override
	public String toString() {
		return "mL{" +
				"s=" + s + q!=null?
				", q='" + q + '\'':", r='" + r + '\'' +
				'}';
	}
}

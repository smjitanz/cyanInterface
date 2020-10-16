package com.cyanParser.model;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.ArrayList;
@XmlRootElement(name="preVEE")
@XmlAccessorType(XmlAccessType.FIELD)
public class preVEE {


	@XmlElement(name = "mcIdN")
	private String mcIdN;
	@XmlElement(name = "stDt")
	private String stDt;
	@XmlElement(name = "enDt")
	private String enDt;
	@XmlElement(name = "spi")
	private String spi;
	@XmlElement(name = "imdType")
	private String imdType="D1IL";
	@XmlElement(name = "mcIS")
	private String mcIS="D1IN";
	@XmlElement(name = "dvcIdN")
	private String dvcIdN;
	@XmlElement(name = "msrs")
	ArrayList<msrs> msrs = new ArrayList<msrs>();

	public preVEE(String mcIdN, String stDt, String enDt, String spi, String imdType, String mcIS, String dvcIdN, ArrayList<com.cyanParser.model.msrs> msrs) {
		this.mcIdN = mcIdN;
		this.stDt = stDt;
		this.enDt = enDt;
		this.spi = spi;
		this.imdType = imdType;
		this.mcIS = mcIS;
		this.dvcIdN = dvcIdN;
		this.msrs = msrs;
		this.msrs.add(new msrs());
	}

	public preVEE() {
		msrs.add(new msrs());
	}

	public String getImdType() {
		return imdType;
	}

	public void setImdType(String imdType) {
		this.imdType = imdType;
	}

	public String getMcIS() {
		return mcIS;
	}

	public void setMcIS(String mcIS) {
		this.mcIS = mcIS;
	}

	public String getDvcIdN() {
		return dvcIdN;
	}

	public void setDvcIdN(String dvcIdN) {
		this.dvcIdN = dvcIdN;
	}

	public ArrayList<msrs> getMsrs() {
		return msrs;
	}

	public void setMsrs(ArrayList<msrs> msrs) {
		this.msrs = msrs;
	}
// Getter Methods

	public String getMcIdN() { return mcIdN; }
	public String getStDt() { return stDt; }
	public String getEnDt() { return enDt; }
	public String getSpi() { return spi; }


	// Setter Methods

	public void setMcIdN(String mcIdN) {
		this.mcIdN = mcIdN;
	}
	public void setStDt(String stDt) { this.stDt = stDt; }
	public void setEnDt(String enDt) { this.enDt = enDt; }
	public void setSpi(String spi) { this.spi = spi; }


}

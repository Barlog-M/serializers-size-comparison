package com.example.model.xml;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Version {
	private byte major = 0;
	private byte minor = 1;

	public byte getMajor() {
		return major;
	}

	@XmlElement
	public void setMajor(byte major) {
		this.major = major;
	}

	public byte getMinor() {
		return minor;
	}

	@XmlElement
	public void setMinor(byte minor) {
		this.minor = minor;
	}
}

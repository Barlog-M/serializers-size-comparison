package com.example.model.kryo;

public class Version {
	private byte major = 0;
	private byte minor = 1;

	public byte getMajor() {
		return major;
	}

	public void setMajor(byte major) {
		this.major = major;
	}

	public byte getMinor() {
		return minor;
	}

	public void setMinor(byte minor) {
		this.minor = minor;
	}
}

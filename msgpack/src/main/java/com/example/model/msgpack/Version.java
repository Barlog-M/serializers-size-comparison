package com.example.model.msgpack;

import org.msgpack.annotation.Message;

@Message
public class Version {
	private byte major = 0;
	private byte minor = 1;

	public Version() {
	}

	public Version(byte major, byte minor) {
		this.major = major;
		this.minor = minor;
	}

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

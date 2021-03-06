package com.example.model.xml;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.ArrayList;
import java.util.List;

@XmlRootElement
public class Data {
	private long timestamp;
	private Version version;
	private List<Record> records = new ArrayList<>();

	public long getTimestamp() {
		return timestamp;
	}

	@XmlElement
	public void setTimestamp(long timestamp) {
		this.timestamp = timestamp;
	}

	public Version getVersion() {
		return version;
	}

	@XmlElement
	public void setVersion(Version version) {
		this.version = version;
	}

	public List<Record> getRecords() {
		return records;
	}

	@XmlElement
	public void setRecords(List<Record> records) {
		this.records = records;
	}
}

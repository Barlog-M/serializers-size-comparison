package com.example.model.msgpack;

import org.msgpack.annotation.Message;

import java.util.ArrayList;
import java.util.List;

@Message
public class Data {
	private long timestamp;
	private Version version;
	private List<Record> records = new ArrayList<>();

	public Data() {}

	public Data(long timestamp, Version version, List<Record> records) {
		this.timestamp = timestamp;
		this.version = version;
		this.records = records;
	}

	public long getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(long timestamp) {
		this.timestamp = timestamp;
	}

	public Version getVersion() {
		return version;
	}

	public void setVersion(Version version) {
		this.version = version;
	}

	public List<Record> getRecords() {
		return records;
	}

	public void setRecords(List<Record> records) {
		this.records = records;
	}
}

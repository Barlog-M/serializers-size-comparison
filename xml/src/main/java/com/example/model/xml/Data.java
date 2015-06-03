package com.example.model.xml;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.ArrayList;
import java.util.List;

@XmlRootElement
public class Data {
	private long timestamp;
	private Version version;
	private List<Message> messages = new ArrayList<>();

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

	public List<Message> getMessages() {
		return messages;
	}

	@XmlElement
	public void setMessages(List<Message> messages) {
		this.messages = messages;
	}
}

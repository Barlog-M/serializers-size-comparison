package com.example.model.xml;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Message {
	private MessageType type;
	private String header;
	private String value;

	public MessageType getType() {
		return type;
	}

	@XmlElement
	public void setType(MessageType type) {
		this.type = type;
	}

	public String getHeader() {
		return header;
	}

	@XmlElement
	public void setHeader(String header) {
		this.header = header;
	}

	public String getValue() {
		return value;
	}

	@XmlElement
	public void setValue(String value) {
		this.value = value;
	}
}

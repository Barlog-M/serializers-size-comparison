package com.example.model.xml;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Record {
	private Type type;
	private int value;
	private String message;
	private String description;

	public Type getType() {
		return type;
	}

	@XmlElement
	public void setType(Type type) {
		this.type = type;
	}

	public int getValue() {
		return value;
	}

	@XmlElement
	public void setValue(int value) {
		this.value = value;
	}

	public String getMessage() {
		return message;
	}

	@XmlElement
	public void setMessage(String message) {
		this.message = message;
	}

	public String getDescription() {
		return description;
	}

	@XmlElement
	public void setDescription(String description) {
		this.description = description;
	}
}

package com.example.model.msgpack;

import org.msgpack.annotation.Message;

@Message
public class Record {
	private Type type;
	private int value;
	private String message;
	private String description;

	public Record() {
	}

	public Record(Type type, int value, String message, String description) {
		this.type = type;
		this.value = value;
		this.message = message;
		this.description = description;
	}

	public Type getType() {
		return type;
	}

	public void setType(Type type) {
		this.type = type;
	}

	public int getValue() {
		return value;
	}

	public void setValue(int value) {
		this.value = value;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
}

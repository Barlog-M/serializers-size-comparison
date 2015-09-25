package com.example;

import com.example.model.protobuf.DTO.Data;
import com.example.model.protobuf.DTO.Record;
import com.example.model.protobuf.DTO.Type;
import com.example.model.protobuf.DTO.Version;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

public class ProtobufTester extends AbstractTester {
	private final List<Record> records;
	private final Version version;
	private Data data;

	public ProtobufTester() {
		name = "Protobuf";

		records = new ArrayList<>(App.RECORDS_COUNT);
		version = Version.newBuilder().setMajor(0).setMinor(1).build();
	}

	@Override
	public void addData(int value, String message, String description) {
		Record record = Record.newBuilder()
			.setType(Type.RECORD)
			.setValue(value)
			.setMessage(message)
			.setDescription(description)
			.build();

		records.add(record);
	}

	@Override
	public void serialize() {
		serialized = data.toByteArray();
		printUnpackedResult();
	}

	@Override
	public void initialize() {
		Data.Builder builder = Data.newBuilder();
		records.forEach(builder::addRecords);
		builder.setVersion(version);
		builder.setTimestamp(Instant.now().getEpochSecond());
		data = builder.build();
	}
}

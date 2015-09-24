package com.example;

import com.example.model.protobuf.DTO.Data;
import com.example.model.protobuf.DTO.Record;
import com.example.model.protobuf.DTO.Type;
import com.example.model.protobuf.DTO.Version;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

public class ProtobufExample {
	private static final List<Record> records = new ArrayList<>(App.RECORDS_COUNT);
	private static final Version version;
	private static Data data;

	static {
		version = Version.newBuilder().setMajor(0).setMinor(1).build();
	}

	public static void addData(int value, String message, String description) {
		Record record = Record.newBuilder()
			.setType(Type.RECORD)
			.setValue(value)
			.setMessage(message)
			.setDescription(description)
			.build();

		records.add(record);
	}

	public static void calc(Function<byte[], byte[]> fn) {
		fillData();

		byte[] result = data.toByteArray();
		System.out.println("\tunpacked: " + result.length);

		byte[] packed = fn.apply(result);
		System.out.println("\tpacked: " + packed.length);
	}

	public static void fillData() {
		Data.Builder builder = Data.newBuilder();
		records.forEach(builder::addRecords);
		builder.setVersion(version);
		builder.setTimestamp(Instant.now().getEpochSecond());
		data = builder.build();
	}
}

package com.example;

import com.example.model.thrift.Data;
import com.example.model.thrift.Version;
import com.example.model.thrift.Record;
import com.example.model.thrift.Type;

import org.apache.thrift.TException;
import org.apache.thrift.TSerializer;
import org.apache.thrift.protocol.TCompactProtocol;

import java.time.Instant;
import java.util.ArrayList;

public class ThriftTester extends AbstractTester {
	private final Data data;

	public ThriftTester() {
		name = "Thrift";
		data = new Data();

		final Version version = new Version();

		data.setTimestamp(Instant.now().getEpochSecond());
		data.setVersion(version);

		data.setRecords(new ArrayList<Record>(App.RECORDS_COUNT));
	}

	@Override
	public void addData(int value, String message, String description) {
		final Record record = new Record();
		record.setType(Type.RECORD);
		record.setValue(value);
		record.setMessage(message);
		record.setDescription(description);

		data.getRecords().add(record);
	}

	@Override
	public void serialize() {
		final TSerializer serializer = new TSerializer(new TCompactProtocol.Factory());
		try {
			serialized = serializer.serialize(data);
		} catch (TException e) {
			e.printStackTrace();
		}
		printUnpackedResult();
	}
}

package com.example;

import com.example.model.kryo.Data;
import com.example.model.kryo.Record;
import com.example.model.kryo.Type;
import com.example.model.kryo.Version;

import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryo.io.Output;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.time.Instant;
import java.util.ArrayList;

public class KryoTester extends AbstractTester {
	private final Data data;

	public KryoTester() {
		name = "Kryo";
		data = new Data();

		final Version version = new Version();

		data.setTimestamp(Instant.now().getEpochSecond());
		data.setVersion(version);
		data.setRecords(new ArrayList<Record>(App.RECORDS_COUNT));
	}

	@Override
	public void addData(int value, String message, String description) {
		Record m = new Record();
		m.setType(Type.RECORD);
		m.setValue(value);
		m.setMessage(message);
		m.setDescription(description);

		data.getRecords().add(m);
	}

	@Override
	public void serialize() {
		final Kryo kryo = new Kryo();

		try (
			ByteArrayOutputStream os = new ByteArrayOutputStream();
			Output output = new Output(os);
		) {
			kryo.writeObject(output, data);
			output.flush();
			serialized = os.toByteArray();
		} catch (IOException e) {
			e.printStackTrace();
		}
		printUnpackedResult();
	}
}

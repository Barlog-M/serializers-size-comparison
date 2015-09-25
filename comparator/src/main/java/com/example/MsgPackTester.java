package com.example;

import com.example.model.msgpack.Data;
import com.example.model.msgpack.Record;
import com.example.model.msgpack.Type;
import com.example.model.msgpack.Version;
import org.msgpack.MessagePack;

import java.io.IOException;
import java.time.Instant;
import java.util.ArrayList;
import java.util.function.Function;

public class MsgPackTester extends AbstractTester {
	private final Data data;

	public MsgPackTester() {
		name = "MessagePack";
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
		final MessagePack msgpack = new MessagePack();
		try {
			serialized = msgpack.write(data);
		} catch (IOException e) {
			e.printStackTrace();
		}
		printUnpackedResult();
	}
}

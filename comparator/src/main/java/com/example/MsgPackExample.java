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

public class MsgPackExample {
	private static final Data data = new Data();
	private static final MessagePack msgpack = new MessagePack();

	static {
		Version version = new Version();

		data.setTimestamp(Instant.now().getEpochSecond());
		data.setVersion(version);
		data.setRecords(new ArrayList<Record>(App.RECORDS_COUNT));
	}


	public static void addData(int value, String message, String description) {
		Record m = new Record();
		m.setType(Type.RECORD);
		m.setValue(value);
		m.setMessage(message);
		m.setDescription(description);

		data.getRecords().add(m);
	}

	public static void calc(Function<byte[], byte[]> fn) {
		try {
			byte[] result = msgpack.write(data);

			System.out.println("\tunpacked: " + result.length);

			byte[] packed = fn.apply(result);
			System.out.println("\tpacked: " + packed.length);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}

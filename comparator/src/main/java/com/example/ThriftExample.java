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
import java.util.function.Function;

public class ThriftExample {
	private static final Data data = new Data();

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
		TSerializer serializer = new TSerializer(new TCompactProtocol.Factory());
		byte[] result = null;
		try {
			result = serializer.serialize(data);
			System.out.println("\tunpacked: " + result.length);
		} catch (TException e) {
			e.printStackTrace();
		}

		byte[] packed = fn.apply(result);
		System.out.println("\tpacked: " + packed.length);
	}
}

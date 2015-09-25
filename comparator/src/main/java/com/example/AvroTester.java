package com.example;

import com.example.model.avro.Data;
import com.example.model.avro.Record;
import com.example.model.avro.Type;
import com.example.model.avro.Version;

import org.apache.avro.file.DataFileWriter;
import org.apache.avro.io.DatumWriter;
import org.apache.avro.specific.SpecificDatumWriter;
import org.msgpack.MessagePack;

import javax.xml.bind.JAXBException;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.time.Instant;
import java.util.ArrayList;

public class AvroTester extends AbstractTester {
	private final Data data;

	public AvroTester() {
		name = "Avro";
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
		final DatumWriter<Data> datumWriter = new SpecificDatumWriter<>(Data.class);
		final DataFileWriter<Data> fileWriter = new DataFileWriter<>(datumWriter);

		try (ByteArrayOutputStream os = new ByteArrayOutputStream()) {
			fileWriter.create(data.getSchema(), os);
			fileWriter.append(data);
			fileWriter.close();
			serialized = os.toByteArray();
		} catch (IOException e) {
			e.printStackTrace();
		}

		printUnpackedResult();
	}
}

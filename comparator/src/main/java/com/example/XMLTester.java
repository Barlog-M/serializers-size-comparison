package com.example;

import com.example.model.xml.Data;
import com.example.model.xml.Record;
import com.example.model.xml.Type;
import com.example.model.xml.Version;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.time.Instant;
import java.util.ArrayList;
import java.util.function.Function;

public class XMLTester extends AbstractTester {
	private final Data data;

	public XMLTester() {
		name = "XML";
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
		try (ByteArrayOutputStream os = new ByteArrayOutputStream()) {
			JAXBContext jaxbContext = JAXBContext.newInstance(Data.class);
			Marshaller jaxbMarshaller = jaxbContext.createMarshaller();

			jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
			jaxbMarshaller.marshal(data, os);
			os.flush();

			serialized = os.toByteArray();

		} catch (JAXBException | IOException e) {
			e.printStackTrace();
		}
		printUnpackedResult();
	}
}

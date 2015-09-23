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

public class XMLExample {
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

	public static void calc() {
		try (ByteArrayOutputStream os = new ByteArrayOutputStream()) {
			JAXBContext jaxbContext = JAXBContext.newInstance(Data.class);
			Marshaller jaxbMarshaller = jaxbContext.createMarshaller();

			jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
			jaxbMarshaller.marshal(data, os);

			System.out.println("xml unpacked: " + os.toByteArray().length);

			byte[] deflated = App.deflate(os.toByteArray());
			System.out.println("xml deflated: " + deflated.length);
		} catch (JAXBException | IOException e) {
			e.printStackTrace();
		}
	}
}

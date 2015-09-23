package com.example;

import com.example.model.xml.Data;
import com.example.model.xml.Message;
import com.example.model.xml.MessageType;
import com.example.model.xml.Version;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;
import java.io.ByteArrayOutputStream;
import java.time.Instant;
import java.util.ArrayList;
import java.util.zip.Deflater;
import java.util.zip.DeflaterOutputStream;

public class XMLExample {
	private static final Data data = new Data();

	static {
		Version version = new Version();

		data.setTimestamp(Instant.now().getEpochSecond());
		data.setVersion(version);
		data.setMessages(new ArrayList<Message>(100));
	}


	public static void addData(String header, String value) {
		Message m = new Message();
		m.setType(MessageType.DATA);
		m.setHeader(header);
		m.setValue(value);

		data.getMessages().add(m);
	}

	public static void calc() {
		try {
			JAXBContext jaxbContext = JAXBContext.newInstance(Data.class);
			Marshaller jaxbMarshaller = jaxbContext.createMarshaller();

			jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);

			ByteArrayOutputStream byteArrayOutputStream1 = new ByteArrayOutputStream();
			jaxbMarshaller.marshal(data, byteArrayOutputStream1);
			System.out.println("xml unpacked: " + byteArrayOutputStream1.toByteArray().length);

			ByteArrayOutputStream byteArrayOutputStream2 = new ByteArrayOutputStream();
			DeflaterOutputStream deflaterOutputStream = new DeflaterOutputStream(byteArrayOutputStream2, new Deflater());
			jaxbMarshaller.marshal(data, deflaterOutputStream);
			deflaterOutputStream.close();
			System.out.println("xml deflated: " + byteArrayOutputStream2.toByteArray().length);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}

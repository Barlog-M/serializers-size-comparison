package com.example;

import com.example.model.xml.Data;
import com.example.model.xml.Message;
import com.example.model.xml.MessageType;
import com.example.model.xml.Version;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;
import java.io.ByteArrayOutputStream;
import java.time.Instant;
import java.util.zip.Deflater;
import java.util.zip.DeflaterOutputStream;

public class XMLExample {
	public static void calc() {
		Version version = new Version();

		Data data = new Data();
		data.setTimestamp(Instant.now().getEpochSecond());
		data.setVersion(version);

		Message m1 = new Message();
		m1.setType(MessageType.DATA);
		m1.setHeader("Длинный заголовок сообщения номер один");
		m1.setValue("Само сообщение для длинного заголовка номер один");

		Message m2 = new Message();
		m1.setType(MessageType.DATA);
		m1.setHeader("Длинный заголовок сообщения номер два");
		m1.setValue("Само сообщение для длинного заголовка номер два");

		Message m3 = new Message();
		m1.setType(MessageType.DATA);
		m1.setHeader("Длинный заголовок сообщения номер три");
		m1.setValue("Само сообщение для длинного заголовка номер три");

		data.getMessages().add(m1);
		data.getMessages().add(m2);
		data.getMessages().add(m3);

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

package com.example;

import com.example.model.thrift.Data;
import com.example.model.thrift.Version;
import com.example.model.thrift.Message;
import com.example.model.thrift.MessageType;
import org.apache.thrift.TException;
import org.apache.thrift.TSerializer;
import org.apache.thrift.protocol.TBinaryProtocol;

import java.io.ByteArrayOutputStream;
import java.time.Instant;
import java.util.ArrayList;
import java.util.zip.Deflater;
import java.util.zip.DeflaterOutputStream;

public class ThriftExample {
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

		data.setMessages(new ArrayList<Message>(3));
		data.getMessages().add(m1);
		data.getMessages().add(m2);
		data.getMessages().add(m3);

		TSerializer serializer = new TSerializer(new TBinaryProtocol.Factory());
		byte[] bytes = new byte[0];
		try {
			bytes = serializer.serialize(data);
			System.out.println("thrift unpacked: " + bytes.length);
		} catch (TException e) {
			e.printStackTrace();
		}

		try {
			ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
			DeflaterOutputStream deflaterOutputStream = new DeflaterOutputStream(byteArrayOutputStream, new Deflater());
			deflaterOutputStream.write(bytes, 0, bytes.length);
			deflaterOutputStream.close();
			System.out.println("thrift deflated: " + byteArrayOutputStream.toByteArray().length);
		} catch (Exception e) {
			e.printStackTrace();
		}

/*
		TDeserializer deserializer = new TDeserializer(new TBinaryProtocol.Factory());
		Data moreWork = new Data();
		deserializer.deserialize(moreWork, bytes);
*/
	}
}

package com.example;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Random;
import java.util.zip.Deflater;
import java.util.zip.DeflaterOutputStream;

public final class App {
	public static int RECORDS_COUNT = 10000;
	private static final int MAX = Integer.MAX_VALUE;
	private static final Random rnd = new Random();

	public static void main(String... args) {
		for (int i = 0; i < RECORDS_COUNT; i++) {
			int value = rnd.nextInt(MAX);
			String message = Converter.getText(value);
			String description = Converter.getText(rnd.nextInt(MAX));

			XMLExample.addData(value, message, description);
			ThriftExample.addData(value, message, description);
			MsgPackExample.addData(value, message, description);
		}

		XMLExample.calc();
		ThriftExample.calc();
		MsgPackExample.calc();
	}

	public static byte[] deflate(byte[] data) {
		byte[] result = null;
		try (ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream()) {
			DeflaterOutputStream deflaterOutputStream = new DeflaterOutputStream(byteArrayOutputStream, new Deflater(Deflater.BEST_COMPRESSION));
			deflaterOutputStream.write(data, 0, data.length);
			deflaterOutputStream.flush();
			deflaterOutputStream.close();
			result = byteArrayOutputStream.toByteArray();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return result;
	}
}

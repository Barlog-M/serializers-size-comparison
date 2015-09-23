package com.example;

import org.tukaani.xz.FilterOptions;
import org.tukaani.xz.LZMA2Options;
import org.tukaani.xz.XZOutputStream;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Random;
import java.util.function.Consumer;
import java.util.function.Function;
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

		System.out.println("XML deflate:");
		XMLExample.calc(deflate);

		System.out.println("XML xz:");
		XMLExample.calc(xz);

		System.out.println("Thrift deflate:");
		ThriftExample.calc(deflate);

		System.out.println("Thrift xz:");
		ThriftExample.calc(xz);

		System.out.println("MessagePack deflate:");
		MsgPackExample.calc(deflate);

		System.out.println("MessagePack xz:");
		MsgPackExample.calc(xz);
	}

	private static final Function<byte[], byte[]> deflate = (data) -> {
		byte[] result = null;
		try (ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream()) {
			OutputStream outputStream = new DeflaterOutputStream(byteArrayOutputStream, new Deflater(Deflater.BEST_COMPRESSION));
			outputStream.write(data, 0, data.length);
			outputStream.flush();
			outputStream.close();
			result = byteArrayOutputStream.toByteArray();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return result;
	};

	private static final Function<byte[], byte[]> xz = (data) -> {
		byte[] result = null;
		try (ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream()) {
			LZMA2Options options = new LZMA2Options();
			//options.setPreset(9);
			OutputStream outputStream = new XZOutputStream(byteArrayOutputStream, options);
			outputStream.write(data, 0, data.length);
			outputStream.flush();
			outputStream.close();
			result = byteArrayOutputStream.toByteArray();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return result;
	};
}

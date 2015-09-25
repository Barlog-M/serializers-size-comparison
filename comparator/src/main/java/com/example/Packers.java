package com.example;

import org.tukaani.xz.LZMA2Options;
import org.tukaani.xz.XZOutputStream;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.function.Function;
import java.util.zip.Deflater;
import java.util.zip.DeflaterOutputStream;

public class Packers {
	public static final Function<byte[], byte[]> deflate = (data) -> {
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

	public static final Function<byte[], byte[]> xz = (data) -> {
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

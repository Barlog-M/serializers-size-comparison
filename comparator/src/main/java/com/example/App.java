package com.example;

import java.util.Arrays;
import java.util.List;
import java.util.Random;

public final class App {
	public static int RECORDS_COUNT = 10000;
	private static final int MAX = Integer.MAX_VALUE;
	private static final Random rnd = new Random();

	public static void main(String... args) {
		final List<Tester> testers = Arrays.asList(
			new XMLTester(),
			new ThriftTester(),
			new MsgPackTester(),
			new ProtobufTester());

		generateData(testers);

		testers.forEach(t -> t.serialize());
		testers.forEach(t -> t.pack(Packers.deflate, "deflate"));
		testers.forEach(t -> t.pack(Packers.xz, "XZ"));
	}

	private static void generateData(List<Tester> testers) {
		for (int i = 0; i < RECORDS_COUNT; i++) {
			int value = rnd.nextInt(MAX);
			String message = Converter.getText(value);
			String description = Converter.getText(rnd.nextInt(MAX));

			testers.forEach(t -> t.addData(value, message, description));
		}

		testers.forEach(t -> t.initialize());
	}
}

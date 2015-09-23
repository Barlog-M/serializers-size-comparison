package com.example;

public final class App {
	public static int RECORDS_COUNT = 10000;

	public static void main(String... args) {
		for(int i=0; i < RECORDS_COUNT; i++) {
			String header = Generator.getRandomString();
			String value = Generator.getRandomString();

			XMLExample.addData(header, value);
			ThriftExample.addData(header, value);
		}

		XMLExample.calc();
		ThriftExample.calc();
	}
}

package com.example;

public final class App {
	public static void main(String... args) {
		for(int i=0; i<=9999; i++) {
			String header = Generator.getRandomString();
			String value = Generator.getRandomString();

			XMLExample.addData(header, value);
			ThriftExample.addData(header, value);
		}

		XMLExample.calc();
		ThriftExample.calc();
	}
}

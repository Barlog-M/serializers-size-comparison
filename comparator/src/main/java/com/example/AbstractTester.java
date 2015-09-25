package com.example;

import java.util.function.Function;

public abstract class AbstractTester implements Tester {
	protected String name;
	protected byte[] serialized;

	@Override
	public void pack(Function<byte[], byte[]> fn, String packerName) {
		byte[] packed = fn.apply(serialized);
		printPackedResult(packed.length, packerName);
	}

	@Override
	public void initialize() {
	}

	protected void printUnpackedResult() {
		System.out.println(serialized.length + " unpacked " + name);
	}

	protected void printPackedResult(int length, String packerName) {
		System.out.println(length + " packed by " + packerName + " " + name);
	}
}

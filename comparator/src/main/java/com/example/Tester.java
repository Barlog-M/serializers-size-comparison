package com.example;

import java.util.function.Function;

public interface Tester {
	void addData(int value, String message, String description);
	void serialize();
	void pack(Function<byte[], byte[]> fn, String packerName);
	void initialize();
}

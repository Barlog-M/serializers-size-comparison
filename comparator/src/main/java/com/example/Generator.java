package com.example;

import com.ibm.icu.text.RuleBasedNumberFormat;
import com.ibm.icu.util.ULocale;

import java.util.Random;

public class Generator {
	private static final ULocale locale = new ULocale("ru_RU");
	private static final RuleBasedNumberFormat rule = new com.ibm.icu.text.RuleBasedNumberFormat(locale, RuleBasedNumberFormat.SPELLOUT);
	private static final Random rnd = new Random();
	private static final int MAX = 9999;

	public static String getRandomString() {
		return rule.format(rnd.nextInt(MAX));
	}
}

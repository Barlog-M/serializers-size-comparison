package com.example;

import com.ibm.icu.text.RuleBasedNumberFormat;
import com.ibm.icu.util.ULocale;

import java.util.Random;

public class Converter {
	private static final ULocale locale = new ULocale("ru_RU");
	private static final RuleBasedNumberFormat rule = new RuleBasedNumberFormat(locale, RuleBasedNumberFormat.SPELLOUT);

	public static String getText(int value) {
		return rule.format(value);
	}
}

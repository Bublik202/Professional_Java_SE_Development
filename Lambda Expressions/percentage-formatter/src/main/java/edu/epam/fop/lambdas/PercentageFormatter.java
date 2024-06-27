package edu.epam.fop.lambdas;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.Locale;
import java.util.function.DoubleFunction;

public interface PercentageFormatter {

  //DoubleFunction<String> INSTANCE = value -> String.format("%.0f", value * 100) + " %";
	DecimalFormat format = new DecimalFormat("#.#", DecimalFormatSymbols.getInstance(Locale.US));
	DoubleFunction<String> INSTANCE = value -> format.format(value * 100) + " %";
}

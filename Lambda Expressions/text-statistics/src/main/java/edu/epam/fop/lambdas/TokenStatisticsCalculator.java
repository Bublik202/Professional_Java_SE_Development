package edu.epam.fop.lambdas;

import java.util.Iterator;
import java.util.Map;

@FunctionalInterface
public interface TokenStatisticsCalculator<K, V> {

  void calculate(Map<K, V> statistics, Iterator<K> tokens);
}

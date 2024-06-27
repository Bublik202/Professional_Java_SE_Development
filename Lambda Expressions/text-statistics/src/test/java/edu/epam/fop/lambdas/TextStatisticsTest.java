package edu.epam.fop.lambdas;

import static edu.epam.fop.lambdas.Token.token;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.params.provider.Arguments.arguments;

import edu.epam.fop.lambdas.Token.Type;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.function.BiConsumer;
import java.util.function.BiFunction;
import java.util.function.Function;
import java.util.stream.Stream;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

class TextStatisticsTest {

  @DisplayName("Test for countTokens")
  @ParameterizedTest(name = "For input {0} and tokens {1} the result must be {2}")
  @MethodSource
  void testCountTokens(Map<Token, Long> inputMap, List<Token> tokens, Map<Token, Long> expectedMap) {
    test(TextStatistics.countTokens(), inputMap, tokens.iterator(), expectedMap);
  }

  static Stream<Arguments> testCountTokens() {
    return Stream.of(
        arguments(new HashMap<Token, Long>(), List.<Token>of(), Map.<Token, Long>of()),
        arguments(new HashMap<Token, Long>(), Stream.of(
                "Heap", "Lend", "Moon", "Fun", "Language",
                "Fun", "Obey", "Heap", "Obey", "Moon",
                "Obey", "Obey", "Language", "Fun", "Heap").map(Token::new).toList(),
            Map.of(
                token("Heap"), 3L,
                token("Lend"), 1L,
                token("Moon"), 2L,
                token("Language"), 2L,
                token("Obey"), 4L,
                token("Fun"), 3L
            ))
    );
  }

  @DisplayName("Test for countKnownTokensWithMaxLimit")
  @ParameterizedTest(name = "For input {0} and tokens {1} with max limit {3} the result must be {2}")
  @MethodSource
  void testCountKnownTokensWithMaxLimit(Map<Token, Long> inputMap, List<Token> tokens, Map<Token, Long> expectedMap,
      int maxLimit) {
    test(TextStatistics.countKnownTokensWithMaxLimit(maxLimit), inputMap, tokens.iterator(), expectedMap);
  }

  static Stream<Arguments> testCountKnownTokensWithMaxLimit() {
    return Stream.of(
        arguments(
            new HashMap<>(Map.of(
                token("name"), 8L,
                token("surname"), 11L,
                token("age"), 10L,
                token("gender"), 6L,
                token("education"), 3L,
                token("specialization"), 7L
            )),
            List.of(
                token("name"),
                token("name"),
                token("name"),
                token("surname"),
                token("surname"),
                token("specialization"),
                token("specialization")
            ),
            Map.of(
                token("name"), 10L,
                token("surname"), 11L,
                token("age"), 10L,
                token("gender"), 6L,
                token("education"), 3L,
                token("specialization"), 9L
            ),
            10)
    );
  }

  @DisplayName("Test for findUnknownTokensOfTypes")
  @ParameterizedTest(name = "For input {0} and tokens {1} with types {3} the result must be {2}")
  @MethodSource
  void testFindUnknownTokensOfTypes(Map<Token, Boolean> inputMap, List<Token> tokens, Map<Token, Boolean> expectedMap,
      Set<Type> types) {
    test(TextStatistics.findUnknownTokensOfTypes(types), inputMap, tokens.iterator(), expectedMap);
  }

  static Stream<Arguments> testFindUnknownTokensOfTypes() {
    return Stream.of(
        arguments(
            new HashMap<>(Map.of(
                token("name", Type.WORD), false,
                token("age", Type.NUMBER), false,
                token("education", Type.CUSTOM), false,
                token("specialization", Type.CUSTOM), false,
                token("id", Type.CODE), false
            )),
            List.of(
                token("id", Type.CODE),
                token("insurance id", Type.CODE),
                token("education", Type.CUSTOM),
                token("driver licence", Type.CODE),
                token("courses", Type.CUSTOM),
                token("salary", Type.NUMBER),
                token("company", Type.WORD)
            ),
            Map.of(
                token("name", Type.WORD), false,
                token("age", Type.NUMBER), false,
                token("education", Type.CUSTOM), false,
                token("specialization", Type.CUSTOM), false,
                token("id", Type.CODE), false,
                token("insurance id", Type.CODE), true,
                token("driver licence", Type.CODE), true,
                token("courses", Type.CUSTOM), true
            ),
            Set.of(Type.CODE, Type.CUSTOM)
        )
    );
  }

  @DisplayName("Test for combinedSearch")
  @ParameterizedTest(name = "For input {0} and tokens {1} with max limit {3} and types {4} the result must be {2}")
  @MethodSource
  void testCombinedSearch(Map<Token, Integer> inputMap, List<Token> tokens, Map<Token, Integer> expectedMap,
      int maxLimit, Set<Type> types) {
    test(TextStatistics.combinedSearch(maxLimit, types), inputMap, tokens.iterator(), expectedMap);
  }

  static Stream<Arguments> testCombinedSearch() {
    return Stream.of(
        arguments(
            new HashMap<>(Map.of(
                token("normal", Type.WORD), 5,
                token("exceeded", Type.NUMBER), 1732,
                token("specific", Type.CUSTOM), 4,
                token("broken", Type.CODE), 28147912
            )),
            List.of(
                token("non-existed"),
                token("normal", Type.WORD),
                token("exceeded", Type.NUMBER),
                token("specific", Type.CUSTOM),
                token("broken", Type.CODE)
            ),
            Map.of(
                token("non-existed"), -1,
                token("normal", Type.WORD), 0,
                token("exceeded", Type.NUMBER), 1,
                token("specific", Type.CUSTOM), 2,
                token("broken", Type.CODE), 3
            ),
            10,
            Set.of(Type.WORD, Type.NUMBER)
        )
    );
  }

  private <K, V> void test(TokenStatisticsCalculator<K, V> calculator, Map<K, V> inputMap, Iterator<K> tokens,
      Map<K, V> expectedMap) {
    Map<K, V> copyMap = new RestrictedMap<>(inputMap);
    assertDoesNotThrow(() -> calculator.calculate(copyMap, tokens));
    assertAll(
        () -> assertEquals(copyMap.size(), expectedMap.size()),
        () -> assertTrue(copyMap.keySet().containsAll(expectedMap.keySet())),
        () -> assertTrue(expectedMap.keySet().containsAll(copyMap.keySet()))
    );
    assertAll(copyMap.entrySet().stream()
        .map(entry -> () -> assertEquals(expectedMap.get(entry.getKey()), entry.getValue())));
  }

  private static record RestrictedMap<K, V>(Map<K, V> map) implements Map<K, V> {

    @Override
    public int size() {
      return map.size();
    }

    @Override
    public boolean isEmpty() {
      return map.isEmpty();
    }

    @Override
    public boolean containsKey(Object key) {
      return map.containsKey(key);
    }

    @Override
    public boolean containsValue(Object value) {
      return map.containsValue(value);
    }

    @Override
    public V get(Object key) {
      return map.get(key);
    }

    @Override
    public V put(K key, V value) {
      throw unsupported();
    }

    @Override
    public V remove(Object key) {
      throw unsupported();
    }

    @Override
    public void putAll(Map<? extends K, ? extends V> m) {
      throw unsupported();
    }

    @Override
    public void clear() {
      throw unsupported();
    }

    @Override
    public Set<K> keySet() {
      return Set.copyOf(map.keySet());
    }

    @Override
    public Collection<V> values() {
      return List.copyOf(map.values());
    }

    @Override
    public Set<Entry<K, V>> entrySet() {
      return map.entrySet();
    }

    @Override
    public void replaceAll(BiFunction<? super K, ? super V, ? extends V> function) {
      throw unsupported();
    }

    @Override
    public V putIfAbsent(K key, V value) {
      throw unsupported();
    }

    @Override
    public boolean remove(Object key, Object value) {
      throw unsupported();
    }

    @Override
    public boolean replace(K key, V oldValue, V newValue) {
      throw unsupported();
    }

    @Override
    public V replace(K key, V value) {
      throw unsupported();
    }

    @Override
    public void forEach(BiConsumer<? super K, ? super V> action) {
      map.forEach(action);
    }

    @Override
    public V computeIfAbsent(K key, Function<? super K, ? extends V> mappingFunction) {
      return map.computeIfAbsent(key, mappingFunction);
    }

    @Override
    public V computeIfPresent(K key, BiFunction<? super K, ? super V, ? extends V> remappingFunction) {
      return map.computeIfPresent(key, remappingFunction);
    }

    @Override
    public V compute(K key, BiFunction<? super K, ? super V, ? extends V> remappingFunction) {
      return map.compute(key, remappingFunction);
    }

    @Override
    public V merge(K key, V value, BiFunction<? super V, ? super V, ? extends V> remappingFunction) {
      return map.merge(key, value, remappingFunction);
    }

    @Override
    public V getOrDefault(Object key, V defaultValue) {
      return map.getOrDefault(key, defaultValue);
    }

    private UnsupportedOperationException unsupported() {
      String methodName = Thread.currentThread().getStackTrace()[2].getMethodName();
      return new UnsupportedOperationException(methodName + " is restricted");
    }
  }
}

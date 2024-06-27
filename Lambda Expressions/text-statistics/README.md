# Text Statistics

The purpose of this task is to practice using `Map` functional methods.

Duration: _1 hour_

## Description

In this task, you will implement `TextStatistics` methods, which return `TokenStatisticsCalculator` implementations.
`Map` methods like `put` or `replace` are not allowed. You can use the methods `compute`, `merge`, `computeIfAbsent`,
and `computeIfPresent` to change `Map` content.

The `TokenStatisticsCalculator` accepts `Map<K, V>` and `Iterator<K>`. The iterator provides tokens for processing.
The map is used as input/output parameter, and all the changes must be applied to it.

## Requirements

The `TextStatistics` class has four methods:

1. `countTokens`
   * Counts occurrences of tokens appearance in the iterator's sequence.
   * The map must contain tokens as keys and the number of times they occur in the sequence.
2. `countKnownTokensWithMaxLimit`
   * Counts tokens already present in the map.
   * If a token occurs more times than indicated by `maxLimit`, the corresponding value in the map should not change.
3. `findUnknownTokensOfTypes`
   * Marks tokens that are not in the map and of a specific type.
   * If a token is of the type specified by the types argument, the value must be set to true.
   * If a token does not meet the requirements, it cannot be added to the map.
4. `combinedSearch`
   * Defines a specific code for tokens.
   * If the map does not contain a token from the iterators sequence,
     the token must be stored in the map with a value of **-1**.
   * If the map contains a token from the sequence, its type is specified by the types argument, and its value is less
     than `maxLimit`, it must be stored with a value of **0**.
   * If the map contains a token, its type is specified by the types argument, and its value exceeds `maxLimit`,
     it must be stored with a value of **1**.
   * If the map contains a token, its value is less than `maxLimit`,
     and its type is not specified by the types argument, it must be stored with a value of **2**.
   * If the map contains a token, its type is not specified by the types argument, and its value exceed `maxLimit`,
     it must be stored with a value of **3**.

## Examples

```java
Map<Token, Long> map = new HashMap<>();
List<Token> sequence = List.of(
  new Token("name"),
  new Token("name"),
  new Token("name"),
  new Token("surname"),
  new Token("surname"),
  new Token("age")
);
TextStatistics.coundTokens().calculate(map, sequence.iterator());
assert map.get("name") == 3
assert map.get("surname") == 2
assert map.get("age") == 1
```

# Cat Generator

The purpose of this exercise is to train you to work with the operations _generate_ and _iterate_.

Duration: _30 minutes_

### Description

You need to implement methods that generate new cats and food.

Please, implement methods in the [CatGenerator](src/main/java/com/epam/autotasks/CatGenerator.java) class.

- `public static List<Cat> generateCats(int count)` - returns a list of new cats whose size is _count_.
- `public static long generateFood(int familySize, int skip)` - returns the amount of food needed to feed a family
  whose size is _familySize_. _skip_ represents a number of young cats who already got their food.

<mark style="background-color: lightblue">Notes about the _generateFood_ method</mark>

1. You have a family of cats, where each cat eat twice as mush as a previous one. Calculate how much food you need to feed this cat family.
2. If then younger cats have already gotten their food, their portions should be excluded from the calculation.
3. Please throw IllegalArgumentException, when the _familySize_ and/or _skip_ values are negative.
   The exception message should be _"Input arguments cannot be negative"_.
4. If a result value exceeds Long.MAX_VALUE, the method should throw ArithmeticException.
5. If _skip_ is greater than _familySize_, the method should return _0_;
6. The minimum portion of food is 4 grams.

### Examples

```java
        return generateCats(4);
// Sample result
//{
// Cat("Alex", 1, MAINE_COON),
// Cat("Fluff", 4, PERSIAN),
// Cat("Chester", 5, SIBERIAN),
// Cat("Choo", 2, MUNCHKIN)
// }
```

---

```java
        return generateFood(4,0);
// 60
```

---

```java
        return generateFood(0,0);
// 0
```

---

```java
        return generateFood(6,5);
// 128
```

---

```java
        return generateFood(9,10);
// 0
```

---

```java
        return generateFood(19438,0);
// java.lang.ArithmeticException: long overflow
```

---

```java
        return generateFood(9,-10);
// java.lang.IllegalArgumentException: Input arguments cannot be negative
```
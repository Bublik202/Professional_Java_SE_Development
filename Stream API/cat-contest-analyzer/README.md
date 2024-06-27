# Cat Contest Analyzer

The purpose of this exercise is to train you to work with the short terminal operations _max_, _min_, _average_, _findFirst_, _findAny_, _allMatch_ and _noneMatch_ and review some of the operations from previous tasks.

Duration: _1 hour_

### Description

You need to implement methods that analyze contest results and choose the leaders.

Please, implement methods in the [CatContestAnalyzer](src/main/java/com/epam/autotasks/CatContestAnalyzer.java) class.

- `public Integer getMaxResult(List<Cat> cats)` - returns the maximum result among the given _cats_. If no results are found, the default value is -1.
- `public Integer getMinResult(List<Cat> cats)` - returns the minimum result among the given _cats_, excluding the result _0_. If no results are found, the default value is -1.
- `public OptionalDouble getAverageResultByBreed(List<Cat> cats, Cat.Breed breed)` - returns the average result among the given _cats_ by a specific _breed_.
- `public Optional<Cat> getWinner(List<Cat> cats)` - returns a winner among the given _cats_ based on the results of all three competitions.
- `public List<Cat> getThreeLeaders(List<Cat> cats)` - returns three leaders among the given _cats_ based on the results of all three competitions.
- `public boolean validateResultSumNotNull(List<Cat> cats)` - returns true if all _cats_ have _sum > 0_.
- `public boolean validateAllResultsSet(List<Cat> cats)` - returns true if there are no _cats_ with _any contest result = 0_.
- `public Optional<Cat> findAnyWithAboveAverageResultByBreed(List<Cat> cats, Cat.Breed breed)` - returns any cat among the given _cats_ with a contest result greater than the average result by breed.

1. All cat fields are nullable.
2. You should also override the _equals/hashcode_ methods to compare properly.

### Examples

```java
        List<Cat> cats = new ArrayList<>();
        cats.add(Cat.builder().name("Alex").age(1).breed(Cat.Breed.MAINE_COON).contestResult(new ContestResult(93, 59, 93).build());
        cats.add(Cat.builder().name("Chester").age(5).breed(Cat.Breed.SIBERIAN).contestResult(new ContestResult(35, 57, 67).build());
        cats.add(Cat.builder().name("Fluff").age(4).breed(Cat.Breed.PERSIAN).contestResult(new ContestResult(39, 77, 85).build());
        cats.add(Cat.builder().name("Kit").age(9).breed(Cat.Breed.MAINE_COON).contestResult(new ContestResult(88, 59, 49).build());
        cats.add(Cat.builder().name("Boo").age(3).breed(Cat.Breed.MAINE_COON).contestResult(new ContestResult(56, 89, 77).build());

        return getMaxResult(cats);
// 245
```

---

```java
        List<Cat> cats = new ArrayList<>();
        cats.add(Cat.builder().name("Alex").age(1).breed(Cat.Breed.MAINE_COON).contestResult(new ContestResult(93, 59, 93).build());
        cats.add(Cat.builder().name("Chester").age(5).breed(Cat.Breed.SIBERIAN).contestResult(new ContestResult(35, 57, 67).build());
        cats.add(Cat.builder().name("Fluff").age(4).breed(Cat.Breed.PERSIAN).contestResult(new ContestResult(39, 77, 85).build());
        cats.add(Cat.builder().name("Kit").age(9).breed(Cat.Breed.MAINE_COON).contestResult(new ContestResult(88, 59, 49).build());
        cats.add(Cat.builder().name("Boo").age(3).breed(Cat.Breed.MAINE_COON).contestResult(new ContestResult(56, 89, 77).build());

        return getMinResult(cats);
// 159
```

---

```java
        List<Cat> cats = new ArrayList<>();
        cats.add(Cat.builder().name("Alex").age(1).breed(Cat.Breed.MAINE_COON).contestResult(new ContestResult(93, 59, 93).build());
        cats.add(Cat.builder().name("Chester").age(5).breed(Cat.Breed.SIBERIAN).contestResult(new ContestResult(35, 57, 67).build());
        cats.add(Cat.builder().name("Fluff").age(4).breed(Cat.Breed.PERSIAN).contestResult(new ContestResult(39, 77, 85).build());
        cats.add(Cat.builder().name("Kit").age(9).breed(Cat.Breed.MAINE_COON).contestResult(new ContestResult(88, 59, 49).build());
        cats.add(Cat.builder().name("Boo").age(3).breed(Cat.Breed.MAINE_COON).contestResult(new ContestResult(56, 89, 77).build());

        return getAverageResultByBreed(cats, Cat.Breed.MAINE_COON);
// 221.0
```

---

```java
        List<Cat> cats = new ArrayList<>();
        cats.add(Cat.builder().name("Alex").age(1).breed(Cat.Breed.MAINE_COON).contestResult(new ContestResult(93, 59, 93).build());
        cats.add(Cat.builder().name("Chester").age(5).breed(Cat.Breed.SIBERIAN).contestResult(new ContestResult(35, 57, 67).build());
        cats.add(Cat.builder().name("Fluff").age(4).breed(Cat.Breed.PERSIAN).contestResult(new ContestResult(39, 77, 85).build());
        cats.add(Cat.builder().name("Kit").age(9).breed(Cat.Breed.MAINE_COON).contestResult(new ContestResult(88, 59, 49).build());
        cats.add(Cat.builder().name("Boo").age(3).breed(Cat.Breed.MAINE_COON).contestResult(new ContestResult(56, 89, 77).build());

        return getWinner(cats);
// Optional.of(Cat("Alex", 1, MAINE_COON, ContestResult(93, 59, 93, 245)))
```

---

```java
        List<Cat> cats = new ArrayList<>();
        cats.add(Cat.builder().name("Alex").age(1).breed(Cat.Breed.MAINE_COON).contestResult(new ContestResult(93, 59, 93).build());
        cats.add(Cat.builder().name("Chester").age(5).breed(Cat.Breed.SIBERIAN).contestResult(new ContestResult(35, 57, 67).build());
        cats.add(Cat.builder().name("Fluff").age(4).breed(Cat.Breed.PERSIAN).contestResult(new ContestResult(39, 77, 85).build());
        cats.add(Cat.builder().name("Kit").age(9).breed(Cat.Breed.MAINE_COON).contestResult(new ContestResult(88, 59, 49).build());
        cats.add(Cat.builder().name("Boo").age(3).breed(Cat.Breed.MAINE_COON).contestResult(new ContestResult(56, 89, 77).build());

        return getThreeLeaders(cats);
// {
// Cat("Alex", 1, MAINE_COON, ContestResult(93, 59, 93, 245))),
// Cat("Boo", 3, MAINE_COON, ContestResult(56, 89, 77, 222))),
// Cat("Fluff", 4, PERSIAN, ContestResult(39, 77, 85, 201)))
// }
```

---

```java
        List<Cat> cats = new ArrayList<>();
        cats.add(Cat.builder().name("Alex").age(1).breed(Cat.Breed.MAINE_COON).contestResult(new ContestResult(93, 59, 93).build());
        cats.add(Cat.builder().name("Chester").age(5).breed(Cat.Breed.SIBERIAN).contestResult(new ContestResult(35, 57, 67).build());
        cats.add(Cat.builder().name("Fluff").age(4).breed(Cat.Breed.PERSIAN).contestResult(new ContestResult(39, 77, 85).build());
        cats.add(Cat.builder().name("Kit").age(9).breed(Cat.Breed.MAINE_COON).contestResult(new ContestResult(88, 59, 49).build());
        cats.add(Cat.builder().name("Boo").age(3).breed(Cat.Breed.MAINE_COON).contestResult(new ContestResult(56, 89, 77).build());

        return validateResultSumNotNull(cats);
// true
```

---

```java
        List<Cat> cats = new ArrayList<>();
        cats.add(Cat.builder().name("Alex").age(1).breed(Cat.Breed.MAINE_COON).contestResult(new ContestResult(93, 59, 93).build());
        cats.add(Cat.builder().name("Chester").age(5).breed(Cat.Breed.SIBERIAN).contestResult(new ContestResult(0, 0, 0).build());
        cats.add(Cat.builder().name("Fluff").age(4).breed(Cat.Breed.PERSIAN).contestResult(new ContestResult(39, 77, 85).build());
        cats.add(Cat.builder().name("Kit").age(9).breed(Cat.Breed.MAINE_COON).contestResult(new ContestResult(88, 59, 49).build());
        cats.add(Cat.builder().name("Boo").age(3).breed(Cat.Breed.MAINE_COON).contestResult(new ContestResult(56, 89, 0).build());

        return validateResultSumNotNull(cats);
// false
```

---

```java
        List<Cat> cats = new ArrayList<>();
        cats.add(Cat.builder().name("Alex").age(1).breed(Cat.Breed.MAINE_COON).contestResult(new ContestResult(93, 59, 93).build());
        cats.add(Cat.builder().name("Chester").age(5).breed(Cat.Breed.SIBERIAN).contestResult(new ContestResult(35, 57, 67).build());
        cats.add(Cat.builder().name("Fluff").age(4).breed(Cat.Breed.PERSIAN).contestResult(new ContestResult(39, 77, 85).build());
        cats.add(Cat.builder().name("Kit").age(9).breed(Cat.Breed.MAINE_COON).contestResult(new ContestResult(88, 59, 49).build());
        cats.add(Cat.builder().name("Boo").age(3).breed(Cat.Breed.MAINE_COON).contestResult(new ContestResult(56, 89, 77).build());

        return validateAllResultsSet(cats);
// true
```

---

```java
        List<Cat> cats = new ArrayList<>();
        cats.add(Cat.builder().name("Alex").age(1).breed(Cat.Breed.MAINE_COON).contestResult(new ContestResult(93, 59, 93).build());
        cats.add(Cat.builder().name("Chester").age(5).breed(Cat.Breed.SIBERIAN).contestResult(new ContestResult(35, 57, 0).build());
        cats.add(Cat.builder().name("Fluff").age(4).breed(Cat.Breed.PERSIAN).contestResult(new ContestResult(39, 0, 85).build());
        cats.add(Cat.builder().name("Kit").age(9).breed(Cat.Breed.MAINE_COON).contestResult(new ContestResult(88, 59, 49).build());
        cats.add(Cat.builder().name("Boo").age(3).breed(Cat.Breed.MAINE_COON).contestResult(new ContestResult(0, 89, 0).build());

        return validateAllResultsSet(cats);
// false
```

---

```java
        List<Cat> cats = new ArrayList<>();
        cats.add(Cat.builder().name("Alex").age(1).breed(Cat.Breed.MAINE_COON).contestResult(new ContestResult(93, 59, 93).build());
        cats.add(Cat.builder().name("Chester").age(5).breed(Cat.Breed.SIBERIAN).contestResult(new ContestResult(35, 57, 67).build());
        cats.add(Cat.builder().name("Fluff").age(4).breed(Cat.Breed.PERSIAN).contestResult(new ContestResult(39, 77, 85).build());
        cats.add(Cat.builder().name("Kit").age(9).breed(Cat.Breed.MAINE_COON).contestResult(new ContestResult(88, 59, 49).build());
        cats.add(Cat.builder().name("Boo").age(3).breed(Cat.Breed.MAINE_COON).contestResult(new ContestResult(56, 89, 77).build());

        return findAnyWithAboveAverageResultByBreed(cats, Cat.Breed.MAINE_COON);
// Optional.of(Cat("Alex", 1, MAINE_COON, ContestResult(93, 59, 93, 245)))
```
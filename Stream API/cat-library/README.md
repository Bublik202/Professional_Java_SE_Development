# Cat Library

The purpose of this task is to give you some practice working with the collectors _toSet_, _toMap_, _toCollection_, _groupingBy_, _mapping_ and _joining_.

Duration: _45 minutes_

### Description

In this task, you have to create a service that processes a dataset of the cat's contest results. Please implement methods to transform cat results using external libraries.

Contest description: <br>
The running contest checks how fast a cat runs after a toy mouse.<br>
The jumping contest checks how high a cat jumps to get a delicious snack.<br>
The purring contest checks how loud a cat purrs.

Please implement methods in the [CatLibrary](src/main/java/com/epam/autotasks/CatLibrary.java) class.

- `public Map<String, Cat> mapCatsByName(List<Cat> cats)` - returns a map with a cat's name as a key.
- `public Map<Cat.Breed, Set<Cat>> mapCatsByBreed(List<Cat> cats)` - returns a map with a breed as a key and _cats_, categorized by breed, as a value.
- `public Map<Cat.Breed, String> mapCatNamesByBreed(List<Cat> cats)` - returns a map with a breed as a key and a string of _cats'_ names, categorized by breed, as a value. Cats with _null_ or _empty_ names should be excluded. The string pattern is "Cat names: [name1], [name2], [name3], ..."
- `public Map<Cat.Breed, Double> mapAverageResultByBreed(List<Cat> cats)` - returns a map with a breed as a key and the value is the average contest result for this breed.
- `public SortedSet<Cat> getOrderedCatsByContestResults(List<Cat> cats)` - returns a set of given _cat_ objects ordered by contest results (from highest to lowest).

1. All cat fields can be null except for the last method.
2. All cat names are unique.

### Examples

```java
        List<Cat> cats = new ArrayList<>();
        cats.add(Cat.builder().name("Alex").age(1).breed(Cat.Breed.MAINE_COON).weight(5).awards(24).contestResult(new ContestResult(93, 59, 93).build());
        cats.add(Cat.builder().name("Chester").age(5).breed(Cat.Breed.SIBERIAN).weight(4).awards(15).contestResult(new ContestResult(35, 57, 67).build());
        cats.add(Cat.builder().name("Fluff").age(4).breed(Cat.Breed.PERSIAN).weight(6).awards(27).contestResult(new ContestResult(39, 77, 85).build());
        cats.add(Cat.builder().name("Kit").age(9).breed(Cat.Breed.MAINE_COON).weight(11).awards(9).contestResult(new ContestResult(88, 59, 49).build());
        cats.add(Cat.builder().name("Boo").age(3).breed(Cat.Breed.MAINE_COON).weight(6).awards(16).contestResult(new ContestResult(56, 89, 77).build());

        return mapCatsByName(cats);
// {
// {"Alex", Cat("Alex", 1, MAINE_COON, 5, 24, ContestResult(93, 59, 93, 245)))},
// {"Chester", Cat("Chester", 5, SIBERIAN, 4, 15, ContestResult(35, 57, 67, 159)))},
// {"Fluff", Cat("Fluff", 4, PERSIAN, 6, 27, ContestResult(39, 77, 85, 201)))}
// {"Kit", Cat("Kit", 9, MAINE_COON, 11, 9, ContestResult(88, 59, 49, 196)))}
// {"Boo", Cat("Boo", 3, MAINE_COON, 6, 16, ContestResult(56, 89, 77, 222)))}
// }
```

---

```java
        List<Cat> cats = new ArrayList<>();
        cats.add(Cat.builder().name("Alex").age(1).breed(Cat.Breed.MAINE_COON).weight(5).awards(24).contestResult(new ContestResult(93, 59, 93).build());
        cats.add(Cat.builder().name("Chester").age(5).breed(Cat.Breed.SIBERIAN).weight(4).awards(15).contestResult(new ContestResult(35, 57, 67).build());
        cats.add(Cat.builder().name("Fluff").age(4).breed(Cat.Breed.PERSIAN).weight(6).awards(27).contestResult(new ContestResult(39, 77, 85).build());
        cats.add(Cat.builder().name("Kit").age(9).breed(Cat.Breed.MAINE_COON).weight(11).awards(9).contestResult(new ContestResult(88, 59, 49).build());
        cats.add(Cat.builder().name("Boo").age(3).breed(Cat.Breed.MAINE_COON).weight(6).awards(16).contestResult(new ContestResult(56, 89, 77).build());

        return mapCatsByBreed(cats);
// {
// {"MAINE_COON", {
//      Cat("Alex", 1, MAINE_COON, 5, 24, ContestResult(93, 59, 93, 245))),
//      Cat("Kit", 9, MAINE_COON, 11, 9, ContestResult(88, 59, 49, 196))),
//      Cat("Boo", 3, MAINE_COON, 6, 16, ContestResult(56, 89, 77, 222)))}}
// {"SIBERIAN", {Cat("Chester", 5, SIBERIAN, 4, 15, ContestResult(35, 57, 67, 159)))}},
// {"Fluff", {Cat("Fluff", 4, PERSIAN, 6, 27, ContestResult(39, 77, 85, 201)))}}
// }
```

---

```java
        List<Cat> cats = new ArrayList<>();
        cats.add(Cat.builder().name("Alex").age(1).breed(Cat.Breed.MAINE_COON).weight(5).awards(24).contestResult(new ContestResult(93, 59, 93).build());
        cats.add(Cat.builder().name("Chester").age(5).breed(Cat.Breed.SIBERIAN).weight(4).awards(15).contestResult(new ContestResult(35, 57, 67).build());
        cats.add(Cat.builder().name("Fluff").age(4).breed(Cat.Breed.PERSIAN).weight(6).awards(27).contestResult(new ContestResult(39, 77, 85).build());
        cats.add(Cat.builder().name("Kit").age(9).breed(Cat.Breed.MAINE_COON).weight(11).awards(9).contestResult(new ContestResult(88, 59, 49).build());
        cats.add(Cat.builder().name("Boo").age(3).breed(Cat.Breed.MAINE_COON).weight(6).awards(16).contestResult(new ContestResult(56, 89, 77).build());

        return mapCatNamesByBreed(cats);
// {
// {"MAINE_COON", "Cat names: Alex, Kit, Boo."},
// {"SIBERIAN", "Cat names: Chester."},
// {"PERSIAN", "Cat names: Fluff."}
// }
```

---

```java
        List<Cat> cats = new ArrayList<>();
        cats.add(Cat.builder().name("Alex").age(1).breed(Cat.Breed.MAINE_COON).weight(5).awards(24).contestResult(new ContestResult(93, 59, 93).build());
        cats.add(Cat.builder().name("Chester").age(5).breed(Cat.Breed.SIBERIAN).weight(4).awards(15).contestResult(new ContestResult(35, 57, 67).build());
        cats.add(Cat.builder().name("Fluff").age(4).breed(Cat.Breed.PERSIAN).weight(6).awards(27).contestResult(new ContestResult(39, 77, 85).build());
        cats.add(Cat.builder().name("Kit").age(9).breed(Cat.Breed.MAINE_COON).weight(11).awards(9).contestResult(new ContestResult(88, 59, 49).build());
        cats.add(Cat.builder().name("Boo").age(3).breed(Cat.Breed.MAINE_COON).weight(6).awards(16).contestResult(new ContestResult(56, 89, 77).build());

        return mapAverageResultByBreed(cats);
// {
// {"MAINE_COON", 221},
// {"SIBERIAN", 159},
// {"PERSIAN", 201}
// }
```

---

```java
        List<Cat> cats = new ArrayList<>();
        cats.add(Cat.builder().name("Alex").age(1).breed(Cat.Breed.MAINE_COON).weight(5).awards(24).contestResult(new ContestResult(93, 59, 93).build());
        cats.add(Cat.builder().name("Chester").age(5).breed(Cat.Breed.SIBERIAN).weight(4).awards(15).contestResult(new ContestResult(35, 57, 67).build());
        cats.add(Cat.builder().name("Fluff").age(4).breed(Cat.Breed.PERSIAN).weight(6).awards(27).contestResult(new ContestResult(39, 77, 85).build());
        cats.add(Cat.builder().name("Kit").age(9).breed(Cat.Breed.MAINE_COON).weight(11).awards(9).contestResult(new ContestResult(88, 59, 49).build());
        cats.add(Cat.builder().name("Boo").age(3).breed(Cat.Breed.MAINE_COON).weight(6).awards(16).contestResult(new ContestResult(56, 89, 77).build());

        return getOrderedCatsByContestResults(cats);
// {
// Cat("Alex", 1, MAINE_COON, 5, 24, ContestResult(93, 59, 93, 245)),
// Cat("Boo", 3, MAINE_COON, 6, 16, ContestResult(56, 89, 77, 222)),
// Cat("Fluff", 4, PERSIAN, 6, 27, ContestResult(39, 77, 85, 201)),
// Cat("Kit", 9, MAINE_COON, 11, 9, ContestResult(88, 59, 49, 196)),
// Cat("Chester", 5, SIBERIAN, 4, 15, ContestResult(35, 57, 67, 159))
// }
```

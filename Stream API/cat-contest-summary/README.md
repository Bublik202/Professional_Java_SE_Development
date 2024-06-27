# Cat Contest Summary

The purpose of this exercise is to train you to work with the terminal operation _reduce_.

Duration: _30 minutes_

### Description

You need to implement the methods that help with the contest arrangement.

Contest description: <br>
Running contest checks how fast a cat runs after a toy mouse.<br>
Jumping contest checks how high a cat jumps to get a delicious snack.<br>
Purring contest checks how loud a cat purrs.

Please implement methods in the [CatContestHelper](src/main/java/com/epam/autotasks/CatContestHelper.java) class.

- `public Integer getCarrierNumber(List<Cat> cats)` - returns the number of carriers that are required to transfer the given _cats_. Each carrier is designed for 30kg of weight. The number rounds up. If weight is 0 (it can happen if the actual cat weight is 0-0,5kg), count it as 1 in the calculation.
- `public String getCarrierId(List<Cat> cats)` - returns a carrier ID that is generated based on the information about the given _cats_. This ID contains only digits and letters without any commas, dots and etc. ID pattern \[CF\]\[first three letters of a first cat name\]\[first three letters of a first cat breed\]\[first three letters of a second cat name\]\[first three letters of a second cat breed\]...
- `public Integer countTeamAwards(List<Cat> cats)` - returns a number of awards that belong to a team of the given _cats_.

All cat fields are nullable.

### Examples

```java
        List<Cat> cats = new ArrayList<>();
        cats.add(Cat.builder().name("Alex").age(1).breed(Cat.Breed.MAINE_COON).weight(5).awards(24).contestResult(new ContestResult(93, 59, 93).build());
        cats.add(Cat.builder().name("Chester").age(5).breed(Cat.Breed.SIBERIAN).weight(4).awards(15).contestResult(new ContestResult(35, 57, 67).build());
        cats.add(Cat.builder().name("Fluff").age(4).breed(Cat.Breed.PERSIAN).weight(6).awards(27).contestResult(new ContestResult(39, 77, 85).build());
        cats.add(Cat.builder().name("Kit").age(9).breed(Cat.Breed.MAINE_COON).weight(11).awards(9).contestResult(new ContestResult(88, 59, 49).build());
        cats.add(Cat.builder().name("Boo").age(3).breed(Cat.Breed.MAINE_COON).weight(6).awards(16).contestResult(new ContestResult(56, 89, 77).build());

        return getCarrierNumber(cats);
// 2
```

---

```java
        List<Cat> cats = new ArrayList<>();
        cats.add(Cat.builder().name("Alex").age(1).breed(Cat.Breed.MAINE_COON).weight(5).awards(24).contestResult(new ContestResult(93, 59, 93).build());
        cats.add(Cat.builder().name("Chester").age(5).breed(Cat.Breed.SIBERIAN).weight(4).awards(15).contestResult(new ContestResult(35, 57, 67).build());
        cats.add(Cat.builder().name("Fluff").age(4).breed(Cat.Breed.PERSIAN).weight(6).awards(27).contestResult(new ContestResult(39, 77, 85).build());
        cats.add(Cat.builder().name("Kit").age(9).breed(Cat.Breed.MAINE_COON).weight(11).awards(9).contestResult(new ContestResult(88, 59, 49).build());
        cats.add(Cat.builder().name("Boo").age(3).breed(Cat.Breed.MAINE_COON).weight(6).awards(16).contestResult(new ContestResult(56, 89, 77).build());

        return getCarrierId(cats);
// CFALEMAICHESIBFLUPERKITMAIBOOMAI
```

---

```java
        List<Cat> cats = new ArrayList<>();
        cats.add(Cat.builder().name("Alex").age(1).breed(Cat.Breed.MAINE_COON).weight(5).awards(24).contestResult(new ContestResult(93, 59, 93).build());
        cats.add(Cat.builder().name("Chester").age(5).breed(Cat.Breed.SIBERIAN).weight(4).awards(15).contestResult(new ContestResult(35, 57, 67).build());
        cats.add(Cat.builder().name("Fluff").age(4).breed(Cat.Breed.PERSIAN).weight(6).awards(27).contestResult(new ContestResult(39, 77, 85).build());
        cats.add(Cat.builder().name("Kit").age(9).breed(Cat.Breed.MAINE_COON).weight(11).awards(9).contestResult(new ContestResult(88, 59, 49).build());
        cats.add(Cat.builder().name("Boo").age(3).breed(Cat.Breed.MAINE_COON).weight(6).awards(16).contestResult(new ContestResult(56, 89, 77).build());

        return countTeamAwards(cats);
// 91
```
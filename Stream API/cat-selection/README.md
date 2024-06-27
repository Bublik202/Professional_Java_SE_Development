# Cat Selection

The purpose of this exercise is to train you to work with the intermediate operations _limit_, _skip_, _takewhile_, _dropwhile_, _distinct_ and _sorted_.

Duration: _1 hour_

### Description
You need to implement various methods that select cats for further statistics and shelter reports.

Please implement methods in the [CatSelection](src/main/java/com/epam/autotasks/CatSelection.java) class.
- `public List<Cat> getFirstNCatsSortedByComparator(List<Cat> cats, Comparator<Cat> comparator, int number)` - returns first _number_ of cats from a cat list, sorted by a specific criterion.
- `public List<Cat> getWithoutFirstNCatsSortedByComparator(List<Cat> cats, Comparator<Cat> comparator, int number)` - returns a cat list, sorted by a specific criterion, excluding first _number_ of cats.
- `public List<Cat> getSmallCats(List<Cat> cats, int threshold)` - returns a list of cats whose weight is smaller than _threshold_.
- `public List<Cat> getTallCats(List<Cat> cats, int threshold)` - returns a list of cats who are taller than _threshold_.
- `public List<String> getUniqueNames(List<Cat> cats)` - returns a list of unique cat names.

1. All cat fields are non-nullable.
2. Methods should return an empty list when given an empty list.
3. Please, use valid threshold values. Choose the numbers  yourself, but try to be realistic. :) For instance, if a given threshold is -999 or 999, methods throw a Runtime exception.

<mark style="background-color: lightblue">Please, do not use _filter_ operation in these methods.</mark>

### Examples


```java
        List<Cat> cats = new ArrayList<>();
        cats.add(Cat.builder().name("Alex").age(1).weight(12).height(35).breed(Cat.Breed.MAINE_COON).build());
        cats.add(Cat.builder().name("Fluff").age(4).weight(7).height(20).breed(Cat.Breed.PERSIAN).build());
        cats.add(Cat.builder().name("Chester").age(5).weight(6).height(22).breed(Cat.Breed.SIBERIAN).build());
        cats.add(Cat.builder().name("Kit").age(9).weight(9).height(27).breed(Cat.Breed.MAINE_COON).build());

        return getFirstNCatsSortedByComparator(cats, Comparator.comparing(Cat::getName), 2);
//{
// Cat("Alex", 1, 12, 35, MAINE_COON),
// Cat("Chester", 5, 6, 22, SIBERIAN)
// }
```

---

```java
        List<Cat> cats = new ArrayList<>();
        cats.add(Cat.builder().name("Fluff").age(4).weight(7).height(20).breed(Cat.Breed.PERSIAN).build());
        cats.add(Cat.builder().name("Chester").age(5).weight(6).height(22).breed(Cat.Breed.SIBERIAN).build());
        cats.add(Cat.builder().name("Alex").age(1).weight(12).height(35).breed(Cat.Breed.MAINE_COON).build());
        cats.add(Cat.builder().name("Kit").age(9).weight(9).height(27).breed(Cat.Breed.MAINE_COON).build());

        return getWithoutFirstNCatsSortedByComparator(cats, Comparator.comparing(Cat::Age), 2);
//{
// Cat("Chester", 5, 6, 22, SIBERIAN),
// Cat("Kit", 9, 9, 27, MAINE_COON)
// }
```

---

```java
        List<Cat> cats = new ArrayList<>();
        cats.add(Cat.builder().name("Alex").age(1).weight(12).height(35).breed(Cat.Breed.MAINE_COON).build());
        cats.add(Cat.builder().name("Fluff").age(4).weight(7).height(20).breed(Cat.Breed.PERSIAN).build());
        cats.add(Cat.builder().name("Chester").age(5).weight(6).height(22).breed(Cat.Breed.SIBERIAN).build());
        cats.add(Cat.builder().name("Kit").age(9).weight(9).height(27).breed(Cat.Breed.MAINE_COON).build());
        cats.add(Cat.builder().name("Boo").age(2).weight(2).height(14).breed(Cat.Breed.BRITISH).build());

        return getSmallCats(cats, 7);
//{
// Cat("Boo", 2, 2, 14, BRITISH),
// Cat("Chester", 5, 6, 22, SIBERIAN),
// }
```

---

```java
        List<Cat> cats = new ArrayList<>();
        cats.add(Cat.builder().name("Alex").age(1).weight(12).height(35).breed(Cat.Breed.MAINE_COON).build());
        cats.add(Cat.builder().name("Fluff").age(4).weight(7).height(20).breed(Cat.Breed.PERSIAN).build());
        cats.add(Cat.builder().name("Chester").age(5).weight(6).height(22).breed(Cat.Breed.SIBERIAN).build());
        cats.add(Cat.builder().name("Kit").age(9).weight(9).height(27).breed(Cat.Breed.MAINE_COON).build());
        cats.add(Cat.builder().name("Boo").age(2).weight(2).height(14).breed(Cat.Breed.BRITISH).build());

        return getTallCats(cats, 20);
//{
// Cat("Chester", 5, 6, 22, SIBERIAN),
// Cat("Kit", 9, 9, 27, MAINE_COON)
// Cat("Alex", 1, 12, 35, MAINE_COON)
// }
```

---

```java
        List<Cat> cats = new ArrayList<>();
        cats.add(Cat.builder().name("Alex").age(1).weight(12).height(35).breed(Cat.Breed.MAINE_COON).build());
        cats.add(Cat.builder().name("Beep").age(5).weight(6).height(22).breed(Cat.Breed.SIBERIAN).build());
        cats.add(Cat.builder().name("Alex").age(9).weight(9).height(27).breed(Cat.Breed.MAINE_COON).build());
        cats.add(Cat.builder().name("Boo").age(2).weight(2).height(14).breed(Cat.Breed.MUNCHKIN).build());
        cats.add(Cat.builder().name("Fluff").age(4).weight(7).height(20).breed(Cat.Breed.PERSIAN).build());

        return getUniqueNames(cats);
//{ Alex, Beep, Boo, Fluff }
```

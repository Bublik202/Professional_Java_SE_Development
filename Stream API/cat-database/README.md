# Cat Database

The purpose of this exercise is to train you to work with the intermediate operations _map_ and _filter_.

Duration: _30 minutes_

### Description

Please implement the methods in the [CatDatabase](src/main/java/com/epam/autotasks/CatDatabase.java) class.
- `public List<String> getCatNamesByBreed(List<Cat> cats, Cat.Breed breed)` - returns a list of cat names for a specific breed.
- `public List<Cat> filterYoungerCatsByAge(List<Cat> cats, Integer age)` - returns a list of cats who are _age_ years old or younger.
- `public List<Cat> filterCatsByNamePrefix(List<Cat> cats, String prefix)` - returns a list of cats whose name starts with _prefix_.

1. The cat _name_, _age_ and _breed_ fields can be null.
2. Search by _prefix_ is case-insensitive.
3. Methods should return an empty list when given an empty list.

### Examples


```java
        List<Cat> cats = new ArrayList<>();
        cats.add(Cat.builder().name("Alex").age(1).breed(Cat.Breed.MAINE_COON).build());
        cats.add(Cat.builder().name("Chester").age(5).breed(Cat.Breed.SIBERIAN).build());
        cats.add(Cat.builder().name("Fluff").age(4).breed(Cat.Breed.PERSIAN).build());
        cats.add(Cat.builder().name("Kit").age(9).breed(Cat.Breed.MAINE_COON).build());

        return getCatNamesByBreed(cats, Cat.Breed.MAINE_COON);
//{Alex, Kit}
```

---

```java
        List<Cat> cats = new ArrayList<>();
        cats.add(Cat.builder().name("Alex").age(1).breed(Cat.Breed.MAINE_COON).build());
        cats.add(Cat.builder().name("Chester").age(5).breed(Cat.Breed.SIBERIAN).build());
        cats.add(Cat.builder().name("Fluff").age(4).breed(Cat.Breed.PERSIAN).build());
        cats.add(Cat.builder().name("Kit").age(9).breed(Cat.Breed.MAINE_COON).build());

        return filterYoungerCatsByAge(cats, 4);
//{
// Cat("Alex", 1, MAINE_COON),
// Cat("Fluff", 4, PERSIAN)
// }
```

---

```java
        List<Cat> cats = new ArrayList<>();
        cats.add(Cat.builder().name("Alex").age(1).breed(Cat.Breed.MAINE_COON).build());
        cats.add(Cat.builder().name("Chester").age(5).breed(Cat.Breed.SIBERIAN).build());
        cats.add(Cat.builder().name("Fluff").age(4).breed(Cat.Breed.PERSIAN).build());
        cats.add(Cat.builder().name("Kit").age(9).breed(Cat.Breed.MAINE_COON).build());
        cats.add(Cat.builder().name("Choo").age(2).breed(Cat.Breed.MUNCHKIN).build());

        return filterCatsByNamePrefix(cats, "C");
//{
// Cat("Chester", 5, SIBERIAN),
// Cat("Choo", 2, MUNCHKIN)
// }
```
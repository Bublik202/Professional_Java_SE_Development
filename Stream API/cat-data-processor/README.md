# Cat Data Processor

The purpose of this task is to give you some practice working with custom collectors.

Duration: _45 minutes_

### Description

In this task, you are provided with the dataset of the cat's contest results. You have to implement methods to transform cat results using external libraries.
You can find more information about these libraries via the following links:
1. [Guava Immutable table](https://guava.dev/releases/21.0/api/docs/com/google/common/collect/ImmutableTable.html)
2. [JSONArray](https://stleary.github.io/JSON-java/org/json/JSONArray.html)

Contest description: <br>
The running contest checks how fast a cat runs after a toy mouse.<br>
The jumping contest checks how high a cat jumps to get a delicious snack.<br>
The purring contest checks how loud a cat purrs.

Please implement methods in the [CatDataProcessor](src/main/java/com/epam/autotasks/CatDataProcessor.java) class.

- `public ImmutableTable<String, Cat.Breed, Integer> createCatTable(List<Cat> cats)` - returns an immutable table of the given _cats_ with non-empty name as a row key, breed as a column key and contest results as value.
- `public JSONArray createCatJson(List<Cat> cats)` - returns a JSON array of the given _cats_, mapped to JSON objects.

1. All cat fields can be null.
2. Names of cats are unique.

### Examples

```java
        List<Cat> cats = new ArrayList<>();
        cats.add(Cat.builder().name("Alex").age(1).breed(Cat.Breed.MAINE_COON).weight(5).awards(24).contestResult(new ContestResult(93, 59, 93).build());
        cats.add(Cat.builder().name("Chester").age(5).breed(Cat.Breed.SIBERIAN).weight(4).awards(15).contestResult(new ContestResult(35, 57, 67).build());
        cats.add(Cat.builder().name("Fluff").age(4).breed(Cat.Breed.PERSIAN).weight(6).awards(27).contestResult(new ContestResult(39, 77, 85).build());
        cats.add(Cat.builder().name("Kit").age(9).breed(Cat.Breed.MAINE_COON).weight(11).awards(9).contestResult(new ContestResult(88, 59, 49).build());
        cats.add(Cat.builder().name("Boo").age(3).breed(Cat.Breed.MAINE_COON).weight(6).awards(16).contestResult(new ContestResult(56, 89, 77).build());

        return createCatTable(cats);
// {"Alex"={MAINE_COON=245}, "Chester"={SIBERIAN=159}, "Fluff"={PERSIAN=201}, "Kit"={MAINE_COON=196), "Boo"={MAINE_COON=222}}
```

---

```java
        List<Cat> cats = new ArrayList<>();
        cats.add(Cat.builder().name("Alex").age(1).breed(Cat.Breed.MAINE_COON).weight(5).awards(24).contestResult(new ContestResult(93, 59, 93).build());
        cats.add(Cat.builder().name("Chester").age(5).breed(Cat.Breed.SIBERIAN).weight(4).awards(15).contestResult(new ContestResult(35, 57, 67).build());
        cats.add(Cat.builder().name("Fluff").age(4).breed(Cat.Breed.PERSIAN).weight(6).awards(27).contestResult(new ContestResult(39, 77, 85).build());

        return createCatJson(cats);
// [
// {
// "awards":24,
// "name":"Alex",
// "weight":5,
// "contestResult":{
// "running":93,
// "purring":59,
// "jumping":93,
// "sum":245
// },
// "age":1,
// "breed":"MAINE_COON"
// },
// {
// "awards":15,
// "name":"Chester",
// "weight":4,
// "contestResult":{
// "running":35,
// "purring":57,
// "jumping":67,
// "sum":159
// },
// "age":8,
// "breed":"SIBERIAN"
// },
// {
// "awards":27,
// "name":"Fluff",
// "weight":6,
// "contestResult":{
// "running":39,
// "purring":77,
// "jumping":85,
// "sum":201
// },
// "age":4,
// "breed":"PERSIAN"
// }
// ]
```
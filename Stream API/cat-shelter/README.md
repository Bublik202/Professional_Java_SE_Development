# Cat Shelter

The purpose of this exercise is to train you to work with the intermediate operations _flatMap_ and _foreach_.

Duration: _30 minutes_

### Description

Implement methods that help shelters take care of their cats.

Please implement methods in the [ShelterService](src/main/java/com/epam/autotasks/ShelterService.java) class.

- `public void assignAttendants(List<Room> rooms)` - assigns a Staff attendant to each cat in each room.
- `public List<Cat> getCheckUpList(List<ShelterRoom> rooms, LocalDate date)` - returns a list of cats, who had a
  check-up before _date_ and should have a new check-up soon.
- `public List<Cat> getCatsByBreed(List<ShelterRoom> rooms, Cat.Breed breed)` - returns a single list of cats,
  categorized by breed from each room.

1. All cat fields are nullable.
2. The methods should return an empty list when given an empty list.

<mark style="background-color: lightblue"><b>Optional task.</b><br/>
Of course, you can assign the same attendant to each cat, and it will not affect tests. But it would be significantly
more convenient for shelters if you implemented an allocating policy or a randomizer.</mark>

### Examples

```java
        List<ShelterRoom> rooms=new ArrayList<>();

        ShelterRoom firstRoom=new ShelterRoom(new ArrayList<>());
        firstRoom.getCats().add(Cat.builder().name("Alex").age(1).weight(12).height(35).breed(Cat.Breed.MAINE_COON).lastCheckUpDate(LocalDate.of(2021,5,5)).build());
        firstRoom.getCats().add(Cat.builder().name("Fluff").age(4).weight(7).height(20).breed(Cat.Breed.PERSIAN).lastCheckUpDate(LocalDate.of(2021,7,25)).build());

        ShelterRoom secondRoom=new ShelterRoom(new ArrayList<>());
        secondRoom.getCats().add(Cat.builder().name("Chester").age(5).weight(6).height(22).breed(Cat.Breed.SIBERIAN).lastCheckUpDate(LocalDate.of(2022,1,17)).build());
        secondRoom.getCats().add(Cat.builder().name("Kit").age(9).weight(9).height(27).breed(Cat.Breed.MAINE_COON).lastCheckUpDate(LocalDate.of(2020,12,24)).build());

        assignAttendants(rooms);
//{
// ShelterRoom(
// {
//  Cat("Alex", 1, 12, 35, MAINE_COON, NANCY, 2021-05-05),
//  Cat("Fluff", 4, 7, 20, PERSIAN, JOHN, 2021-07-25)
// }),
// ShelterRoom(
// {
//  Cat("Chester", 5, 6, 22, SIBERIAN, CATHERINE, 2022-01-17)
//  Cat("Kit", 9, 9, 27, MAINE_COON, EDWARD, 2020-12-24),
// })
// }
```

---

```java
        List<ShelterRoom> rooms=new ArrayList<>();

        ShelterRoom firstRoom=new ShelterRoom(new ArrayList<>());
        firstRoom.getCats().add(Cat.builder().name("Alex").age(1).weight(12).height(35).breed(Cat.Breed.MAINE_COON).lastCheckUpDate(LocalDate.of(2021,5,5)).build());
        firstRoom.getCats().add(Cat.builder().name("Fluff").age(4).weight(7).height(20).breed(Cat.Breed.PERSIAN).lastCheckUpDate(LocalDate.of(2021,7,25)).build());

        ShelterRoom secondRoom=new ShelterRoom(new ArrayList<>());
        secondRoom.getCats().add(Cat.builder().name("Chester").age(5).weight(6).height(22).breed(Cat.Breed.SIBERIAN).lastCheckUpDate(LocalDate.of(2022,1,17)).build());
        secondRoom.getCats().add(Cat.builder().name("Kit").age(9).weight(9).height(27).breed(Cat.Breed.MAINE_COON).lastCheckUpDate(LocalDate.of(2020,12,24)).build());

        return getCheckUpList(rooms,LocalDate.of(2021,7,31));
//{
// Cat("Alex", 1, 12, 35, MAINE_COON, 2021-05-05),
// Cat("Fluff", 4, 7, 20, PERSIAN, 2021-07-25),
// Cat("Kit", 9, 9, 27, MAINE_COON, 2020-12-24),
// }
```

---

```java
        List<ShelterRoom> rooms=new ArrayList<>();

        ShelterRoom firstRoom=new ShelterRoom(new ArrayList<>());
        firstRoom.getCats().add(Cat.builder().name("Alex").age(1).weight(12).height(35).breed(Cat.Breed.MAINE_COON).lastCheckUpDate(LocalDate.of(2021,5,5)).build());
        firstRoom.getCats().add(Cat.builder().name("Fluff").age(4).weight(7).height(20).breed(Cat.Breed.PERSIAN).lastCheckUpDate(LocalDate.of(2021,7,25)).build());

        ShelterRoom secondRoom=new ShelterRoom(new ArrayList<>());
        secondRoom.getCats().add(Cat.builder().name("Chester").age(5).weight(6).height(22).breed(Cat.Breed.SIBERIAN).lastCheckUpDate(LocalDate.of(2022,1,17)).build());
        secondRoom.getCats().add(Cat.builder().name("Kit").age(9).weight(9).height(27).breed(Cat.Breed.MAINE_COON).lastCheckUpDate(LocalDate.of(2020,12,24)).build());

        return getCatsByBreed(rooms,Cat.Breed.MAINE_COON);
//{
// Cat("Alex", 1, 12, 35, MAINE_COON, 2021-05-05),
// Cat("Kit", 9, 9, 27, MAINE_COON, 2020-12-24),
// }
```
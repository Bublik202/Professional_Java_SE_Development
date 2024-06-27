# Contacts Comparators

The goal of this task is to practice combining multiple comparators
and use some of their utility methods to build complex comparators easily.

Duration: _1 hour_

## Description

Let's say you have some application for storing contacts-information about people, addresses, and companies.
You can design the corresponding class for each one of them.
These classes are not that interesting because they're practically just a bunch of data with accessors (getter/setter).
We use **Java Records Style** for accessors (or **C#** style if you like),
just for a more fluent API and to reduce unnecessary code.
Also, you should notice that these classes refer to each other;
for example, the class `Person` has a field of the type `Address`.

Now, take a look at the `Comparators` class. This is where you'll write your code.
Your goal is to implement all the constants in this interface.

It is best not to implement each one manually but use `Comparator` utility methods.
Please be aware that the objects being compared might contain `null` or be `null` themselves.

## Requirements

The order of the requirements matters. They have the following format:
> field, order, null order/error

Here are some examples:

1. `name, ASC, last` are compared by field `name` in ascending order,
   and `null` values must be placed after all non-`null` values.
3. `age, DESC, first` are compared by field `date` in descending order,
   and `null` values must be placed before all non-`null` values.
4. `id, ASC, error` are compared by field `id` in ascending order,
   and `null` values are unacceptable (must throw an error).

In this example, first, we compare by `name`. Then, if the result is **0**, we compare by `age`;
if the result is **0**, we compare by `id`. If `id` is `null`, then `NullPointerException` must be thrown.

Only `NullPointerException` must be thrown.

If the comparator allows `null` values in general, then all these operations are valid:
```java
comparator.compare(obj, null);
comparator.compare(null, obj);
comparator.compare(null, null);
```

### ZIP_CODE_COMPARATOR

allows `null` addresses, and `null` values must be placed before non-`null` ones.

1. `zipCode`, DESC, error

### STREET_COMPARATOR

allows `null` addresses, and `null` values must be placed before non-`null` ones.

1. `street`, ASC, last
2. `building`, ASC, last
3. `apartment`, ASC, last

### REGION_COMPARATOR

allows `null` addresses, and `null` values must be placed before non-`null` ones.

1. `country`, ASC, last
2. `city`, ASC, last
3. `zipCode`, DESC, error

### ADDRESS_COMPARATOR

allows `null` addresses, and `null` values must be placed after non-`null` ones.

1. `country`, ASC, last
2. `city`, ASC, last
3. `zipCode`, DESC, error
4. `street`, ASC, last
5. `building`, ASC, last
6. `apartment`, ASC, last

### FULL_NAME_COMPARATOR

allows `null` addresses, and `null` values must be placed before non-`null` ones.

1. `name`, ASC, error
2. `surname`, ASC, error

### BIRTHDATE_COMPARATOR

allows `null` addresses, and `null` values must be placed before non-`null` ones.

1. `birthdate`, DESC, last

### PERSON_COMPARATOR

Allows `null` addresses, `null` values must be placed after non-`null` ones.

1. `name`, ASC, error
2. `surname`, ASC, error
3. `birthdate`, DESC, last
4. `address.country`, ASC, last
5. `address.city`, ASC, last
6. `address.zipCode`, DESC, error
7. `address.street`, ASC, last
8. `address.building`, ASC, last
9. `address.apartment`, ASC, last

### REGISTRATION_ID_COMPARATOR

allows `null` addresses, and `null` values must be placed after non-`null` ones.

1. `registrationUuid`, ASC, error

### COMPANY_COMPARATOR

allows `null` addresses, and `null` values must be placed after non-`null` ones.

1. `name`, ASC, last
2. `registrationUuid`, ASC, error
3. `director.name`, ASC, error
4. `director.surname`, ASC, error
5. `director.birthdate`, DESC, last
6. `director.address.country`, ASC, last
7. `director.address.city`, ASC, last
8. `director.address.zipCode`, DESC, error
9. `director.address.street`, ASC, last
10. `director.address.building`, ASC, last
11. `director.address.apartment`, ASC, last
12. `officeAddress.country`, ASC, last
13. `officeAddress.city`, ASC, last
14. `officeAddress.zipCode`, DESC, error
15. `officeAddress.street`, ASC, last
16. `officeAddress.building`, ASC, last
17. `officeAddress.apartment`, ASC, last

## Examples

```java
int cmp = Comparators.ZIP_CODE_COMPARATOR.compare(
  new Address().zipCode(15000),
  new Address().zipCode(11000)
);
assert cmp == 1;
```

```java
int cmp = Comparators.FULL_NAME_COMPARATOR.compare(
  new Person().name("Daniel").surname("D'Arby"),
  new Person().name("Rudol").surname("Stroheim")
);
assert cmp == -1
```

```java
int cmp = Comparators.BIRTHDATE_COMPARATOR.compare(
  new Person().birthdate(LocalDate.of(1867, 4, 10)),
  new Person().birthdate(LocalDate.of(1867, 4, 10))
);
assert cmp == 0
```

```java
int cmp = Comparators.ZIP_CODE_COMPARATOR.compare(
  new Address().zipCode(null),
  new Address().zipCode(11000)
);
// throws NullPointerException
```

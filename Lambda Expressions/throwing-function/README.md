# Functional Interface: Throwing Function Task

The purpose of this task is to train you to declare your functional interface,
practice a little more with generics, and see an example of why you might want to
declare your interface when we have plenty of predefined ones.

Duration: _15 minutes_

## Description

In this task, you'll implement the so-called `ThrowingFunction`.
It's a variant of a `java.util.function.Function`, except for it may throw
an exception. It might be really helpful when you want to pass a function
to some method, but the underlying code throws some checked exception
(it happens pretty often, when you work with Java IO, for example).

But what exception it might throw? `IOException`, or `ClassNotFoundException`,
or just `Exception`, or even `Throwable`? Well, let's try to use generics here.
We want to declare a function that would throw an exception, which we can specify.

There is also a pretty common practice to define "companion" methods for such
functions. Usually, they called something like `silent`, `wrap`, `quiet`, etc.
The main idea of such companions is to wrap the throwing function and return
the non-throwing function. It's useful when you need to pass your throwing function
to some API that accepts only non-throwing ones (e.g. Stream API).

## Requirements

Let's go through the requirements of this task:
* `ThrowingFunction` interface must be declared under `edu.epam.fop.lambdas` package
* It must be a generic type with generalized:
    * Argument type
    * Return type
    * Exception type
* It must have an abstract `apply` method, which accepts one argument,
  of one generic type and returns an instance of another generic type
* `apply` method must be able to throw any exception up to `Throwable`
* It must have a static method for wrapping `ThrowingFunction` into `Function`,
  named `quiet`
* `quiet` method must be generic too
* `quiet` method must return `null` when `null` is passed
* `quiet` method must not throw any exception
* The returned by `quiet` method `Function` must wrap any occurred
  in `ThrowingFunction` exception into `RuntimeException` without hiding the cause
* The returned by `quiet` method `Function` must work
  just as `ThrowingFunction`, when no error has occurred
* generic types of `ThrowingFunction` and `Function` must correspond to each other
* Generic types must not have any boundaries if possible
* `ThrowingFunction` must be annotated with `@FunctionalInterface`

## Examples

### Variable declaration
```java
ThrowingFunction<String, Integer, IOException> ioFunc = new ThrowingFunction<>() {
  @Override
  Integer apply(String fileName) throws IOException {
    return IOUtils.getSize(fileName);
  }
}
```

### Function calling
```java
try {
  return ioFunc.apply("big-file.csv");
}
catch (IOException e) {
  e.printStackTrace();
}
```

### Silencing function
```java
Function<String, Integer> func = ThrowingFunction.quiet(ioFunc);
func.apply("big-file.csv");
```

# Tri-Function Task

The goal of this task is to practice in declaring custom functional interfaces.

Duration: _15 minutes_

## Description

For this task, you will create a functional interface named `TriFunction`
under `edu.epam.fop.lambdas` package. It will be sort of an extended version
of the standard Java interfaces: `Function` and `BiFunction`. However, it must
accept not two but three generalized arguments and return a generalized object.
In case some terms are not clear:
* `Function` accepts one generalized argument and returns a generalized object.
* `BiFunction` accepts two generalized arguments and returns a generalized object.

## Requirements

* The interface is named `TriFunction`.
* It has three generalized arguments.
* It returns a generalized object.
* All generalizations are declared on the type level.
* Its method is named `apply`.
* The interface is annotated with `@FunctionalInterface`.
* It is placed under the `edu.epam.fop.lambdas` package.

## Examples

If your interface is declared correctly, it must be transformable
into a returning lambda with three arguments:
```java
TriFunction fun = (a, b, c) -> null;
```

As in the example above, the code must be compilable, or your interface
is incorrect.

Also, it must support generalization, so the following example should not be compiled:

```java
TriFunction<Object, Object, Object, Integer> fun = (a, b, c) -> "Hello World";
```

We assume that the last parameter type is for the returning type.
Here we have `Integer` as the returning type, but `String` is returning,
so this example should fail.

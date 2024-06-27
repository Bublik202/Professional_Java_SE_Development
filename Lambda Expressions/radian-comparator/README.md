# Radian Comparator

The purpose of this task is to practice writing custom comparators.

Duration: _30 min_

## Description

In this task, your goal is to implement `RadianComparator`, which simply compares two double values.
The catch here is that you will be comparing not just some values but radians, a unit of angular measure.
Also, periods should be ignored. The period is equal to **2π**, so using the provided definitions,
angles of **a** and **a + 2πn** radians are equal. Ultimately, the angles are compared in the range **[0..2π)**,
and all other values must be mapped on that range by subtracting or adding the required number of periods.

Here are some examples of equal angles:
```
a = a + 2π
a = a - 2π
a = a + 16π
a = a - 16π
0 = 2π
0 = -2π
0 = 4π
```

No concrete number is provided to avoid misunderstandings caused by the precision of these values.

All `null` values must be less than non-`null` values. Two `null` values must be equal.

Since this comparator will be used to compare `double` values, keep in a mind the precision of this comparator.
Because high-precision calculations are outside the scope of this task,
it doesn't need to be set high, so **0.001** will be used as an acceptable difference.
This means that if value **a** differs from value **b** on a value less than **0.001**, they are considered equal.

## Requirements

Let's formalize our requirements for this comparator.

1. It must follow the `Comparator` contract.
2. It must compare values in the range **[0..2π)**.
3. Periods must be ignored (values outside the specified range must be aligned with it).
4. The precision is **0.001**.
5. `null` value is always less than a non-`null` value.
6. Two `null` values are equal to each other.

## Examples

```
Comparator<Double> cmp = RadianComparator.get();
cmp.compare(0, 0);                 //   0
cmp.compare(0, 0.0005);            //   0
cmp.compare(0, 2 * Math.PI);       //   0
cmp.compare(0, Math.PI);           // < 0
cmp.compare(Math.PI, 0);           // > 0
cmp.compare(Math.PI, 3 * Math.PI); //   0
cmp.compare(Math.PI, 4 * Math.PI); // > 0
```

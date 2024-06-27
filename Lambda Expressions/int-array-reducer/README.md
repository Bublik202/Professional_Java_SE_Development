# IntArrayReducer Task

The goal of this task is to practice implementing different lambdas with the same functional interface
and see how one functional interface can generalize multiple scenarios.

Duration: _1 hour_

## Description

In this task, you already have a functional interface - `IntArrayReducer`. It has the method `reduce`, which accepts
array of `int`s and returns just one `int` value. Makes sense, right?

Your goal is to implement different functionalities based on this interface. All of them must be placed in
`IntArrayReducers` constants such as `MIN_FINDER`, `SUMMARIZER`, etc.

All you have to do is initialize each of these constants
with the correct lambda expression.

## Requirements

First, let's make some assumptions:
1. None of these constants will get `null` as a parameter.
2. None of these constants will get an empty array as a parameter.
3. All calculations will be in the defined range of the `int` type.

With all these assumptions, you don't have to worry about integer overflow, null awareness, or anything else.
You can just focus on implementation without doing any additional checks.

Now, let's go through each constant and define the requirements on them:

1. `SUMMARIZER`
   * Returns the sum of the array values `(a[0] + a[1] + ...)`
2. `MULTIPLIER`
   * Returns the product of the array values `(a[0] * a[1] * ...)`
3. `MIN_FINDER`
   * Returns the min value of the array
4. `MAX_FINDER`
   * Returns the max value of the array
5. `AVERAGE_CALCULATOR`
   * Returns the average value of the array
   * The average must be rounded mathematically
6. `UNIQUE_COUNTER`
   * Counts all unique values of the array
7. `SORT_DIRECTION_DEFINER`
   * Returns **1** if the array is sorted in ascending order
   * Returns **-1** if the array is sorted in descending order
   * Returns **0** if the array is not sorted or all its elements are equal

## Examples

1. `SUMMARIZER`
   1. `[1, 2, 3, 4]` => **10**
2. `MULTIPLIER`
   1. `[1, 2, 3, 4]` => **24**
3. `MIN_FINDER`
   1. `[1, 2, 3, 4]` => **1**
4. `MAX_FINDER`
    1. `[1, 2, 3, 4]` => **4**
5. `AVERAGE_CALCULATOR`
     1. `[1, 2, 3]` => **2**
     2. `[1, 2]` => **2** (**1.5**)
     3. `[1, 1, 3]` => **1** (**~1.333**)
6. `UNIQUE_COUNTER`
   1. `[1, 2, 3]` => **3**
   2. `[1, 2, 2, 3, 3, 3]` => **3**
   3. `[1, 1, 1, 1]` => **1**
7. `SORT_DIRECTION_DEFINER`
   1. `[1, 2, 3]` => **1**
   1. `[3, 2, 1]` => **-1**
   1. `[1, 1, 1]` => **0**
   1. `[1, 2, 1]` => **0**

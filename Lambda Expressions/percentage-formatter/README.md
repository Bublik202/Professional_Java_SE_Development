# Percentage Formatter Task

The purpose of this task is to practice writing simple and concise Java lambda expressions.

Duration: _30 minutes_

## Description

Your task is to provide a Java lambda expression that implements `DoubleFunction<String>`.

As the name of the functional interface indicates it accepts a `double` value,
which we want to format to the corresponding `String` value in the format:
```
[-]\d+[.\d] %
```
Where `[...]` means optional, `\d` means digit, and `+` means at least one symbol.
Please note, the space between the number and percent sign. This lambda must never return `null`.

The passed value can be anything, even more than **1** or less than **0**.
The value **1** we consider **100 %**, so **-0.5** would be **-50 %**.

## Requirements

* Convert the `double` value to the corresponding `String` value, representing the double value as a percent string.
* Never return `null`.
* A `-` sign before the number in a string of negative values.
* A space between the resulting number and the percent sign.
* A maximum of one digit in the fraction part.
* You must round the fraction part mathematically, i.e. anything less than **0.5** is rounded down (floor),
and anything greater than or equal to **0.5** is rounded up (ceil). For example:
  * **1.4** => **1**
  * **2.45** => **2**
  * **3.5** => **4**
  * **17.7000001** => **18**
* (Optional) Make the lambda *one line* (i.e., without using curly brackets)

## Examples

The lambda can be tested using the following call, which returns a `String` value for testing:
```java
PercentageFormatter.INSTANCE.apply(<double value>);
```

And here are some expected input/output pairs:
* **0.42**    => `42 %`
* **0.427**   => `42.7 %`
* **0.4273**  => `42.7 %`
* **0.4275**  => `42.8 %`
* **-0.4275** => `-42.8 %`

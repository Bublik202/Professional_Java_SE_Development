# Insurance calculator

The purpose of this task is to practice with Optional, and its main methods - `map`, `filter`, `flatMap`, etc.

Duration: _3 hours_

## Description

In this task, you are provided with some classes that are used for insurance calculations.
In the `insurance` package you have:
1. `Subject` - the main sealed interface, which must be implemented by insurance subjects.
2. `Car`, `Accommodation`, `Person` - the subject's insurance is calculated for (which implement the `Subject` interface).
3. `Currency` - just an `enum` of the currencies provided.
4. `RepeatablePayment` - represent, for example, rent, salary, or a loan.
5. `Injury`, `Family` - not subject classes; used to describe a certain part of the subject classes.

These classes a mostly plain data objects without any logic. The only things to highlight are:
1. Some getters return `Optional` instead of the target type.
2. Some of these classes implement `Comparable` interface to make it easier to retrieve the required instance
   from `SortedSet`.

Also, you have `calculator` package. Here you have two main classes:
1. `InsuranceCoefficient` - a wrapper for `int` value in range **[0; 100]**,
   which represents the percentage of trustworthy clients.
2. `InsuranceCalculator<S extends Subject>` - a functional interface,
   which determines `InsuranceCoefficient` based on the passed `S` instance.

Also, here you can find several `*InsurancePolicies` classes, which provide methods that must be implemented.
Take a closer look at them and define the requirements.

## Requirements

No implementations must throw an exception, and all of them must be `null`-safe. Objects can be `null` or return `null`.
Getters that return `Optional` never return `null`.

All arguments cannot be `null`.

### AccommodationInsurancePolicies

#### rentDependentInsurance

Accepts `BigInteger divider` which is used to adjust the insurance coefficient.

It must return an insurance coefficient when an accommodation is rented; monthly payment should be in **USD**.
As a result, for the insurance coefficient, you must divide the rent amount by the divider provided.
If the resulting insurance coefficient is more than **100**, then `InsuranceCoefficient.MAX` is returned.
If a condition is not met, then return empty `Optional`.

1. The accommodation must include `rent`.
2. The rent period must be in months.
3. The rent currency must be **USD**.
4. The rent amount must be greater than **0**.
5. The coefficient is equal to rent divided by the divider provided in percent (`rent / divider * 100%`).
6. If the relation is more than **100%**, **100%** should be returned.
7. The default value is `Optional.empty`

#### priceAndRoomsAndAreaDependentInsurance

Accepts `BigInteger priceThreshold`, which must be met or exceeded by the accommodation price,
`int roomsThreshold`, which must be met or exceeded by the room counts,
and `areaThreshold` are the same but for the accommodation area.

Accommodations price, room count, and the area must be greater or equal to their corresponding thresholds.
If all the conditions are met, `InsuranceCoefficient.MAX` must be returned;
if not `InsuranceCoefficient.MIN` must be returned.

1. Accommodation price >= `priceThreshold`
2. Accommodation rooms >= `roomsThreshold`
3. Accommodation area >= `areaThreshold`
4. Return `InsuranceCoefficient.MAX` if all conditions are met
5. Return `InsuranceCoefficient.MIN` if one or more conditions are not met

### CarInsurancePolicies

#### ageDependInsurance

Accepts `LocalDate baseDate`, which is used as a pivot point for calculations. All calculations must use it.
Date-math precision is **1 day**.

The calculator must return:
1. `InsuranceCoefficient.MAX` if the manufactured date is less than **1 year** from `baseDate`
2. A coefficient of **70** if less than **5 years**
3. A coefficient of **30** if less than **10 years**
4. `InsuranceCoefficient.MIN` if a car is older than **10 years**.
5. If the manufactured date is unknown, `Optional.empty` must be returned

#### priceAndOwningOfFreshCarInsurance

Accepts `LocalDate baseDate` as a base for the date-math calculations,
`BigInteger priceThreshold` which must be met or exceeded by car price,
and `Period owningThreshold` - the minimum period of ownership.

The idea is to calculate the insurance coefficient only for cars which are still owned by a person (`soldDate` is not set),
price is greater or equal to `priceThreshold`, the period from `purchaseDate` to `baseDate`
is greater or equal to `owningThreshold`.

The resulting coefficient must be based on car price and `priceThreshold`.
If the price of the car is greater than or equal to three times the `priceThreshold`,
`InsuranceCoefficient.MAX` must be returned. If it exceeds two times but is less than three times greater,
a coefficient of **50** must be returned. `InsuranceCoefficient.MIN` must be returned
if the price is less than or equal to twice the threshold.
If any of the conditions are not met, `Optional.empty` must be returned.

1. `soldDate` must not be set.
2. `price` must be >= than `priceThreshold`.
3. `purchaseDate` + `owningThreshold` must be >= `baseDate`.
4. `InsuranceCoefficient.MAX` must be returned if `price` >= **3** * `priceThreshold`.
5. A coefficient of **50** must be returned if **3** * `priceThreshold` > `price` >= **2** * `priceThreshold`.
6. `InsuranceCoefficient.MIN` must be returned if `price` < **2** * `priceThreshold`.
7. `Optional.empty` must be returned if any of the conditions are not met.

### PersonInsurancePolicies

#### childrenDependent

Accepts `int childrenCountThreshold`. The coefficient must be calculated based on the number of children
a person has relative to the threshold provided.

1. A person must have a `family`.
2. A person must have `children`.
3. The resulting coefficient equals to `max(100, childrenCount / threshold)`.
4. `InsuranceCoefficient.MIN` must return if any of the conditions are not met.

#### employmentDependentInsurance

Accepts `BigInteger salaryThreshold` and `Set<Currency> currencies`,
which together define the salary threshold based on the amount and the currency allowed.

1. Person must have an employment history of at least four records.
2. They must have multi-currency account.
3. They must not have any recorded injuries.
4. They must have at least one accommodation (either owned or rented).
5. They must be currently employed (i.e., the last employment record does not have `endDate`).
6. `salary` for this job must be in one of the `currencies` provided.
7. `salary.amount` must be greater than or equal to `salaryThreshold`.
8. If all conditions are met, a coefficient of 50 must be returned.
9. If any of the conditions are not met, `Optional.empty` must be returned.

#### accommodationEmergencyInsurance

Accepts `Set<EmergencyStatus> statuses`, which defines the allowed emergency statuses
of an accommodation owned or rented by a person.

1. A person must have an accommodation.
2. The calculator must pick the accommodation that is the smallest in area.
3. Check to make sure its `emergencyStatus` is listed in the `statuses` parameter.
4. The coefficient is calculated as `100 * (1 - status.ordinal() / totalStatuses)`.
5. If any of the conditions are not met, `Optional.empty` must be returned.

#### injuryAndRentDependentInsurance

Accepts `BigInteger rentThreshold`.

1. A person must have an injury.
2. The injury must have been caused by someone else.
3. The largest accommodation (by area) must be rented.
4. The rent must be in **GBP**.
5. The coefficient is calculated as `max(100, 100 * rent / threshold)`.
6. If any of the conditions are not met, `Optional.empty` must be returned.

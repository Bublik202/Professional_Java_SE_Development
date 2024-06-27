package edu.epam.fop.lambdas.insurance;

import java.math.BigInteger;
import java.time.Period;

public record RepeatablePayment(Period unit, Currency currency, BigInteger amount) {

}

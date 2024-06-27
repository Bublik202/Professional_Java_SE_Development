package edu.epam.fop.lambdas.calculator;

import edu.epam.fop.lambdas.insurance.Subject;
import java.util.Optional;

@FunctionalInterface
public interface InsuranceCalculator<S extends Subject> {

  Optional<InsuranceCoefficient> calculate(S entity);
}

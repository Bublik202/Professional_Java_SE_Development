package edu.epam.fop.lambdas.kamino;

import edu.epam.fop.lambdas.kamino.BatchPolicies.BatchPolicy;

public final class KaminoFactory {

  public static CloneTrooper[] formBatch(BatchPolicy policy, CloneTrooper base, int count) {
    return policy.formBatchOf(base, count);
  }

  public static CloneTrooper growClone(String code) {
    return new CloneTrooper(code);
  }
}

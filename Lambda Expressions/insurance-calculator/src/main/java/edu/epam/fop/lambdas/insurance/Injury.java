package edu.epam.fop.lambdas.insurance;

import java.math.BigInteger;
import java.time.LocalDate;
import java.util.Comparator;
import java.util.Optional;

public class Injury implements Comparable<Injury> {

  private static final Comparator<Injury> CMP = Comparator.comparing(Injury::date);

  private String name;
  private BigInteger curingCost;
  private Person culprit;
  private String reason;
  private LocalDate date;

  public Injury() {
  }

  public Optional<Person> culprit() {
    return Optional.ofNullable(culprit);
  }

  @Override
  public int compareTo(Injury o) {
    return CMP.compare(this, o);
  }


  public String name() {
    return this.name;
  }

  public BigInteger curingCost() {
    return this.curingCost;
  }

  public String reason() {
    return this.reason;
  }

  public LocalDate date() {
    return this.date;
  }

  public Injury name(String name) {
    this.name = name;
    return this;
  }

  public Injury curingCost(BigInteger curingCost) {
    this.curingCost = curingCost;
    return this;
  }

  public Injury culprit(Person culprit) {
    this.culprit = culprit;
    return this;
  }

  public Injury reason(String reason) {
    this.reason = reason;
    return this;
  }

  public Injury date(LocalDate date) {
    this.date = date;
    return this;
  }

  public boolean equals(final Object o) {
    if (o == this) {
      return true;
    }
    if (!(o instanceof Injury)) {
      return false;
    }
    final Injury other = (Injury) o;
    if (!other.canEqual((Object) this)) {
      return false;
    }
    final Object this$name = this.name;
    final Object other$name = other.name;
    if (this$name == null ? other$name != null : !this$name.equals(other$name)) {
      return false;
    }
    final Object this$curingCost = this.curingCost;
    final Object other$curingCost = other.curingCost;
    if (this$curingCost == null ? other$curingCost != null : !this$curingCost.equals(other$curingCost)) {
      return false;
    }
    final Object this$culprit = this.culprit;
    final Object other$culprit = other.culprit;
    if (this$culprit == null ? other$culprit != null : !this$culprit.equals(other$culprit)) {
      return false;
    }
    final Object this$reason = this.reason;
    final Object other$reason = other.reason;
    if (this$reason == null ? other$reason != null : !this$reason.equals(other$reason)) {
      return false;
    }
    final Object this$date = this.date;
    final Object other$date = other.date;
    if (this$date == null ? other$date != null : !this$date.equals(other$date)) {
      return false;
    }
    return true;
  }

  protected boolean canEqual(final Object other) {
    return other instanceof Injury;
  }

  public int hashCode() {
    final int PRIME = 59;
    int result = 1;
    final Object $name = this.name;
    result = result * PRIME + ($name == null ? 43 : $name.hashCode());
    final Object $curingCost = this.curingCost;
    result = result * PRIME + ($curingCost == null ? 43 : $curingCost.hashCode());
    final Object $culprit = this.culprit;
    result = result * PRIME + ($culprit == null ? 43 : $culprit.hashCode());
    final Object $reason = this.reason;
    result = result * PRIME + ($reason == null ? 43 : $reason.hashCode());
    final Object $date = this.date;
    result = result * PRIME + ($date == null ? 43 : $date.hashCode());
    return result;
  }

  public String toString() {
    return "Injury(name=" + this.name + ", curingCost=" + this.curingCost + ", culprit="
        + Optional.ofNullable(this.culprit).map(p -> p.name() + " " + p.surname()) + ", reason="
        + this.reason + ", date=" + this.date + ")";
  }
}

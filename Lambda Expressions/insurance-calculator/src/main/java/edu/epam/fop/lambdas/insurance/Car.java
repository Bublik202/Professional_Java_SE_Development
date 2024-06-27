package edu.epam.fop.lambdas.insurance;

import java.math.BigInteger;
import java.time.LocalDate;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;

public final class Car implements Subject {

  private String model;
  private BigInteger price;
  private LocalDate manufactureDate;
  private LocalDate purchaseDate;
  private LocalDate soldDate;

  public Car() {
  }

  public Optional<LocalDate> soldDate() {
    return Optional.ofNullable(soldDate);
  }

  public String model() {
    return this.model;
  }

  public BigInteger price() {
    return this.price;
  }

  public LocalDate manufactureDate() {
    return this.manufactureDate;
  }

  public LocalDate purchaseDate() {
    return this.purchaseDate;
  }

  public Car model(String model) {
    this.model = model;
    return this;
  }

  public Car price(BigInteger price) {
    this.price = price;
    return this;
  }

  public Car manufactureDate(LocalDate manufactureDate) {
    this.manufactureDate = manufactureDate;
    return this;
  }

  public Car purchaseDate(LocalDate purchaseDate) {
    this.purchaseDate = purchaseDate;
    return this;
  }

  public Car soldDate(LocalDate soldDate) {
    this.soldDate = soldDate;
    return this;
  }

  public boolean equals(final Object o) {
    if (o == this) {
      return true;
    }
    if (!(o instanceof Car)) {
      return false;
    }
    final Car other = (Car) o;
    final Object this$model = this.model;
    final Object other$model = other.model;
    if (this$model == null ? other$model != null : !this$model.equals(other$model)) {
      return false;
    }
    final Object this$price = this.price;
    final Object other$price = other.price;
    if (this$price == null ? other$price != null : !this$price.equals(other$price)) {
      return false;
    }
    final Object this$manufactureDate = this.manufactureDate;
    final Object other$manufactureDate = other.manufactureDate;
    if (this$manufactureDate == null ? other$manufactureDate != null
        : !this$manufactureDate.equals(other$manufactureDate)) {
      return false;
    }
    final Object this$purchaseDate = this.purchaseDate;
    final Object other$purchaseDate = other.purchaseDate;
    if (this$purchaseDate == null ? other$purchaseDate != null : !this$purchaseDate.equals(other$purchaseDate)) {
      return false;
    }
    final Object this$soldDate = this.soldDate;
    final Object other$soldDate = other.soldDate;
    if (this$soldDate == null ? other$soldDate != null : !this$soldDate.equals(other$soldDate)) {
      return false;
    }
    return true;
  }

  public int hashCode() {
    final int PRIME = 59;
    int result = 1;
    final Object $model = this.model;
    result = result * PRIME + ($model == null ? 43 : $model.hashCode());
    final Object $price = this.price;
    result = result * PRIME + ($price == null ? 43 : $price.hashCode());
    final Object $manufactureDate = this.manufactureDate;
    result = result * PRIME + ($manufactureDate == null ? 43 : $manufactureDate.hashCode());
    final Object $purchaseDate = this.purchaseDate;
    result = result * PRIME + ($purchaseDate == null ? 43 : $purchaseDate.hashCode());
    final Object $soldDate = this.soldDate;
    result = result * PRIME + ($soldDate == null ? 43 : $soldDate.hashCode());
    return result;
  }

  public String toString() {
    return "Car(model=" + this.model + ", price=" + this.price + ", manufactureDate=" + this.manufactureDate
        + ", purchaseDate=" + this.purchaseDate + ", soldDate=" + this.soldDate + ")";
  }

  public static class Incident implements Comparable<Incident> {

    private static final Comparator<Incident> CMP = Comparator.comparing(Incident::date);

    private LocalDate date;
    private BigInteger ownDamageCost;
    private List<BigInteger> othersDamageCosts;
    private Person culprit;

    public Incident() {
    }

    public Optional<Person> culprit() {
      return Optional.ofNullable(culprit);
    }

    @Override
    public int compareTo(Incident o) {
      return CMP.compare(this, o);
    }

    public LocalDate date() {
      return this.date;
    }

    public BigInteger ownDamageCost() {
      return this.ownDamageCost;
    }

    public List<BigInteger> othersDamageCosts() {
      return this.othersDamageCosts;
    }

    public Incident date(LocalDate date) {
      this.date = date;
      return this;
    }

    public Incident ownDamageCost(BigInteger ownDamageCost) {
      this.ownDamageCost = ownDamageCost;
      return this;
    }

    public Incident othersDamageCosts(List<BigInteger> othersDamageCosts) {
      this.othersDamageCosts = othersDamageCosts;
      return this;
    }

    public Incident culprit(Person culprit) {
      this.culprit = culprit;
      return this;
    }

    public boolean equals(final Object o) {
      if (o == this) {
        return true;
      }
      if (!(o instanceof Incident)) {
        return false;
      }
      final Incident other = (Incident) o;
      if (!other.canEqual((Object) this)) {
        return false;
      }
      final Object this$date = this.date;
      final Object other$date = other.date;
      if (this$date == null ? other$date != null : !this$date.equals(other$date)) {
        return false;
      }
      final Object this$ownDamageCost = this.ownDamageCost;
      final Object other$ownDamageCost = other.ownDamageCost;
      if (this$ownDamageCost == null ? other$ownDamageCost != null : !this$ownDamageCost.equals(other$ownDamageCost)) {
        return false;
      }
      final Object this$othersDamageCosts = this.othersDamageCosts;
      final Object other$othersDamageCosts = other.othersDamageCosts;
      if (this$othersDamageCosts == null ? other$othersDamageCosts != null
          : !this$othersDamageCosts.equals(other$othersDamageCosts)) {
        return false;
      }
      final Object this$culprit = this.culprit;
      final Object other$culprit = other.culprit;
      if (this$culprit == null ? other$culprit != null : !this$culprit.equals(other$culprit)) {
        return false;
      }
      return true;
    }

    protected boolean canEqual(final Object other) {
      return other instanceof Incident;
    }

    public int hashCode() {
      final int PRIME = 59;
      int result = 1;
      final Object $date = this.date;
      result = result * PRIME + ($date == null ? 43 : $date.hashCode());
      final Object $ownDamageCost = this.ownDamageCost;
      result = result * PRIME + ($ownDamageCost == null ? 43 : $ownDamageCost.hashCode());
      final Object $othersDamageCosts = this.othersDamageCosts;
      result = result * PRIME + ($othersDamageCosts == null ? 43 : $othersDamageCosts.hashCode());
      final Object $culprit = this.culprit;
      result = result * PRIME + ($culprit == null ? 43 : $culprit.hashCode());
      return result;
    }

    public String toString() {
      return "Car.Incident(date=" + this.date + ", ownDamageCost=" + this.ownDamageCost + ", othersDamageCosts="
          + this.othersDamageCosts + ", culprit=" + this.culprit + ")";
    }
  }
}

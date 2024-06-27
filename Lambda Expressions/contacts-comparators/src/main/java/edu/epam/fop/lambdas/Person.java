package edu.epam.fop.lambdas;

import java.time.LocalDate;
import java.util.Objects;

public final class Person {

  private String name;
  private String surname;
  private LocalDate birthdate;
  private Address address;

  public String name() {
    return name;
  }

  public Person name(String name) {
    this.name = name;
    return this;
  }

  public String surname() {
    return surname;
  }

  public Person surname(String surname) {
    this.surname = surname;
    return this;
  }

  public LocalDate birthdate() {
    return birthdate;
  }

  public Person birthdate(LocalDate birthdate) {
    this.birthdate = birthdate;
    return this;
  }

  public Address address() {
    return address;
  }

  public Person address(Address address) {
    this.address = address;
    return this;
  }

  @Override
  public boolean equals(Object obj) {
    if (obj == this)
      return true;
    if (obj == null || obj.getClass() != this.getClass())
      return false;
    var that = (Person) obj;
    return Objects.equals(this.name, that.name) &&
        Objects.equals(this.surname, that.surname) &&
        Objects.equals(this.birthdate, that.birthdate) &&
        Objects.equals(this.address, that.address);
  }

  @Override
  public int hashCode() {
    return Objects.hash(name, surname, birthdate, address);
  }

  @Override
  public String toString() {
    return "Person[" +
        "name=" + name + ", " +
        "surname=" + surname + ", " +
        "birthdate=" + birthdate + ", " +
        "address=" + address + ']';
  }
}

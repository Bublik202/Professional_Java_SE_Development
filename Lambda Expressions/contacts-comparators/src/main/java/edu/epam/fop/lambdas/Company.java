package edu.epam.fop.lambdas;

import java.util.Objects;

public final class Company {

  private String name;
  private String registrationUuid;
  private Person director;
  private Address officeAddress;

  public String name() {
    return name;
  }

  public Company name(String name) {
    this.name = name;
    return this;
  }

  public String registrationUuid() {
    return registrationUuid;
  }

  public Company registrationUuid(String registrationUuid) {
    this.registrationUuid = registrationUuid;
    return this;
  }

  public Person director() {
    return director;
  }

  public Company director(Person director) {
    this.director = director;
    return this;
  }

  public Address officeAddress() {
    return officeAddress;
  }

  public Company officeAddress(Address officeAddress) {
    this.officeAddress = officeAddress;
    return this;
  }

  @Override
  public boolean equals(Object obj) {
    if (obj == this)
      return true;
    if (obj == null || obj.getClass() != this.getClass())
      return false;
    var that = (Company) obj;
    return Objects.equals(this.name, that.name) &&
        Objects.equals(this.registrationUuid, that.registrationUuid) &&
        Objects.equals(this.director, that.director) &&
        Objects.equals(this.officeAddress, that.officeAddress);
  }

  @Override
  public int hashCode() {
    return Objects.hash(name, registrationUuid, director, officeAddress);
  }

  @Override
  public String toString() {
    return "Company[" +
        "name=" + name + ", " +
        "registrationId=" + registrationUuid + ", " +
        "director=" + director + ", " +
        "officeAddress=" + officeAddress + ']';
  }
}

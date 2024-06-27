package edu.epam.fop.lambdas;

import java.util.Objects;

public final class Address {

  private String country;
  private String city;
  private Integer zipCode;
  private String street;
  private Integer building;
  private Integer apartment;

  public String country() {
    return country;
  }

  public Address country(String country) {
    this.country = country;
    return this;
  }

  public String city() {
    return city;
  }

  public Address city(String city) {
    this.city = city;
    return this;
  }

  public Integer zipCode() {
    return zipCode;
  }

  public Address zipCode(Integer zipCode) {
    this.zipCode = zipCode;
    return this;
  }

  public String street() {
    return street;
  }

  public Address street(String street) {
    this.street = street;
    return this;
  }

  public Integer building() {
    return building;
  }

  public Address building(Integer building) {
    this.building = building;
    return this;
  }

  public Integer apartment() {
    return apartment;
  }

  public Address apartment(Integer apartment) {
    this.apartment = apartment;
    return this;
  }

  @Override
  public boolean equals(Object obj) {
    if (obj == this)
      return true;
    if (obj == null || obj.getClass() != this.getClass())
      return false;
    var that = (Address) obj;
    return Objects.equals(this.country, that.country) &&
        Objects.equals(this.city, that.city) &&
        Objects.equals(this.zipCode, that.zipCode) &&
        Objects.equals(this.street, that.street) &&
        Objects.equals(this.building, that.building) &&
        Objects.equals(this.apartment, that.apartment);
  }

  @Override
  public int hashCode() {
    return Objects.hash(country, city, zipCode, street, building, apartment);
  }

  @Override
  public String toString() {
    return "Address[" +
        "country=" + country + ", " +
        "city=" + city + ", " +
        "zipCode=" + zipCode + ", " +
        "street=" + street + ", " +
        "building=" + building + ", " +
        "apartment=" + apartment + ']';
  }
}

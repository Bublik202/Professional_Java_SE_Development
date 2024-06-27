package edu.epam.fop.lambdas.kamino.equipment;

import java.util.Objects;

public class Equipment {

  private String armor;
  private String weapon;

  public String getArmor() {
    return armor;
  }

  public void setArmor(String armour) {
    this.armor = armour;
  }

  public String getWeapon() {
    return weapon;
  }

  public void setWeapon(String weapon) {
    this.weapon = weapon;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Equipment equipment = (Equipment) o;
    return getArmor().equals(equipment.getArmor()) && getWeapon().equals(equipment.getWeapon());
  }

  @Override
  public int hashCode() {
    return Objects.hash(getArmor(), getWeapon());
  }

  @Override
  public String toString() {
    return "Equipment{" +
        "armour='" + armor + '\'' +
        ", weapon='" + weapon + '\'' +
        '}';
  }
}

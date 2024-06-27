package edu.epam.fop.lambdas.kamino;

import edu.epam.fop.lambdas.kamino.equipment.Equipment;
import java.util.Objects;

public class CloneTrooper {

  private final String code;
  private String nickname;
  private Equipment equipment;

  CloneTrooper(String code) {
    this.code = Objects.requireNonNull(code);
  }

  public String getCode() {
    return code;
  }

  public String getNickname() {
    return nickname;
  }

  public void setNickname(String nickname) {
    this.nickname = nickname;
  }

  public Equipment getEquipment() {
    return equipment;
  }

  public void setEquipment(Equipment equipment) {
    this.equipment = equipment;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    CloneTrooper that = (CloneTrooper) o;
    return getCode().equals(that.getCode()) && Objects.equals(getNickname(), that.getNickname())
        && Objects.equals(getEquipment(), that.getEquipment());
  }

  @Override
  public int hashCode() {
    return Objects.hash(getCode(), getNickname(), getEquipment());
  }

  @Override
  public String toString() {
    return "CloneTrooper{" +
        "code='" + code + '\'' +
        ", nickname='" + nickname + '\'' +
        ", equipment=" + equipment +
        '}';
  }
}

package edu.epam.fop.lambdas.kamino.equipment;

public final class EquipmentFactory {

  private EquipmentFactory() {}

  public static Equipment orderTheSame(Equipment equipment) {
    Equipment ordered = new Equipment();
    ordered.setWeapon(equipment.getWeapon());
    ordered.setArmor(equipment.getArmor());
    return ordered;
  }
}

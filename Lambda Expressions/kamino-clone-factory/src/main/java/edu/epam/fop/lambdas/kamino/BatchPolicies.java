package edu.epam.fop.lambdas.kamino;

import edu.epam.fop.lambdas.kamino.equipment.Equipment;
import edu.epam.fop.lambdas.kamino.equipment.EquipmentFactory;

import java.text.DecimalFormat;
import java.util.Iterator;

public class BatchPolicies {

  public interface BatchPolicy {

    CloneTrooper[] formBatchOf(CloneTrooper base, int count);
  }
  
  public static BatchPolicy getCodeAwarePolicy(String codePrefix, int codeSeed) {
	  return (base, count) -> {
		  CloneTrooper[] clones = new CloneTrooper[count];
		  
//		  String format = String.format("####");
		  DecimalFormat decimalFormat = new DecimalFormat("0000");
		  int seed = codeSeed;
		  for (int i = 0; i < clones.length; i++) {
			  String code = codePrefix + "-" + decimalFormat.format(seed++);
			
			  CloneTrooper cloneTrooper = new CloneTrooper(code);
			  clones[i] = cloneTrooper;
		  }
		  return clones;
      };
  }

  public static BatchPolicy addNicknameAwareness(Iterator<String> nicknamesIterator, BatchPolicy policy) {
	  return (base, count) -> {
		  CloneTrooper[] clones = policy.formBatchOf(base, count);
		  int i = 0;
		  while(nicknamesIterator.hasNext() && i < clones.length) {			 
			  clones[i++].setNickname(nicknamesIterator.next());
		  }  		
		  return clones;
	  };
  }

  public static BatchPolicy addEquipmentOrdering(Equipment equipmentExample, BatchPolicy policy) {
	  return (base, count) -> {
		  CloneTrooper[] clones = policy.formBatchOf(base, count);
		  for (CloneTrooper cloneTrooper : clones) {
			  Equipment equipment = EquipmentFactory.orderTheSame(equipmentExample);
			  cloneTrooper.setEquipment(equipment);
		  }
		  return clones;		  
	  };
  }
}

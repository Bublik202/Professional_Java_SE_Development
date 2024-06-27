package edu.epam.fop.lambdas;

import java.util.Comparator;

public final class RadianComparator {
	/*
	   * Compares 2 angles (passed in radians). The angle counts from 0 up to 2π, the period must
	   * be ignored, i.e. if angle is greater than 2π, then it must be converted to the range [0; 2π).
	   * Precision must be 0.001 (delta), so if |a - b| < 0.001, then a == b.
	   * 0 == 2π
	   */
	
    private static final double PRECISION = 0.001;
    private static final double PERIOD = 2 * Math.PI;
	
	public static Comparator<Double> get() {
		return (o1, o2) -> {
			if(o1 == null && o2 == null) {
	    		return 0; 
	    	}
			if (o1 == null) {
	    		return -1;
	    	}
			if (o2 == null) {
	    		return 1;
	    	}
			double o1Angle = o1 % PERIOD;
			double o2Angle = o2 % PERIOD;
			
			if(Math.abs(o1Angle - o2Angle) < PRECISION) {
				return 0;
			}else if(o1Angle < o2Angle) {
				return -1;
			}else {
				return 1;
			}
		};
    }

}

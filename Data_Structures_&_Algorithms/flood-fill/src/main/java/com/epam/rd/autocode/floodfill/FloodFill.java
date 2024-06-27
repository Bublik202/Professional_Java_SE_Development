package com.epam.rd.autocode.floodfill;

import java.util.Arrays;

public interface FloodFill {
    void flood(final String map, final FloodLogger logger);

    static FloodFill getInstance() {
        return new FloodFill() {
			
			@Override
			public void flood(String map, FloodLogger logger) {
				logger.log(map);
				if(map.indexOf(LAND) == -1) {
					return;
				}
				String[] arrayStr = map.split("\n");
				char[] array = map.toCharArray();
				char[] checkArray = map.toCharArray();

				int len = arrayStr[0].length() + 1;
				for (int i = 0; i < checkArray.length; i++) {
					if(checkArray[i] == WATER) {	
						if(i - 1 >= 0 && array[i - 1] != '\n') {						
							array[i-1] = WATER;
						}
						if(i + 1 <= checkArray.length-1 && array[i + 1] != '\n') {
							array[i+1] = WATER;
						}
						if(i + len < map.length() && array[i + len] != '\n') {
							array[i + len] = WATER;
						}
						if(i - len >= 0 && array[i - len] != '\n') {
							array[i - len] = WATER;
						}
					}
				}
				checkArray = Arrays.copyOf(array, array.length);
				flood(String.valueOf(array), logger);
			}
		};
    }

    char LAND = '█';
    char WATER = '░';
}

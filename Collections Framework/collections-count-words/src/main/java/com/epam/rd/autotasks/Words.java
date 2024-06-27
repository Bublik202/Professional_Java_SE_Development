package com.epam.rd.autotasks;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.StringJoiner;
import java.util.Map.Entry;
import java.util.StringTokenizer;

public class Words {

    public String countWords(List<String> lines) {
    	StringTokenizer tokenizer = new StringTokenizer(
    			lines.toString(), "[] .,‘’(“—/:?!”;*)'\\\"-");
    	
    	Map<String, Integer> map = new HashMap<String, Integer>();
    	
    	while (tokenizer.hasMoreTokens()) {
			String str = tokenizer.nextToken().toLowerCase();
			if(str.length() > 3) {
				int value = map.getOrDefault(str, 0);			
				map.put(str, ++value);				
				
			}
		} 	
    	
    	List<Map.Entry<String, Integer>> list = new ArrayList<>(map.entrySet());       		
        Collections.sort(list, new Comparator<Map.Entry<String, Integer>>() {

			@Override
			public int compare(Entry<String, Integer> o1, Entry<String, Integer> o2) {	
//				int value = o2.getValue().compareTo(o1.getValue());
//				if(value == 0) {
//					return o1.getKey().compareTo(o2.getKey());
//				}								
//				return value;
				return o2.getValue().compareTo(o1.getValue()) == 0 ? 
						o1.getKey().compareTo(o2.getKey()) :
							o2.getValue().compareTo(o1.getValue());
							
			}
		});	 			
    		
        StringJoiner joiner = new StringJoiner("\n");
    	for (Entry<String, Integer> entry : list) {
    		if(entry.getValue() > 9) {
    			joiner.add(entry.getKey() + " - "  + entry.getValue());
    		}   		
		}

        return joiner.toString();
    }
}

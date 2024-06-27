package com.epam.rd.autotasks;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map.Entry;
import java.util.StringTokenizer;
import java.util.function.Function;
import java.util.stream.Collectors;

public class Words {

    public String countWords(List<String> lines) {    
        return Collections.list(new StringTokenizer(
        		lines.toString(), "[] .,‘’(“—/:?!”;*)'\\\"-"))
        		.stream()
        		.map(line -> line.toString().toLowerCase())        		
        		.filter(line -> line.length() >= 4)
        		.collect(Collectors.groupingBy(
        				Function.identity(), 
        				HashMap::new, 
        				Collectors.counting()))
        		.entrySet().stream()
        		.sorted(Entry.<String, Long>comparingByValue().reversed()
        				.thenComparing(Entry.comparingByKey()))
        		.filter(line -> line.getValue() > 9)
        		.map(t -> t.getKey() + " - " + t.getValue())
        		.collect(Collectors.joining("\n"));
    }
}

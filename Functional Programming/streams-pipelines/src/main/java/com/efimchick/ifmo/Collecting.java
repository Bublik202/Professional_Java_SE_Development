package com.efimchick.ifmo;


import com.efimchick.ifmo.util.CourseResult;
import com.efimchick.ifmo.util.Person;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.Comparator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Map.Entry;
import java.util.TreeMap;
import java.util.function.Function;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class Collecting {
	private static final DecimalFormat decimalFormat = new DecimalFormat("#.00",
			DecimalFormatSymbols.getInstance(Locale.US));
	private static final Map<Double, String> map = new LinkedHashMap<>();
	
	{
	    map.put(90.0, "A");
	    map.put(83.0, "B");
	    map.put(75.0, "C");
	    map.put(68.0, "D");
	    map.put(60.0, "E");		
	}    

    public int sum(IntStream intStream) {
    	return intStream.reduce(0, Integer::sum);
    }

    public int production(IntStream intStream) {
    	return intStream.reduce((acc, x) -> acc * x).getAsInt();
    }

    public int oddSum(IntStream intStream) {
    	return intStream
    			.filter(value -> value % 2 != 0)
    			.sum();
    }

    public Map<Integer, Integer> sumByRemainder(int divider, IntStream intStream) {
    	return intStream
    			.boxed()
    			.collect(Collectors.groupingBy(
    					t -> t % divider, 
    					Collectors.summingInt(Integer::intValue)));
    }

    public Map<Person, Double> totalScores(Stream<CourseResult> results) {  
    	List<CourseResult> list = results.collect(Collectors.toList());   	
    	return list.stream()
    			.collect(Collectors.groupingBy(t -> t.getPerson(),
    					Collectors.averagingDouble(value -> value.getTaskResults()
    							.entrySet().stream()
    							.map(t -> t.getValue().doubleValue())
    							.reduce(0.0, Double::sum) / getCountTask(list))));							
    }
    
    public long getCountTask(List<CourseResult> results) {	
		return results.stream()
				.map(CourseResult::getTaskResults)
				.flatMap(t -> t.keySet().stream())
				.distinct()
				.count();   	
    }
    
    public long getCountPerson(List<CourseResult> results) {	
		return results.stream()
				.map(CourseResult::getPerson)
				.distinct()
				.count();   	
    }

    public double averageTotalScore(Stream<CourseResult> results) {
    	List<CourseResult> list = results.collect(Collectors.toList());   	
    	return totalScores(list.stream())
    			.values().stream()
    			.reduce(Double::sum).get() / getCountPerson(list);
    }

    public Map<String, Double> averageScoresPerTask(Stream<CourseResult> results) {
    	List<CourseResult> list = results.collect(Collectors.toList());   	
    	return list.stream()
    			.flatMap(t -> t.getTaskResults().entrySet().stream())
    			.collect(Collectors.groupingBy(Entry::getKey,
    					Collectors.summingDouble(value -> value.getValue().doubleValue()
    							/ getCountPerson(list))));
    }

    public Map<Person, String> defineMarks(Stream<CourseResult> results) {
    	List<CourseResult> list = results.collect(Collectors.toList());
    	return list.stream()
    			.collect(Collectors.toMap(
    					CourseResult::getPerson,
    					t -> {
    						Double score = t.getTaskResults().entrySet().stream()
    								.map(v -> v.getValue().doubleValue())
    								.reduce(0.0, Double::sum) / getCountTask(list);
    						return letterScore(score);
    					}));
    }
    private String letterScore(double score) {
		return map.entrySet().stream()
				.filter(t -> t.getKey() <= score)
				.findFirst()
				.map(t -> t.getValue())
				.orElse("F");
	}

    public String easiestTask(Stream<CourseResult> results) {
    	List<CourseResult> list = results.collect(Collectors.toList());   	
    	return list.stream()
    			.flatMap(t -> t.getTaskResults().entrySet().stream())
    			.collect(Collectors.groupingBy(Entry::getKey,
    					Collectors.summingDouble(value -> value.getValue().doubleValue())))
    			.entrySet().stream()
    			.max(Comparator.comparing(Entry::getValue))
    			.map(Entry::getKey).get();
    }

    public Collector<CourseResult, ?, String> printableStringCollector() {
    	return Collectors.collectingAndThen(Collectors.groupingBy(
    			t -> t.getPerson().getLastName(), TreeMap::new, Collectors.toList()),
    			this::formatString);
    }

	private String formatString(Map<String, List<CourseResult>> map) {
		Integer len = map.entrySet().stream()
				.flatMap(t -> t.getValue().stream())
				.map(t -> t.getPerson().getFirstName()+ " " + t.getPerson().getLastName())
				.mapToInt(String::length)
				.max().getAsInt();
		
		List<String> tasks = map.entrySet().stream()
				.flatMap(t -> t.getValue().stream())
				.flatMap(t -> t.getTaskResults().entrySet().stream())
				.map(Entry::getKey)
				.distinct()
				.sorted()
				.collect(Collectors.toList());
		
		List<CourseResult> sortedCourseResult = map.entrySet().stream()
				.flatMap(t -> t.getValue().stream())
				.collect(Collectors.toList());
		
		StringBuilder builder = new StringBuilder();
		header(len, tasks, builder);		
		middle(len, tasks, sortedCourseResult, builder);		
		bottom(len, tasks, sortedCourseResult, builder);
		
		return builder.toString();
	}

	private void bottom(Integer len, List<String> tasks, List<CourseResult> sortedCourseResult, StringBuilder builder) {
		pedRight(len, builder, "Average");
		
		//score
		Map<String, Double> mapTaskScore = tasks.stream()
		.collect(Collectors.toMap(
				Function.identity(),
				task -> sortedCourseResult.stream()
				.flatMap(t -> t.getTaskResults().entrySet().stream())
						.filter(k -> k.getKey().equalsIgnoreCase(task))						
						.mapToDouble(Map.Entry::getValue)
						.sum() / getCountPerson(sortedCourseResult)));
		tasks.forEach(task -> {
			Double value = mapTaskScore.getOrDefault(task, 0.0);
		    builder.append(String.format("%" + task.length() + "s | ", decimalFormat.format(value)));			
		});
		//Total
		double total = mapTaskScore.entrySet().stream()
				.mapToDouble(Entry::getValue)
				.sum() / getCountTask(sortedCourseResult);
		builder.append(decimalFormat.format(total)).append(" | ");
		
		//Mark
		builder.append(String.format("%" + "Mark".length() + "s", letterScore(total))).append(" |");
	}

	private void middle(Integer len, List<String> tasks, List<CourseResult> sortedCourseResult, StringBuilder builder) {
		sortedCourseResult.forEach(t -> {
			//Name
			String name = t.getPerson().getLastName() + " " + t.getPerson().getFirstName();
			pedRight(len, builder, name);
			
			//Score
			tasks.forEach(task -> {
				Integer score = t.getTaskResults().getOrDefault(task, 0);
				builder.append(String.format("%" + task.length() + "d | ", score));
			});	
			//Total
			double total = t.getTaskResults().values().stream()
					.mapToInt(Integer::intValue).sum() / (double) getCountTask(sortedCourseResult);			
			builder.append(decimalFormat.format(total)).append(" | ");
			//Mark
			builder.append(String.format("%" + "Mark".length() + "s", letterScore(total))).append(" |");
			
			builder.append("\n");
		});
	}

	private void header(Integer len, List<String> tasks, StringBuilder builder) {
		pedRight(len, builder, "Student");
		tasks.forEach(t -> {
			builder.append(String.format("%s", t)).append(" | ");
		});
		builder.append(String.format("%s | %s", "Total", "Mark")).append(" |\n");		
    }

	private void pedRight(Integer len, StringBuilder builder, String name) {
		builder.append(String.format("%-" + len + "s", name)).append(" | ");
	}
	
}


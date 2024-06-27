package com.epam.autotasks;

import java.time.LocalDate;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

import com.epam.autotasks.Cat.Staff;

public class ShelterService {

    public void assignAttendants(List<ShelterRoom> rooms) {
    	Staff[] val = Staff.values();
    	AtomicInteger atomicInteger = new AtomicInteger(0);
    	
    	rooms.stream()
    	.flatMap(room -> room.getCats().stream())   	
    	.filter(cat -> cat.getAttendant() == null)
    	.forEach(cat -> {    		
    		cat.setAttendant(val[atomicInteger.getAndIncrement() % val.length]);
    	});
    	
    }

    public List<Cat> getCheckUpList(List<ShelterRoom> rooms, LocalDate date) {
    	return rooms.stream()
    			.flatMap(room -> room.getCats().stream())
    			.filter(cat -> cat.getLastCheckUpDate() != null 
    			&& cat.getLastCheckUpDate().isBefore(date))
        		.collect(Collectors.toList());
    }

    public List<Cat> getCatsByBreed(List<ShelterRoom> rooms, Cat.Breed breed) {
        return rooms.stream()
        		.flatMap(room -> room.getCats().stream())
        		.filter(cat -> cat.getBreed() != null && cat.getBreed().equals(breed))
        		.collect(Collectors.toList());
    }
}

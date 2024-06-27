package com.efimchick.ifmo.collections;

import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

class MedianQueue extends LinkedList<Integer>{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	public Integer poll() {
		List<Integer> list = new LinkedList<>(this);
        Collections.sort(list);
        int i = this.size() / 2;
        if(this.size() % 2 == 0) {
            i--;
        }
        remove(list.get(i));
		return list.get(i);
	}

	@Override
	public Integer peek() {
		List<Integer> list = new LinkedList<>(this);
        Collections.sort(list);
        int i = this.size() / 2;
        if(this.size() % 2 == 0) {
            i--;
        }
		return list.get(i);
	}

	@Override
	public boolean offer(Integer e) {
		return super.offer(e);
	}
	
	@Override
	public Iterator<Integer> iterator() {
		return super.iterator();
	}
	
	@Override
	public int size() {
		return super.size();
	}
}

package com.efimchick.ifmo.collections;

import java.util.Collection;
import java.util.Comparator;
import java.util.Iterator;
import java.util.Set;
import java.util.TreeSet;

class SortedByAbsoluteValueIntegerSet implements Set<Integer>{

	Set<Integer> sort = new TreeSet<>(new Comparator<Integer>() {

		@Override
		public int compare(Integer o1, Integer o2) {
			return Math.abs(o1) - Math.abs(o2);
		}
		
	});
	

	@Override
	public int size() {
		return sort.size();
	}

	@Override
	public boolean isEmpty() {
		return sort.isEmpty();
	}

	@Override
	public boolean contains(Object o) {
//		return this.contains(o) ? true : false;
		return sort.contains(o);
	}

	@Override
	public Iterator<Integer> iterator() {
		return sort.iterator();
	}

	@Override
	public Object[] toArray() {
		return sort.toArray();
	}

	@Override
	public <T> T[] toArray(T[] a) {
		return sort.toArray(a);
	}

	@Override
	public boolean add(Integer e) {	
		return sort.add(e);
	}

	@Override
	public boolean remove(Object o) {
//		return this.remove(o);
		return sort.remove(o);
	}

	@Override
	public boolean containsAll(Collection<?> c) {
		return sort.containsAll(c);
	}

	@Override
	public boolean addAll(Collection<? extends Integer> c) {
		for (Integer i : c) {
			this.add(i);
		}
		return true;
	}

	@Override
	public boolean retainAll(Collection<?> c) {
		return sort.retainAll(c);
	}

	@Override
	public boolean removeAll(Collection<?> c) {
		return sort.removeAll(c);
	}

	@Override
	public void clear() {
		sort.clear();
	}

}

package com.efimchick.ifmo.collections;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

class PairStringList  extends ArrayList<String>{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	public String remove(int index) {
		if(super.get(index - 1) == super.get(index)) {
			super.remove(index - 1);
			return super.remove(index - 1);
		}else{
			super.remove(index + 1);
			return super.remove(index + 1);
		}		
	}

	@Override
	public boolean remove(Object o) {
		super.remove(o);
		return super.remove(o);
	}

	@Override
	public boolean add(String e) {
		super.add(e);
		return super.add(e);
	}

	@Override
	public void add(int index, String element) {
		if(index % 2 != 0) {
			index += 1;
		}			
		
		super.add(index, element);
		super.add(index, element);
	}

	@Override
	public boolean addAll(Collection<? extends String> c) {
		for (String str : c) {
			this.add(str);
		}
		return true;
	}

	@Override
	public boolean addAll(int index, Collection<? extends String> c) {
		List<String> list = new ArrayList<>();
		for (String str : c) {
			list.add(str);
			list.add(str);
		}
		
		return super.addAll(index % 2 == 0 ? index : index + 1, list);
	}

	@Override
	public String get(int index) {
		return super.get(index);
	}

	@Override
	public String set(int index, String element) {
		super.set(index, element);	
		return index % 2 == 0 ? super.set(index + 1, element) 
				: super.set(index - 1, element);
	}	
	
}

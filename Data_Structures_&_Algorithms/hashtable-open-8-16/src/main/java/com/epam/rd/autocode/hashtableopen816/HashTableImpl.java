package com.epam.rd.autocode.hashtableopen816;

public class HashTableImpl implements HashtableOpen8to16 {
	private int capacity;	
	private int size;
	private MyTable[] myTable;
	private final static int MAX_SIZE = 16;

	public HashTableImpl() {
		this.capacity = 8;
		this.size = 0;
		this.myTable = new MyTable[capacity];
	}
	
	private int hash(int key) {
		if(key < 0) {
			key = - key; 
		}
        return key % capacity;
    }
	
	private void reSize(int newSize) {
		MyTable[] temp = this.myTable;
		this.myTable = new MyTable[newSize];
		this.size = 0;
		for (MyTable myTable : temp) {
			if(myTable != null) {
				insert(myTable.getKeys(), myTable.getVals());
			}
			
		}

	}
	
	@Override
	public void insert(int key, Object value) {
		if(search(key) != null) {
			return;
		}
		if(size >= MAX_SIZE) {
			throw new IllegalStateException();
		}
		
		if(size == capacity) {
			capacity *= 2;
			reSize(capacity);
		}
		MyTable table = new MyTable(key, value);
		int index = hash(key);
				
		while(this.myTable[index] != null && this.myTable[index].getKeys() != key) {
			index++;
			index %= capacity;
		}
		if(this.myTable[index] == null) {			
			size++;
		}
		this.myTable[index] = table;
		
	}

	@Override
	public Object search(int key) {	
		int index = hash(key); 	
		int count = 0;
		while(true) {			
			if(this.myTable[index] != null && this.myTable[index].getKeys() == key) {
				break;
			}
			if(count == capacity) {
				return null;
			}
			count++;
			index++;
			index %= capacity;
		}		
				     		
		return this.myTable[index].getVals();
	}

	@Override
	public void remove(int key) {
		int index = hash(key); 		
		int count = 0;
		while(true) {			
			if(this.myTable[index] != null && this.myTable[index].getKeys() == key) {
				this.myTable[index] = null;		
				size--;
				break;
			}
			if(count++ == capacity) {
				break;
			}
			index++;
			index %= capacity;
		}
				
		if(size * 4 <= capacity && capacity != 2) {
			capacity /= 2;
			reSize(capacity);
		}
	}

	@Override
	public int size() {
		return this.size;
	}

	@Override
	public int[] keys() {
		int[] array = new int[capacity];
		int index = 0;
		for (MyTable table : this.myTable) {
			if(table != null) {
				array[index] = table.getKeys();
			}			
			index++;
		}
		return array;
	}

}

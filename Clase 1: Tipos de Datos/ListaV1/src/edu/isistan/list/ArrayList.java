package edu.isistan.list;

public class ArrayList extends AbstractList {
	
	private static final int DEFAULT_SIZE = 10;
	private Object[] data;
	private int size = 0;
	
	public ArrayList() {
		this.data = new Object[DEFAULT_SIZE];
	}

	@Override
	public void add(Object o) {
		this.mods++;
		//Extends the array size
		if(this.data.length == this.size) {
			Object[] newArray = new Object[this.data.length+DEFAULT_SIZE];
			System.arraycopy(this.data, 0, newArray, 0, this.size);
			this.data = newArray;
		}
		this.data[this.size++] = o;
	}

	@Override
	public Object get(int i) {
		if ((i<0) ||(i>=this.size))
			throw new IndexOutOfBoundsException();
		return this.data[i];
	}

	@Override
	public int size() {
		return this.size;
	}

	@Override
	public Object remove(int i) {
		if ((i<0) ||(i>=this.size))
			throw new IndexOutOfBoundsException();
		Object res = this.data[i];
		System.arraycopy(this.data, i + 1, this.data, i , --this.size - i);
		this.data[this.size] = null;
		return res;
	}
	
	@Override
	public Object[] toArray() {
		//Se reimplementa por eficiencia.
		Object[] res = new Object[this.size];
		System.arraycopy(this.data, 0, res, 0, this.size);
		return res;
	}

}

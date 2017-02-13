package edu.isistan.list;

public class LinkedList extends AbstractList {

	private Node first = null;
	private Node last = null;
	private int size = 0;
	@Override
	public void add(Object o) {
		mods++;
		this.size++;
		Node toAdd = new Node();
		toAdd.value = o;
		if (first == null) {
			this.first = toAdd;
		} else {
			this.last.next = toAdd;
		}
		this.last = toAdd;
	}

	@Override
	public Object get(int i) {
		if ((i<0) ||(i>=this.size))
			throw new IndexOutOfBoundsException();
		Node c = first;
		for(int j = 0; j<i; j++)
			c = c.next;
		return c.value;
	}

	@Override
	public int size() {
		return this.size;
	}

	@Override
	public Object remove(int i) {
		if ((i<0) ||(i>=this.size))
			throw new IndexOutOfBoundsException();
		Node c = first;
		Object v = null;
		this.size--;
		if(i==0) {
			v = first.value;
			first = first.next;
			return v;
		}
		for(int j = 0; j < i-1; j++)
			c = c.next;
		v = c.next.value;
		c.next = c.next.next;
		return v;
	}
	
	private class Node {
		public Object value;
		public Node next;
	}

}

package edu.isistan.list;

public class LinkedList<T> extends AbstractList<T> {

	private Node<T> first = null;
	private Node<T> last = null;
	private int size = 0;
	@Override
	public void add(T o) {
		mods++;
		this.size++;
		Node<T> toAdd = new Node<>();
		toAdd.value = o;
		if (first == null) {
			this.first = toAdd;
		} else {
			this.last.next = toAdd;
		}
		this.last = toAdd;
	}

	@Override
	public T get(int i) {
		if ((i<0) ||(i>=this.size))
			throw new IndexOutOfBoundsException();
		Node<T> c = first;
		for(int j = 0; j<i; j++)
			c = c.next;
		return c.value;
	}

	@Override
	public int size() {
		return this.size;
	}

	@Override
	public T remove(int i) {
		if ((i<0) ||(i>=this.size))
			throw new IndexOutOfBoundsException();
		Node<T> c = first;
		T v = null;
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
	
	private class Node<V> {
		public V value;
		public Node<V> next;
	}

}

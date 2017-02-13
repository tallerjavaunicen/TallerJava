package edu.isistan.list;

public interface List<T> extends Iterable<T>{
	/**
	 * Adds the object o at the end of the list
	 * @param o
	 */
	public void add(T o);
	/**
	 * Gets the object position at i
	 * @param i
	 * @return
	 */
	public T get(int i);
	/**
	 * Gets the index of object o
	 * @param o
	 * @return
	 */
	public int indexOf(T o);
	/**
	 * Gets the size of the list
	 * @return
	 */
	public int size();
	/**
	 * Removes object at pos i
	 * @param i
	 */
	public T remove(int i);
	/**
	 * Remove the first object that is equals to o if exist
	 * @param o
	 */
	public T remove(T o);
	/**
	 * Remove all instances of object o
	 * @param o
	 */
	public int removeAll(T o);
	/**
	 * Returns an array containing all the list elements
	 * @return
	 */
	public Object[] toArray();
	
}

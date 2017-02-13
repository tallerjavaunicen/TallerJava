package edu.isistan.list;

public interface List extends Iterable<Object>{
	/**
	 * Adds the object o at the end of the list
	 * @param o
	 */
	public void add(Object o);
	/**
	 * Gets the object position at i
	 * @param i
	 * @return
	 */
	public Object get(int i);
	/**
	 * Gets the index of object o
	 * @param o
	 * @return
	 */
	public int indexOf(Object o);
	/**
	 * Gets the size of the list
	 * @return
	 */
	public int size();
	/**
	 * Removes object at pos i
	 * @param i
	 */
	public Object remove(int i);
	/**
	 * Remove the first object that is equals to o if exist
	 * @param o
	 */
	public Object remove(Object o);
	/**
	 * Remove all instances of object o
	 * @param o
	 */
	public int removeAll(Object o);
	/**
	 * Returns an array containing all the list elements
	 * @return
	 */
	public Object[] toArray();
	
}

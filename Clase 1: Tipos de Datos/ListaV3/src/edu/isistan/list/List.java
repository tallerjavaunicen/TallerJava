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
	public default int indexOf(T o) {
		if (o == null){
			//edge case null
			for(int i = 0; i < this.size(); i++) {
				if (o == this.get(i))
					return i;
			}
		} else {
			for(int i = 0; i < this.size(); i++) {
				if (o.equals(this.get(i)))
					return i;
			}
		}
		return -1;
	}

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
	public default T remove(T o) {
		int i = this.indexOf(o);
		if (i != -1)
			return this.remove(i);
		return null;
	}
	/**
	 * Remove all instances of object o
	 * @param o
	 */
	public default int removeAll(T o) {
		int next = this.indexOf(o);
		int count = 0;
		while(next != -1) {
			remove(next);
			count++;
			next = this.indexOf(o);
		}
		return count;
	}
	/**
	 * Returns an array containing all the list elements
	 * @return
	 */
	public default Object[] toArray() {
		Object[] res = new Object[this.size()];
		for(int i=0; i<this.size();i++){
			res[i] = this.get(i);
		}
		return res;
	}
	
}

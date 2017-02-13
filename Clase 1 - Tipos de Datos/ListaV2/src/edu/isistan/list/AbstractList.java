package edu.isistan.list;

import java.util.ConcurrentModificationException;
import java.util.Iterator;

public abstract class AbstractList<T> implements List<T> {
	protected int mods = 0;

	@Override
	public Iterator<T> iterator() {
		Iterator<T> i= new Iterator<T>() {
			int next = 0;
			
			int courrentMods = AbstractList.this.mods;
			@Override
			public boolean hasNext() {
				if (courrentMods != AbstractList.this.mods)
					throw new ConcurrentModificationException();
				return next < AbstractList.this.size();
			}

			@Override
			public T next() {
				if (courrentMods != AbstractList.this.mods)
					throw new ConcurrentModificationException();
				return AbstractList.this.get(next++);
			}

			@Override
			public void remove() {
				AbstractList.this.remove(next);
			}
			
			
		};
		return i;
	}

	@Override
	public int indexOf(T o) {
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


	@Override
	public T remove(T o) {
		int i = this.indexOf(o);
		if (i != -1)
			return this.remove(i);
		return null;
	}

	@Override
	public int removeAll(T o) {
		int next = this.indexOf(o);
		int count = 0;
		while(next != -1) {
			remove(next);
			count++;
			next = this.indexOf(o);
		}
		return count;
	}
	
	@Override
	public Object[] toArray() {
		Object[] res = new Object[this.size()];
		for(int i=0; i<this.size();i++){
			res[i] = this.get(i);
		}
		return res;
	}

	@Override
	public int hashCode() {
		int hashCode = 1;
	    for (T e : this)
	    	hashCode = 31*hashCode + (e==null ? 0 : e.hashCode());
	    return hashCode;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (!(obj instanceof List))
			return false;
		List<?> other = (List<?>) obj;
		if (this.size()!=other.size())
			return false;
		Iterator<?> i = other.iterator();
		for (Object o1: this) {
			Object o2 = i.next();
			if (o1 == null ? o2 != null : !o1.equals(o2))
				return false;
		}
		return true;
	}
}

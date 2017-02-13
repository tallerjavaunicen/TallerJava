package edu.isistan.list.test;

import static org.junit.Assert.*;

import java.util.ConcurrentModificationException;

import org.junit.Test;

import edu.isistan.list.LinkedList;
import edu.isistan.list.List;

public class LinkedListTest {
	private static final int DEFAULT_SIZE = 10;
	private List createTenIntList(){
		List l = new LinkedList();
		for(int i = 0; i<DEFAULT_SIZE; i++)
			l.add(i);
		return l;
	}

	@Test
	public void testCreateAddGet() {
		List l = this.createTenIntList();
		int i = 0;
		for(; i < DEFAULT_SIZE; i++)
			assertEquals(i, l.get(i));
		assertEquals(DEFAULT_SIZE, i);
		try{
			l.get(DEFAULT_SIZE);
			fail("Exception expected");
		} catch(IndexOutOfBoundsException e) {
			
		}
	}
	@Test
	public void testIterator() {
		List l = this.createTenIntList();
		int i = 0;
		for(Object o: l)
			assertEquals(Integer.valueOf(i++), o);
		assertEquals(DEFAULT_SIZE, i);
		try{
			for(@SuppressWarnings("unused") Object o: l)
				l.add(10);
				fail("Exception expected");
			l = this.createTenIntList();
			for(@SuppressWarnings("unused") Object o: l)
				l.remove(0);
				fail("Exception expected");
			
		} catch(ConcurrentModificationException e) {
			
		}
	}
	@Test
	public void testIndexOf() {
		List l = this.createTenIntList();
		for(int i = 0; i < DEFAULT_SIZE; i++)
			assertEquals(i, l.indexOf(i));
		assertEquals(-1, l.indexOf(DEFAULT_SIZE));
	}
	@Test
	public void testRemove() {
		List l = this.createTenIntList();
		assertEquals(DEFAULT_SIZE, l.size());
		l.remove(5);
		assertEquals(DEFAULT_SIZE-1, l.size());
		for(int i = 0; i < 5; i++)
			assertEquals(i, l.get(i));
		for(int i = 5; i < l.size(); i++)
			assertEquals(i+1, l.get(i));
		
		l = this.createTenIntList();
		assertEquals(DEFAULT_SIZE, l.size());
		l.remove(0);
		assertEquals(DEFAULT_SIZE-1, l.size());
		for(int i = 0; i < l.size(); i++)
			assertEquals(i+1, l.get(i));
		
		l = this.createTenIntList();
		assertEquals(DEFAULT_SIZE, l.size());
		l.remove(DEFAULT_SIZE-1);
		assertEquals(DEFAULT_SIZE-1, l.size());
		for(int i = 0; i < l.size(); i++)
			assertEquals(i, l.get(i));
	}
	@Test
	public void testRemoveObject() {
		List l = this.createTenIntList();
		assertEquals(DEFAULT_SIZE, l.size());
		l.remove(Integer.valueOf(5));
		assertEquals(DEFAULT_SIZE-1, l.size());
		for(int i = 0; i < 5; i++)
			assertEquals(i, l.get(i));
		for(int i = 5; i < l.size(); i++)
			assertEquals(i+1, l.get(i));
		
		l = this.createTenIntList();
		assertEquals(DEFAULT_SIZE, l.size());
		l.remove(Integer.valueOf(0));
		assertEquals(DEFAULT_SIZE-1, l.size());
		for(int i = 0; i < l.size(); i++)
			assertEquals(i+1, l.get(i));
		
		l = this.createTenIntList();
		assertEquals(DEFAULT_SIZE, l.size());
		l.remove(Integer.valueOf(DEFAULT_SIZE-1));
		assertEquals(DEFAULT_SIZE-1, l.size());
		for(int i = 0; i < l.size(); i++)
			assertEquals(i, l.get(i));
	}
	@Test
	public void testToArray() {
		List l = this.createTenIntList();
		assertArrayEquals(new Object[]{0, 1,  2, 3, 4, 5, 6, 7, 8, 9}, l.toArray());
	}
	@Test
	public void testDoubleSize() {
		List l = this.createTenIntList();
		for(int i = DEFAULT_SIZE; i < DEFAULT_SIZE * 2; i++)
			l.add(i);
		for(int i = 0; i < l.size(); i++)
			assertEquals(i, l.get(i));
		assertEquals(DEFAULT_SIZE * 2, l.size());
	}
}

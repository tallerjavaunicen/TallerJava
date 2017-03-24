package collections;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.TreeSet;
import java.util.concurrent.ConcurrentSkipListSet;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.CopyOnWriteArraySet;

public class CollectionPerformance {

	public interface TimeAction{

		String getName();

		long timeAction(Collection<String> what, int limit);
	}

	public static class TimeIteratorAction implements TimeAction {

		private final String pattern = "Element %d";

		@Override
		public String getName() {
			return "iterate()";
		}

		@Override
		public long timeAction(Collection<String> what, final int limit) {
			for (int i = 0; i < limit; i++) {
				what.add(String.format(pattern, i));
			}

			final long start = System.currentTimeMillis();
			for (final Iterator<String> i = what.iterator(); i.hasNext();) {
				i.next();
			}
			return System.currentTimeMillis() - start;
		}

	}

	public static class TimeGetAction implements TimeAction {

		private final String pattern = "Element %d";

		@Override
		public String getName() {
			return "get()";
		}

		@Override
		public long timeAction(Collection<String> what, final int limit) {
			for (int i = 0; i < limit; i++) {
				what.add(String.format(pattern, i));
			}

			final long start = System.nanoTime();
			for (int i = 0, size = what.size(); i < limit; i++) {
				((List<String>) what).get(i % size);
			}
			return System.nanoTime() - start;
		}

	}

	public static class TimeContainsAction implements TimeAction {

		private final String pattern = "Element %d";

		@Override
		public String getName() {
			return "contains()";
		}

		@Override
		public long timeAction(Collection<String> what, final int limit) {
			for (int i = 0; i < limit; i++) {
				what.add(String.format(pattern, i));
			}

			final long start = System.nanoTime();
			for (int i = 0; i < limit; i++) {
				what.contains(String.format(pattern, i));
			}
			return System.nanoTime() - start;
		}

	}
	
	public static class TimeSetAction implements TimeAction {

		private final String pattern = "Element %d";

		@Override
		public String getName() {
			return "set()";
		}

		@Override
		public long timeAction(Collection<String> what, int limit) {
			for (int i = 0; i < limit; i++) {
				what.add("");
			}

			final long start = System.nanoTime();
			for (int i = 0, size = what.size(); i < limit; i++) {
				((List<String>) what).set(i % size, String.format(pattern, i));
			}
			return System.nanoTime() - start;
		}

	}

	public static class TimeAddAction implements TimeAction {

		private final String pattern = "Element %d";

		@Override
		public String getName() {
			return "add()";
		}

		@Override
		public long timeAction(Collection<String> what, int limit) {
			
			final long start = System.nanoTime();
			for (int i = 0; i < limit; i++) {
				what.add(String.format(pattern, i));
			}

			return System.nanoTime() - start;
		}

	}
	
	public static class TimeSizeAction implements TimeAction {

		private final String pattern = "Element %d";

		@Override
		public String getName() {
			return "size()";
		}

		@Override
		public long timeAction(Collection<String> what, int limit) {
			for (int i = 0; i < limit; i++) {
				what.add(String.format(pattern, i));
			}

			final long start = System.nanoTime();
			for (int i = 0; i < limit; i++) {
				what.size();
			}
			return System.nanoTime() - start;
		}

	}

	private static void run(int limit,int runs, Collection<String> what,TimeAction timeListAction) {
				
		System.out.println(what.getClass().getName());

		long timeInNs = 0;

		System.gc();
		for (int i = 0; i < runs; i++) {
			final long time = timeListAction.timeAction(what, limit);
			timeInNs += time;
		}

		final long avgTimeInNs = timeInNs / runs;
//		final double avgTimeInMs = avgTimeInNs / 1000000D;
		System.out.println("| "+ avgTimeInNs);


		System.out.println();
		System.gc();
	}

	public static void main(String[] args) {

		TimeAction [] actions = new TimeAction[]{new TimeGetAction(),new TimeSetAction(),new TimeSizeAction(),new TimeIteratorAction(),new TimeAddAction()};
		
		//ArrayList - LinkedList - CopyOnWriteArrayList
//		for(TimeAction action : actions){
//			System.out.println(action.getName());
//			CollectionPerformance.run(10000, 100, new ArrayList<String>(), action);
//			CollectionPerformance.run(10000, 100, new LinkedList<String>(), action);
//			CollectionPerformance.run(10000, 100, new CopyOnWriteArrayList<String>(), action);	
//		}
		
		TimeAction [] actionsSet = new TimeAction[]{//new TimeSizeAction(),new TimeIteratorAction(),new TimeAddAction(),
				new TimeContainsAction()};
		
		for(TimeAction action : actionsSet){
			System.out.println(action.getName());
			CollectionPerformance.run(10000, 100, new HashSet<String>(), action);
			CollectionPerformance.run(10000, 100, new LinkedHashSet<String>(), action);
			CollectionPerformance.run(10000, 100, new TreeSet<String>(), action);
			CollectionPerformance.run(10000, 100, new CopyOnWriteArraySet<String>(), action);
			CollectionPerformance.run(10000, 100, new ConcurrentSkipListSet<String>(), action);
		}
		
	}

}

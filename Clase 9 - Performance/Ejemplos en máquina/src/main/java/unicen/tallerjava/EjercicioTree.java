package unicen.tallerjava;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Scanner;
import java.util.logging.Logger;

public class EjercicioTree {

	public static class Tree {
		int x;
		Tree l;
		Tree r;
	}

	private static Logger log = Logger.getLogger(EjercicioTree.class.getName());

	public static void main(String[] args) {
		// Encontrar el camino m�s largo hasta la hoja sin contar repetidos.
		// 4
		// 1 4
		// 2 1 2
		// 3
		// deber�a dar 4: 4->1->2->3

		// 4
		// 1 4
		// 1 1 2
		// 1
		// deber�a dar 2: 4->1

		Tree[] trees = new Tree[] { createTree("(4,(1,(2,(3,,),(4,(1,,),)),),(2,,))"),
				createTree("(4,(1,(1,(1,,),(4,(1,,),)),),(2,,))"), /*randomTree(20000000, 1d)*/ };
		for (Tree tree : trees) {
			// Esto es solo para confirmar que se ley� bien el �rbol
			// log.info(printTree(tree));

			long init = System.currentTimeMillis();
			log.info("Resultado: " + Integer.valueOf(process(tree, 0, new HashMap<>())).toString());
			log.info("Tiempo: " + (System.currentTimeMillis() - init) / 1000f);
		}
	}

	public static class Pair<K, V> {
		K first;
		V second;

		public Pair(K first, V second) {
			super();
			this.first = first;
			this.second = second;
		}
	}

	private static Tree randomTree(int maxNodos, double prob) {
		Tree root = new Tree();
		int nodos = 1;
		LinkedList<Pair<Tree, Integer>> toProcess = new LinkedList<>();
		toProcess.add(new Pair<>(root, 1));
		while (!toProcess.isEmpty()) {
			Pair<Tree, Integer> pair = Math.random() > 0.5 ? toProcess.removeFirst() : toProcess.removeLast();
			Tree currentNode = pair.first;
			Integer prof = pair.second;
			currentNode.x = (int) (Math.random() * 1000);
			if (Math.random() < prob && nodos != maxNodos) {
				currentNode.l = new Tree();
				toProcess.add(new Pair<>(currentNode.l, prof + 1));
				nodos++;
			}
			if (Math.random() < prob && nodos != maxNodos) {
				currentNode.r = new Tree();
				toProcess.add(new Pair<>(currentNode.r, prof + 1));
				nodos++;
			}
		}
		return root;
	}

	private static class Counter {
		int count = 0;
	}

	// No solo no funciona, sino que funciona lento para �rboles grandes
	public static int process(Tree t, int max, HashMap<Integer, Counter> path) {
		if (t == null)
			return 0;
		//
		// int i = path.getOrDefault(t.x, 0) + 1;
		// path.put(t.x, i);

		Counter c = path.get(t.x);
		if (c == null) {
			c = new Counter();
			path.put(t.x, c);
		}
		c.count++;

		if (t.l == null && t.r == null)
			max = path.size();
		else {
			int left = process(t.l, max, path);
			int right = process(t.r, max, path);
			max = left > right ? left : right;
		}

		c.count--;
		if (c.count == 0)
			path.remove(t.x);

		// if (i == 1)
		// path.remove(t.x);
		// else path.put(t.x, i-1);

		return max;
	}

	public static Tree doCreateTree(Scanner scanner) {
		String next = scanner.next();
		if (next.startsWith("(")) {
			Tree t = new Tree();
			t.x = Integer.valueOf(next.substring(1, next.length()));
			t.l = doCreateTree(scanner);
			t.r = doCreateTree(scanner);
			return t;
		}
		return null;
	}

	public static Tree createTree(String tree) {
		Scanner scanner = new Scanner(tree);
		scanner.useDelimiter(",");
		return doCreateTree(scanner);
	}

	public static String printTree(Tree tree) {
		if (tree == null)
			return "";
		else
			return "(" + tree.x + "," + printTree(tree.l) + "," + printTree(tree.r) + ")";
	}

}

package unicen.tallerjava;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Scanner;
import java.util.logging.Logger;

public class EjercicioTreeConErrores {

	public static class Tree {
		int x;
		Tree l;
		Tree r;
	}

	private static Logger log = Logger.getLogger(EjercicioTreeConErrores.class.getName());

	public static void main(String[] args) {
		// Encontrar el camino m�s largo hasta la hoja sin contar repetidos.
		//    4
		//  1   4
		// 2   1 2
		// 3      3
		// deber�a dar 4: 4->1->2->3

		// 4
		// 1 4
		// 1 1 2
		// 1
		// deber�a dar 2: 4->1

		Tree[] trees = new Tree[] { /*createTree("(4,(1,(2,(3,,),(4,(1,,),)),),(2,,))"),
				createTree("(4,(1,(1,(1,,),(4,(1,,),)),),(2,,))"),*/ randomTree(20000000, 1d) };
		for (Tree tree : trees) {
			// Esto es solo para confirmar que se ley� bien el �rbol
			// log.info(printTree(tree));

			long init = System.currentTimeMillis();
			log.info("Resultado: " + Integer.valueOf(process(tree, 0, new ArrayList<>())).toString());
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

	// No solo no funciona, sino que funciona lento para �rboles grandes
	public static int process(Tree t, int max, ArrayList<Integer> path) {
		if (t == null)
			return 0;
		
		// Falta hacer algo ac�
		path.add(t.x);

		if (t.l == null && t.r == null)
			// �Esta estructura es la correcta para este caso?
			max = new HashSet<>(path).size();
		else {
			int left = process(t.l, max, path);
			int right = process(t.r, max, path);
			max = left > right ? left : right;
		}
		
		// Y ac�.
		path.remove(path.size()-1);
		
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

package unicen.tallerjava;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Scanner;

public class TreeTest {
	public static class Tree {
		int x;
		Tree l;
		Tree r;
	}

	public static int process(Tree t, int max, ArrayList<Integer> path) {
		if (t == null)
			return 0;

		path.add(t.x);

		if (t.l == null && t.r == null && path.size() > max)
			max = new HashSet(path).size();
		else {
			int left = process(t.l, max, path);
			int right = process(t.r, max, path);
			max = left > right ? left : right;
		}
		path.remove(path.size() - 1);

		return max;
	}

	public static void main(String[] args) {
		Tree createTree = createTree("(4,(1,(1,(1,,),(4,,)),),(2,,))");
		System.out.println(printTree(createTree));
		System.out.println(process(createTree, 0, new ArrayList<>()));
		System.out.println(getRepetidos("B", Arrays.asList("A", "B", "1", "B", "A", "2", "A", "1", "A", "a")));
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

	public static int getRepetidos(Object el, List<?> cosas) {
		int ret = 0;
		for (int i = 0; i < cosas.size(); i++) {
			if (cosas.get(i).equals(el))
				ret++;
		}
		return ret;
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

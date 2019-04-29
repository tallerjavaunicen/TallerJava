package unicen.tallerjava;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Microbenchmark {

	public int getMinimum(int[] list) {
		int min = Integer.MAX_VALUE;
		for (int i = 0; i < list.length; i++) {
			if (list[i] < min)
				min = list[i];
		}
		return min;
	}

	// En VM args: -XX:+PrintCompilation (imprime las compilaciones)
	// Con -XX:+UnlockDiagnosticVMOptions -XX:CompileCommand=print,*Microbenchmark.getMinimum
	// se imprime el código assembly del método filtrado (getMinimum). Cuidado, porque pide bibliotecas nativas externas.
	// Se pueden encontrar en google y se ponen en la base del proyecto.
	public static void main(String[] args) throws IOException {
		Microbenchmark micro = new Microbenchmark();
		
		List<Integer> intList = null;
		try (Stream<String> lines = Files.lines(Paths.get("ints.txt"))) {
			intList = lines.map(s -> Integer.valueOf(s)).collect(Collectors.toList());
		} catch (IOException e) {
		}
		if (intList == null) {
			intList = new ArrayList<>();
			FileWriter writer = new FileWriter(new File("ints.txt"));
			for (int i = 0; i < 100000; i++) {
				int e = (int) (Math.random() * 100000);
				intList.add(e);
				writer.write(e + "\n");
			}
			writer.close();
		}

		int[] list = new int[intList.size()];
		for (int i = 0; i < list.length; i++) {
			list[i] = intList.get(i);
		}

		// Warm-up
		for (int i = 0; i < 1000; i++) {
			micro.getMinimum(list);
		}

		long init = System.nanoTime();
		int min = micro.getMinimum(list);
		System.out.println(System.nanoTime() - init + " ns");
		System.out.println(min);
	}
}

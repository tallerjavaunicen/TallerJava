package unicen.tallerjava;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Set;

/**
 * Created by acorbellini on 02/03/17.
 */
public class Notificacion<E extends Notificacion.DatosNotificacion, D extends Notificacion.Display> {
	public static interface Display {
		public void mostrar(String text);
	}

	public static class DatosNotificacion {
		String titulo;
		String contenido;

		public DatosNotificacion(String titulo, String contenido) {
			this.titulo = titulo;
			this.contenido = contenido;
		}

		public String imprimirNotificacion() {
			return titulo + ": " + contenido;
		}
	}

	D display;

	List<E> notificaciones = new ArrayList<>();

	public Notificacion(D display) {
		this.display = display;
	}

	public void crearNotificacion(E datos) {
		notificaciones.add(datos);
	}

	public void mostrarNotificaciones() {
		for (E not : notificaciones) {
			display.mostrar(not.imprimirNotificacion());
		}
	}

	public static class DisplaySalidaEstandar implements Display {

		@Override
		public void mostrar(String text) {
			System.out.println(text);
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

		public K getFirst() {
			return first;
		}

		public void setFirst(K first) {
			this.first = first;
		}

		public V getSecond() {
			return second;
		}

		public void setSecond(V second) {
			this.second = second;
		}

	}

	public static class PairUtils {
		public static <K, V> String printPairs(Pair<K, V> pair) {
			K key = pair.getFirst();
			V value = pair.getSecond();
			System.out.println(key.getClass());
			System.out.println(value.getClass());
			System.out.println(pair.getClass());
			return "Key: " + key + " Value:" + value;
		}
	}

	public static <T> List<T> concat(List<T> left, List<T> right) {
		List<T> ret = new ArrayList<T>(left.size()+right.size());
		ret.addAll(left);
		ret.addAll(right);
		return ret;
	}

	public static void main(String[] args) {
		
		List<String> ret = concat(Arrays.asList("Hola"), Arrays.asList("Mundo"));
		
		String pair = PairUtils.printPairs(new Pair<>("Hola", Arrays.asList('m', 'u', 'n', 'd', 'o')));
		System.out.println(pair);
		HashMap<String, ArrayList<Set<Double>>> hashmap = new HashMap<>();
		hashmap.put("Hola", new ArrayList<>());
		System.out.println("hola".substring(0, 1).toUpperCase() + "hola".substring(1));
		List<String> list = Arrays.asList("JAVA", "PROGRAMACION", "AVANZADA");
		List<String> contenido = Arrays.asList("java es un lenguaje de programaci�n",
				"programar es la acci�n de desarrollar programas inform�ticos",
				"es avanzada porque se dan cosas que no son triviales");

		Notificacion<DatosNotificacion, ?> not = new Notificacion<>(new DisplaySalidaEstandar());

		for (int i = 0; i < list.size(); i++) {
			not.crearNotificacion(new DatosNotificacion(list.get(i), contenido.get(i)));
		}

		not.mostrarNotificaciones();
	}
}

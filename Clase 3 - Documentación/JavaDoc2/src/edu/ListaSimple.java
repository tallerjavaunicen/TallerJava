package edu;

/**
 * Define una interfaz simple de lista.
 * 
 * @author Antonela Tommasel
 * @version 1.0
 * @param <T> Clase de los datos contenido por la lista
 */
public interface ListaSimple<T> {

	/**
	 * Retorna el elemento contenido en una posición dada de la lista.
	 * @param i Posición
	 * @return elemento
	 */
	public T obtener(int i);
	/**
	 * Agrega un nuevo dato al final de la lista
	 * @param dato elemento a agregar
	 */
	public void agregar(T dato);
	/**
	 * Retorna la cantidad de elementos contenidos por la lista.
	 * <br>
	 * <ul>
	 * <li> {@link #agregar(Object)} Incrementa el tamaño </li>
	 * <li> {@link #eliminar(int)} Decrementa el tamaño </li>
	 * </ul>
	 * @return cantidad de elementos
	 */
	public int tamanio();
	/**
	 * Elimina un elemento de la lista. No es obligatoria su implementación.
	 * @param i posición del elemento a eliminar.
	 * @throws UnsupportedOperationException Occure si la clase no implementa la operación
	 */
	public void eliminar(int i) throws UnsupportedOperationException;
}

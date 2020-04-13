package edu;

public interface ListaSimple<T> {

	public T obtener(int i);
	
	public void agregar(T dato);
	
	public int tamanio();
	
	public void eliminar(int i) throws UnsupportedOperationException;
}

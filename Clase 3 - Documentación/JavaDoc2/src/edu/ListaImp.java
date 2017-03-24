package edu;
/**
 * Implementación de la {@link ListaSimple} utilizando
 * un <a href="https://docs.oracle.com/javase/tutorial/java/nutsandbolts/arrays.html">arreglo de objetos</a>.
 * 
 * @author Antonela Tommasel
 * @see ListaSimple
 * @param <T>
 */
public class ListaImp<T> implements ListaSimple<T> {
	
	private Object[] datos = new Object[10];
	private int tamanio = 0;
	
	/**
	 * {@inheritDoc}	
	 */
	@SuppressWarnings("unchecked")
	@Override
	public T obtener(int i) {
		if ((i<0)||(i>=tamanio))
			throw new IndexOutOfBoundsException();
		return (T)datos[i];
	}
	/**
	 * {@inheritDoc}	
	 */
	@Override
	public void agregar(T dato) {
		if(tamanio==datos.length){
			int nTam = tamanio *3/2 +1;
			Object[] aux = new Object[nTam];
			System.arraycopy(this.datos, 0, aux, 0, this.datos.length);
			this.datos = aux;
		}
		this.datos[tamanio] = dato;
		tamanio++;
	}
	/**
	 * {@inheritDoc}	
	 */
	@Override
	public int tamanio() {
		return tamanio;
	}
	/**
	 * {@inheritDoc}	
	 */
	@Override
	public void eliminar(int i) throws UnsupportedOperationException {
		if ((i<0)||(i>=tamanio))
			throw new IndexOutOfBoundsException();
		for(int j = i; j<tamanio-1; j++)
			this.datos[j] = this.datos[j+1];
		this.datos[tamanio] = null;
		tamanio--;
	}

}

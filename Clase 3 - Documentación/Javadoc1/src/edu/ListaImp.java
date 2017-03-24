package edu;

public class ListaImp<T> implements ListaSimple<T> {
	
	private Object[] datos = new Object[10];
	private int tamanio = 0;
	
	@SuppressWarnings("unchecked")
	@Override
	public T obtener(int i) {
		if ((i<0)||(i>=tamanio))
			throw new IndexOutOfBoundsException();
		return (T)datos[i];
	}

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

	@Override
	public int tamanio() {
		return tamanio;
	}

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

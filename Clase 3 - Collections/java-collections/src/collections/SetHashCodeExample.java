package collections;

import java.util.HashSet;
import java.util.Set;

public class SetHashCodeExample {

	static class Pet{
		String type;
	 
		public Pet(String s){
			type = s;
		}	
		
		//Qué pasa si no se re-define el equals?
		@Override
		public boolean equals(Object arg0) {
			return type.equals(((Pet)arg0).type);
		}
		
		@Override
		public int hashCode() {
			return type.hashCode();
		}
	}

	
	public static void main(String[] args) {
		
		Set<Pet> petSet = new HashSet<Pet>();
		
		petSet.add(new Pet("perro"));
		petSet.add(new Pet("perro"));
 
		System.out.println("Hay " + petSet.size() + " perros!");
 
		if(petSet.contains(new Pet("perro"))){
			System.out.println("Tenemos algún perro!");
		}else{
			System.out.println("No tenemos perros!");
		}	
	}

	
}

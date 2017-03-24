package collections;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class SortingContractExample {

	static class Pet implements Comparable<Pet>{

		String type;
		int age;

		public Pet(String s,int a){
			type = s;
			age = a;
		}	

		@Override
		public boolean equals(Object arg0) {
			return type.equals(((Pet)arg0).type) && age == (((Pet)arg0).age);
		}

		@Override
		public int compareTo(Pet arg0) {
			if (age < ((Pet)arg0).age)
				return -1;
			else 
				if (age >= ((Pet)arg0).age)
					return 1;
				else
					return 0;
		}
		
		public String toString(){
			return type+" "+age;
		}
		
	}

	

	public static void main(String[] args) {

		List<Pet> pets = new ArrayList<Pet>();
		
		for(int i=0;i<5;i++) //Por qué el resultado depende del valor?
			pets.add(new Pet("dog",i % 5));
		
		try{
			Collections.sort(pets);
			System.out.println(pets);
		}
		catch(IllegalArgumentException e){
			e.printStackTrace();
		}

		pets.clear();
		
		for(int i=0;i<91;i++) //Por qué el resultado depende del valor?
			pets.add(new Pet("dog",i % 5));
		
		try{
			Collections.sort(pets);
			System.out.println(pets);
		}
		catch(IllegalArgumentException e){
			e.printStackTrace();
		}

		Pet pet1 = new Pet("perro", 1);
		Pet pet2 = new Pet("perro", 1);
		
		System.out.println("Equals: "+pet1.equals(pet2));
		System.out.println("pet1.compareTo(pet2) "+pet1.compareTo(pet2));
		System.out.println("pet2.compareTo(pet1) "+pet2.compareTo(pet1));
		
	}

}

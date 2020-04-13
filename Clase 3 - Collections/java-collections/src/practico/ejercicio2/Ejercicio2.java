package practico.ejercicio2;

import java.util.HashSet;
import java.util.Set;

public class Ejercicio2 {
	public static void main(String[] args) {
		Set<Student> students = new HashSet<Student>();

		students.add(new Student(1));
		students.add(new Student(3));
		students.add(new Student(4));
		students.add(new Student(1));
		students.add(new Student(3));

		System.out.println(students.size());
	}	 
}


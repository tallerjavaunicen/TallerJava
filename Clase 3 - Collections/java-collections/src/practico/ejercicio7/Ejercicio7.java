package practico.ejercicio7;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Ejercicio7 {
	public static void main(String[] args) {

		List<String> countries = new ArrayList<String>();

		countries.addAll(Arrays.asList("Australia","Canada","India","USA"));
		countries.remove(new String("USA"));

		System.out.print(countries.size());
		List<Employee> empList = new ArrayList<Employee>();

		empList.add(new Employee(1,"A"));
		empList.add(new Employee(1,"B"));
		empList.add(new Employee(1,"C"));

		empList.remove(new Employee(1,"A"));

		System.out.print(empList.size());

	}
}


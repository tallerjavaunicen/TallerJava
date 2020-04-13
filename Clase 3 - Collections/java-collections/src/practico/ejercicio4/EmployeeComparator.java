package practico.ejercicio4;

import java.util.Comparator;

public class EmployeeComparator implements Comparator<Employee>{
	
	@Override
	public int compare(Employee emp1, Employee emp2) {

		return emp2.id - emp1.id;
	}
}


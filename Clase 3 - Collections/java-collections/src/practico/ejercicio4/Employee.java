package practico.ejercicio4;

public class Employee implements Comparable<Employee>{
	int id;
	String name;

	Employee(int id, String name){
		this.id = id;
		this.name = name;
	}
	@Override
	public int compareTo(Employee emp) {
		return this.name.compareTo(emp.name);
	}

}

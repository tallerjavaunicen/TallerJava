package collections;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MapAggregateExample {

	static class Department{
		String name;
		Employee chief;

		public Department(String n,Employee e){
			name = n;
			chief = e;
		}

		public Department(String n){
			name = n;
		}
	}

	static class Employee{
		Department dpto;
		String name;
		int salary;

		public Employee(String n,Department d,int s){
			name = n;
			dpto = d;
			salary = s;
		}

		public Department getDepartament(){
			return dpto;
		}

		public int getSalary(){
			return salary;
		}
	}

	public static void main(String[] args) {
		List<Employee> employees = new ArrayList<Employee>();
		Department d1 = new Department("finanzas");
		Department d2 = new Department("it");

		employees.add(new Employee("Juan", d2, 15000));
		employees.add(new Employee("Marcelo", d1, 25000));
		employees.add(new Employee("Andres", d2, 2500));

		//Mapa con los Empleados por departamento.
		Map<Department,List<Employee>> byDepartament = new HashMap<Department,List<Employee>>();
		for(Employee e : employees){
			Department d = e.getDepartament();
			List<Employee> aux = byDepartament.get(d);
			if(aux == null){
				aux = new ArrayList<Employee>();
				byDepartament.put(d, aux);
			}
			aux.add(e);
		}

		//Sumar los salarios por departamento
		Map<Department,Integer> totalSalaryByDepto = new HashMap<Department,Integer>();
		for(Employee e : employees){
			Department d = e.getDepartament();
			Integer total = totalSalaryByDepto.get(d);
			totalSalaryByDepto.put(d, (total == null) ? e.getSalary() : total + e.getSalary());
		}
	}

}

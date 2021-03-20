package model;

public class Employee { // EmployeeWorkSessionsLog
	public static class EmptyNameException extends Exception {
	}

	public String name;

	public Employee(String name) throws EmptyNameException,
			NullPointerException {
		if (name == null) {
			throw new NullPointerException();
		}
		if (name.length() == 0) {
			throw new EmptyNameException();
		}
		setName(name);
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public boolean equals(Employee employee) {
		if (employee == null)
			return false;
		return getName().equals(employee.getName());
	}

}

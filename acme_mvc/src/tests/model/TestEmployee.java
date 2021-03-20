package tests.model;

import model.Employee;
import model.Employee.EmptyNameException;

public class TestEmployee {
	public static void main(String[] args) {
		Employee testEmployee = null;
		try { // test null employee name
			testEmployee = new Employee(null);
		} catch (NullPointerException e) {
			e.printStackTrace();
		} catch (EmptyNameException e) {
			e.printStackTrace();
		}
		if (testEmployee != null) {
			System.out.println("test null employee name" + "FAILED");
		}
		try { // test empty employee name
			testEmployee = new Employee("");
		} catch (NullPointerException e) {
			e.printStackTrace();
		} catch (EmptyNameException e) {
			e.printStackTrace();
		}
		if (testEmployee != null) {
			System.out.println("test empty employee name" + "FAILED");
		}
		try { // test non-null non-empty employee name
			testEmployee = new Employee("testEmployee");
		} catch (NullPointerException e) {
			e.printStackTrace();
		} catch (EmptyNameException e) {
			e.printStackTrace();
		}
		if (testEmployee == null) {
			System.out.println("test non_null employee name" + "FAILED");
		}
		System.out.println(testEmployee.getName());
	}
}

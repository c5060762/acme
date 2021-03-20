package tests.model;

import java.util.ArrayList;

import model.EmployeeWorkSessionsLog;

public class TestEmployeeWorkSessionsLog {
	public static void TestNullEmployeeName() {
		EmployeeWorkSessionsLog test = null;
		try {// test null employee name
			test = new EmployeeWorkSessionsLog(null, null);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void TestNonNullEmployeeName() {
		EmployeeWorkSessionsLog test = null;
		try {// test null sessions array
			test = new EmployeeWorkSessionsLog("testName", null);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void TestSameDayNonOverlappingSessions() {
		System.out.println("test same day non-overlapping sessions");
		EmployeeWorkSessionsLog test = null;
		try {// test same day non overlapping sessions
			ArrayList<String[]> testArray = new ArrayList<String[]>();
			testArray.add(new String[] { "MO", "09:00", "12:00" });
			testArray.add(new String[] { "MO", "12:01", "14:00" });
			test = new EmployeeWorkSessionsLog("testName", testArray);
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println(test);
	}

	public static void TestSameDayOverlappingSessions() {
		EmployeeWorkSessionsLog test = null;
		System.out.println("test same day overlapping sessions");
		try {// test same day overlapping sessions
			ArrayList<String[]> testArray = new ArrayList<String[]>();
			testArray.add(new String[] { "MO", "09:00", "12:00" });
			testArray.add(new String[] { "MO", "12:00", "14:00" });
			test = new EmployeeWorkSessionsLog("testName", testArray);
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println(test);
	}

	public static void TestExceededSessions() {
		System.out.println("test EXCEEDED SESSIONS");
		EmployeeWorkSessionsLog test = null;
		try {// test EXCEEDED SESSIONS
			ArrayList<String[]> testArray = new ArrayList<String[]>();
			testArray.add(new String[] { "MO", "09:00", "12:00" });
			testArray.add(new String[] { "MO", "12:01", "14:00" });
			testArray.add(new String[] { "TU", "12:01", "14:00" });
			testArray.add(new String[] { "WE", "12:01", "14:00" });
			testArray.add(new String[] { "TH", "12:01", "14:00" });
			testArray.add(new String[] { "FR", "12:01", "14:00" });
			testArray.add(new String[] { "SA", "12:01", "14:00" });
			testArray.add(new String[] { "SA", "12:01", "14:00" }); //*
			testArray.add(new String[] { "SU", "12:01", "14:00" });
			testArray.add(new String[] { "MO", "12:01", "14:00" }); //*
			test = new EmployeeWorkSessionsLog("testName", testArray);
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println(test);
	}

	public static void main(String[] args) {
		TestNullEmployeeName();
		TestNonNullEmployeeName();
		TestSameDayNonOverlappingSessions();
		TestSameDayOverlappingSessions();
		TestExceededSessions();
	}
}

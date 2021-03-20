package model;

import java.time.LocalTime;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;

import model.Employee.EmptyNameException;
import model.WorkSession.NonExistantDayException;
import model.WorkSession.TimeSequenceExcepcion;

public class EmployeeWorkSessionsLog extends ArrayList<WorkSession> {
	public static class OverlappingWorkSessionException extends Exception {
		public OverlappingWorkSessionException(String string) {
			super(string);
		}
	}

	private Employee employee;

	public EmployeeWorkSessionsLog(String name, ArrayList<String[]> sessions)
			throws NullPointerException, EmptyNameException {
		setEmployee(new Employee(name));
		if (sessions == null)
			throw new NullPointerException();
		for (String[] array : sessions) {
			String day = array[0], start = array[1], end = array[2];
			try {
				AddWorkSession(day, start, end);
			} catch (DateTimeParseException | OverlappingWorkSessionException
					| NonExistantDayException | TimeSequenceExcepcion e) {
				e.printStackTrace();
				continue;
			}
		}
	}

	@Override
	public boolean contains(Object object) {
		if (object == null)
			return false;
		WorkSession newSession = (WorkSession) object;
		for (WorkSession session : this) {
			if (session.equals(newSession))
				return true;
		}
		return false;
	}

	public Employee getEmployee() {
		return employee;
	}

	public void setEmployee(Employee employee) {
		this.employee = employee;
	}

	public void SetEmployee(String employeeName) throws EmptyNameException,
			NullPointerException {
		this.employee = new Employee(employeeName);
	}

	public void AddWorkSession(String day, String start, String end)
			throws OverlappingWorkSessionException, NonExistantDayException,
			DateTimeParseException, NullPointerException, TimeSequenceExcepcion {
		WorkSession newWorkSession = new WorkSession(day, start, end);
		boolean sessionAlreadyContained = contains(newWorkSession);
		if (sessionAlreadyContained) {
			throw new OverlappingWorkSessionException(newWorkSession.toString());
		}
		LocalTime s1 = newWorkSession.getStart();
		LocalTime e1 = newWorkSession.getEnd();
		for (WorkSession currentSession : this) {
			// Two same day work sessions shouldn't overlap!!!
			if (currentSession.getDay().equals(newWorkSession.getDay())) {
				LocalTime s0 = currentSession.getStart();
				LocalTime e0 = currentSession.getEnd();
				if (s0.isBefore(s1)) {
					// day0 |-----------------s0-----------e0---------|
					// day0 |--------------------s1-----e1'---e1''----|
					if (e0.isAfter(s1) || e0.equals(s1)) {
						throw new OverlappingWorkSessionException(
								newWorkSession.toString());
					}
				}
				if (s1.isBefore(s0)) {
					// day0 |-----------------s0-----------e0---------|
					// day0 |-------------s1-----------e1'-----e1''---|
					if (e1.isAfter(s0) || e1.equals(s0)) {
						throw new OverlappingWorkSessionException(
								newWorkSession.toString());
					}
				}
				break;
			}
		}
		add(newWorkSession);
	}

	public String toString() {
		StringBuilder strBuilder = new StringBuilder(getEmployee().getName()
				+ "\n");
		for (WorkSession session : this) {
			strBuilder.append(session + "\n");
		}
		strBuilder.append("size: " + size() + "\n");
		return strBuilder.toString();
	}

}

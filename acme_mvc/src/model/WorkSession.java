package model;

import java.time.LocalTime;
import java.time.format.DateTimeParseException;

public class WorkSession {

	public static class TimeSequenceExcepcion extends Exception {
	}

	public static class NonExistantDayException extends Exception {

	}

	public static enum Day {
		MO, TU, WE, TH, FR, SA, SU
	};

	private Day day;
	private LocalTime start;
	private LocalTime end;

	public WorkSession() {

	}

	public WorkSession(String dayName, String start, String end)
			throws DateTimeParseException, TimeSequenceExcepcion,
			NonExistantDayException {
		Day workDay = null;
		for (Day day : Day.values()) {
			if (day.name().equals(dayName)) {
				workDay = day;
				break;
			}
		}
		if (workDay == null)
			throw new NonExistantDayException();
		LocalTime startTime = null;
		LocalTime endTime = null;
		startTime = LocalTime.parse(start);
		endTime = LocalTime.parse(end);
		if (startTime.equals(endTime)) {
			throw new TimeSequenceExcepcion();
		}
		if (endTime.equals(LocalTime.MIDNIGHT)) {
			endTime = LocalTime.MAX;
		}
		if (startTime.equals(endTime))
			throw new TimeSequenceExcepcion();
		if (startTime.isAfter(endTime))
			throw new TimeSequenceExcepcion();
		setDay(workDay);
		setStart(startTime);
		setEnd(endTime);
	}

	public Day getDay() {
		return day;
	}

	public void setDay(Day day) {
		this.day = day;
	}

	public LocalTime getStart() {
		return start;
	}

	public void setStart(LocalTime start) {
		this.start = start;
	}

	public LocalTime getEnd() {
		return end;
	}

	public void setEnd(LocalTime end) {
		this.end = end;
	}

	public String toString() {
		return getDay().name() + "\t" + getStart() + "\t" + getEnd();
	}

	public boolean equals(WorkSession session) {
		if (session == null) {
			return false;
		}
		return getDay().equals(session.getDay())
				&& getStart().equals(session.getStart())
				&& getEnd().equals(session.getEnd());
	}
}

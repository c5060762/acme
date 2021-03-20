package control.payment;

import java.time.LocalTime;

import model.EmployeeWorkSessionsLog;
import model.WorkSession;
import model.WorkSession.Day;

public class PaymentCalculator {

	public float GetWorkSessionPayment(Day day, LocalTime start, LocalTime end) {
		float payment = 0f;
		LocalTime nineInTheMorning = LocalTime.parse("09:00"), sixInTheAfterNoon = LocalTime
				.parse("18:00");
		ContextPayment contextPayment;
		HourPaymentStrategy hourPaymentStrategy = null;
		if (end.equals(LocalTime.MIDNIGHT)) {
			end = LocalTime.MAX;
		}
		if (start.equals(end)) {
			return 0f;
		}
		if (start.isBefore(nineInTheMorning.plusMinutes(1))) {
			if (end.isBefore(nineInTheMorning.plusMinutes(1))) {
				hourPaymentStrategy = new EarlyMorningPayment();
			} else if (end.isAfter(sixInTheAfterNoon)) { // A marathonic work
															// session!!!
				float paymentA = GetWorkSessionPayment(day, start,
						nineInTheMorning);
				float paymentB = GetWorkSessionPayment(day,
						nineInTheMorning.plusMinutes(1), sixInTheAfterNoon);
				float paymentC = GetWorkSessionPayment(day,
						sixInTheAfterNoon.plusMinutes(1), end);
				return paymentA + paymentB + paymentC;
			} else { // Big work session
				float paymentA = GetWorkSessionPayment(day, start,
						nineInTheMorning);
				float paymentB = GetWorkSessionPayment(day,
						nineInTheMorning.plusMinutes(1), end);
				return paymentA + paymentB;
			}
		} else {
			if (start.isAfter(sixInTheAfterNoon)) { // Nightly work session
				hourPaymentStrategy = new NightlyHoursPayment();
			} else { // Regular work session
				if (end.isAfter(sixInTheAfterNoon)) {
					float paymentA = GetWorkSessionPayment(day, start,
							sixInTheAfterNoon);
					float paymentB = GetWorkSessionPayment(day,
							sixInTheAfterNoon.plusMinutes(1), end);
					return paymentA + paymentB;
				} else {
					hourPaymentStrategy = new RegularHoursPayment();
				}
			}
		}
		if (day.ordinal() < WorkSession.Day.SA.ordinal()) {
			contextPayment = new ContextPayment(new WeekDayPayment(),
					hourPaymentStrategy);
		} else {
			contextPayment = new ContextPayment(new WeekEndDayPayment(),
					hourPaymentStrategy);
		}
		payment += contextPayment.getPaymentAmmount(start, end);
		return payment;
	}

	public float GetEmployeeWorkSessionsTotalPayment(
			EmployeeWorkSessionsLog employeeWorkSessionsLog)
			throws ArrayIndexOutOfBoundsException {
		float totalPayment = 0f;
		for (WorkSession session : employeeWorkSessionsLog) {
			float sessionPayment = 0f;
			sessionPayment = GetWorkSessionPayment(session.getDay(),
					session.getStart(), session.getEnd());
			totalPayment += sessionPayment;
		}
		return totalPayment;
	}

}

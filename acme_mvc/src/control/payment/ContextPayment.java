package control.payment;

import java.time.LocalTime;

public class ContextPayment {
	/*
	 * Payment rate depends on day and hour, i.e., depends on the context Early
	 * morning and night work hours implies an additional bonus on the regular
	 * base hour rate.
	 * 
	 * ContextPayment is defined by two payment strategies, one at day level and
	 * one at hour level.
	 * 
	 * Day level strategy is defined by a base work rate. Week days: hourly work
	 * rate is $15; Week end days: hourly work rate is $20
	 * 
	 * Hour level strategy is defined by a bonus work rate which depends on the
	 * following: 01:00 - 09:00 $15; 09:01 - 18:00 $0; 18:01 - 00:00 $10.
	 */
	private DayPaymentStrategy dayPaymentStrategy;
	private HourPaymentStrategy hourPaymentStrategy;

	public ContextPayment(DayPaymentStrategy dayPaymentStrategy,
			HourPaymentStrategy hourPaymentStrategy) {
		this.dayPaymentStrategy = dayPaymentStrategy;
		this.hourPaymentStrategy = hourPaymentStrategy;
	}

	public float getWorkSessionDurationInHours(LocalTime start, LocalTime end) {
		int startHour = start.getHour(), endHour = end.getHour(), startMinute = start
				.getMinute(), endMinute = end.getMinute();
		if (end.equals(LocalTime.MAX)) {
			endHour = 24;
			endMinute = 0;
		}
		float workSessionDurationInHours = (float) (endHour - startHour) * 60f;
		workSessionDurationInHours += (float) (endMinute - startMinute);
		workSessionDurationInHours /= 60f;
		return workSessionDurationInHours;
	}

	public float getPaymentAmmount(LocalTime start, LocalTime end) {
		float payment = 0f;
		payment += getWorkSessionDurationInHours(start, end)
				* (dayPaymentStrategy.getBaseHourlyRate() + hourPaymentStrategy
						.getBonusToBaseHourlyRate());
		return payment;
	}

}

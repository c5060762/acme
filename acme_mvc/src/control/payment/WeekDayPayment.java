package control.payment;

public class WeekDayPayment implements DayPaymentStrategy {

	public float getBaseHourlyRate() {
		return 15f;
	}

}

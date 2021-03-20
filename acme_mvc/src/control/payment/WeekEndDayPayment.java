package control.payment;

public class WeekEndDayPayment implements DayPaymentStrategy{
	public float getBaseHourlyRate() {
		return 20f;
	}

}

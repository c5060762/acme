package control.payment;

public class NightlyHoursPayment implements HourPaymentStrategy{

	public float getBonusToBaseHourlyRate() {
		return 5f;
	}

}

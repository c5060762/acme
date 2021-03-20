package control.payment;
import java.time.LocalTime;

public class EarlyMorningPayment implements HourPaymentStrategy{

	public float CalculatePayment(LocalTime start, LocalTime end) {
		// TODO Auto-generated method stub
		return 0;
	}

	public float getBonusToBaseHourlyRate() {
		// TODO Auto-generated method stub
		return 10f;
	}

}

package view;

public class PaymentDisplay {
	public void Show(String employeeName, float employeePayment) {
		StringBuilder strBuilder = new StringBuilder("The amount to pay "
				+ employeeName + " is: ");
		float floorNumber = (float) Math.floor(employeePayment);
		if (employeePayment - floorNumber < 0.01) {
			strBuilder.append((int) employeePayment);
		} else {
			strBuilder.append(String.format("%.2f", employeePayment));
		}
		System.out.println(strBuilder.append(" USD").toString());
	}
}

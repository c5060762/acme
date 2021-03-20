package control;

import java.io.IOException;
import java.util.ArrayList;

import model.Employee.EmptyNameException;
import model.EmployeeWorkSessionsLog;
import view.PaymentDisplay;
import control.file.LineRepository;
import control.line.LineParser;
import control.line.LineParser.EmptyStringException;
import control.line.LineParser.SkippedLineException;
import control.line.ParsedLine;
import control.payment.PaymentCalculator;

public class Acme {
	public void Process(String filename) {
		try {
			ArrayList<EmployeeWorkSessionsLog> employeeWorkSessionsLogArray = new ArrayList<EmployeeWorkSessionsLog>();
			LineRepository repository = new LineRepository(filename);
			while (repository.getIterator().hasLines()) {
				ParsedLine parsedLine = null;
				String rawLine = repository.getIterator().next();
				try {
					parsedLine = LineParser.ParseLine(rawLine);
				} catch (NullPointerException | EmptyStringException | SkippedLineException e) {
					e.printStackTrace();
					continue;
				}
				try {
					employeeWorkSessionsLogArray
							.add(new EmployeeWorkSessionsLog(parsedLine.name,
									parsedLine.sessions));
				} catch (NullPointerException | EmptyNameException e) {
					e.printStackTrace();
					continue;
				}
			}
			PaymentCalculator paymentCalculator = new PaymentCalculator();
			PaymentDisplay paymentDisplay = new PaymentDisplay();
			for (EmployeeWorkSessionsLog employeeSessions : employeeWorkSessionsLogArray) {
				String employeeName = employeeSessions.getEmployee().getName();
				float employeePayment = paymentCalculator
						.GetEmployeeWorkSessionsTotalPayment(employeeSessions);
				paymentDisplay.Show(employeeName, employeePayment);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		String defaultFilename = "src/resources/untitled", filename = null;
		try {
			filename = args[0];
		} catch (ArrayIndexOutOfBoundsException e) {
			e.printStackTrace();
			filename = defaultFilename;
		}
		Acme acme = new Acme();
		acme.Process(filename);
	}
}

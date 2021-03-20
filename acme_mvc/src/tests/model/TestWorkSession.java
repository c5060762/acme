package tests.model;

import java.time.format.DateTimeParseException;

import model.WorkSession;
import model.WorkSession.NonExistantDayException;
import model.WorkSession.TimeSequenceExcepcion;

public class TestWorkSession {
	public static void main(String[] args){
		WorkSession session=null;
		//Test non-existant day
		try {
			session=new WorkSession("ninguno","01:00","02:00");
		} catch (DateTimeParseException | TimeSequenceExcepcion
				| NonExistantDayException e) {
			e.printStackTrace();
		}
		try {//Test time-sequence exception
			session=new WorkSession("MO","02:00","01:00");
		} catch (DateTimeParseException | TimeSequenceExcepcion
				| NonExistantDayException e) {
			e.printStackTrace();
		}
		try {//test correct input data
			session=new WorkSession("MO","01:00","02:00");
		} catch (DateTimeParseException | TimeSequenceExcepcion
				| NonExistantDayException e) {
			e.printStackTrace();
		}
		System.out.println(session);
		try {//test whole day session
			session=new WorkSession("MO","00:00","00:00");
		} catch (DateTimeParseException | TimeSequenceExcepcion
				| NonExistantDayException e) {
			e.printStackTrace();
		}
		System.out.println(session);
	}

}

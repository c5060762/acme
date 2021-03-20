package control.line;

import java.util.ArrayList;

public class LineParser {

	public static class EmptyStringException extends Exception {
	}

	public static class SkippedLineException extends Exception {

	}

	public static ParsedLine ParseLine(String line)
			throws EmptyStringException, NullPointerException,
			SkippedLineException {
		if (line == null) {
			throw new NullPointerException();
		}
		if (line.length() == 0) {
			throw new EmptyStringException();
		}
		final String nameDelimiterString = "=";
		final String dateSplitterString = ",";
		final String timeSplitterString = "-";
		final String commentString = "/";
		final int weekDayStringLenght = 2;
		String splittedLine[] = line.split(nameDelimiterString);
		String name = splittedLine[0].trim();
		if (name.startsWith(commentString)) {
			throw new SkippedLineException();
		}
		splittedLine[1] = splittedLine[1].split(commentString)[0].trim(); // skip
																			// comments
		String[] sessions = splittedLine[1].split(dateSplitterString);
		ArrayList<String[]> parsedSessions = new ArrayList<String[]>();
		for (String session : sessions) {
			String day = null, start = null, end = null;
			day = session.substring(0, weekDayStringLenght);
			String timeSplit[] = session.substring(weekDayStringLenght).split(
					timeSplitterString);
			start = timeSplit[0];
			end = timeSplit[1];
			parsedSessions.add(new String[] { day, start, end });
		}
		return new ParsedLine(name, parsedSessions);
	}
}

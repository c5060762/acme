package control.line;

import java.util.ArrayList;

public class ParsedLine {
	public String name;
	public ArrayList<String[]> sessions;

	ParsedLine(String name, ArrayList<String[]> sessions) {
		this.name = name;
		this.sessions = sessions;
	}

	public String toString() {
		StringBuilder strBuilder = new StringBuilder(name + "\n");
		for (String[] array : sessions) {
			for (String field : array) {
				strBuilder.append(field + "\t");
			}
			strBuilder.append("\n");
		}
		return strBuilder.toString();
	}
}
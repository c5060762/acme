package control.file;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class LineRepository implements Container<String> {
	BufferedReader reader;

	public LineRepository(String filename) throws FileNotFoundException,
			IOException {
		reader = new BufferedReader(new FileReader(new File(filename)));
	}

	@Override
	public Iterator<String> getIterator() {
		return new LineIterator();
	}

	private class LineIterator implements Iterator<String> {

		@Override
		public boolean hasLines() throws IOException {
			if (reader.ready()) {
				return true;
			} else {
				reader.close();
				return false;
			}
		}

		@Override
		public String next() throws IOException {
			return reader.readLine();
		}
	}
}

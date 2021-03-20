package tests.control;

import java.io.IOException;

import control.file.LineRepository;

public class TestLineRepository {
	public static void main(String[] args) {
		String filename = "readme.md";
		LineRepository repository = null;
		try {
			repository = new LineRepository(filename);
			while (repository.getIterator().hasLines()) {
				System.out.println(repository.getIterator().next());
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}

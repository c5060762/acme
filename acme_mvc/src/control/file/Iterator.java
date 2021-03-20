package control.file;

import java.io.IOException;

public interface Iterator<T> {
	public boolean hasLines() throws IOException;
	public T next() throws IOException;

}

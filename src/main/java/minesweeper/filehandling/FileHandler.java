package minesweeper.filehandling;

import java.io.IOException;

public interface FileHandler {
	
	public String readFromFile(String filename) throws IOException;
	
	public void writeToFile(String txt, String filename) throws IOException;

}

package minesweeper.filehandling;

import java.io.IOException;

public interface FileHandler {
	
	String readFromFile(String filename) throws IOException;
	
	void writeToFile(String txt, String filename) throws IOException;

}

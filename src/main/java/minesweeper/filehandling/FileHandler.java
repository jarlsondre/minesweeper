package minesweeper.filehandling;


public interface FileHandler {
	
	public String readFromFile(String filename);
	
	public void writeToFile(String txt, String filename);

}

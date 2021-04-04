package minesweeper.filehandling;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.Reader;
import java.io.Writer;

public class GameFileHandler implements FileHandler {

	// src/main/java/minesweeper/filehandling/"

	@Override
	public String readFromFile(String filename) throws IOException{
		// URL url = getClass().getResource(filename);
		File file = new File("src/main/java/minesweeper/filehandling/" + filename);
		String games = null;
		InputStream in = new FileInputStream(file);			
		Reader reader = new InputStreamReader(in, "UTF-8");
		char[] chars = new char[(int) file.length()];
        reader.read(chars);
        games = new String(chars);	
        reader.close();
        return games;
	}

	@Override
	public void writeToFile(String txt, String filename) throws IOException {
		// URL url = getClass().getResource(filename);
		File file = new File("src/main/java/minesweeper/filehandling/" + filename);
		OutputStream out = new FileOutputStream(file);
		Writer writer = new OutputStreamWriter(out);
		writer.write(txt);
		writer.close();
	}
	
	
	public static void main(String[] args) {
		// test
		GameFileHandler f = new GameFileHandler();
		// f.writeToFile("Ole: 80,", "games.txt"); // Vet ikke om denne funker bra
		try {
			System.out.println(f.readFromFile("games.txt"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}

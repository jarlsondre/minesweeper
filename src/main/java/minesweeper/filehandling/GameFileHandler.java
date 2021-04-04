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
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.file.Path;
import java.nio.file.Paths;

public class GameFileHandler implements FileHandler {

	// src/main/java/minesweeper/filehandling/"

	@Override
	public String readFromFile(String filename) {
		// URL url = getClass().getResource(filename);
		File file = new File("src/main/java/minesweeper/filehandling/" + filename);
		String games = null;
		try {
			InputStream in = new FileInputStream(file);			
			Reader reader = new InputStreamReader(in, "UTF-8");
			char[] chars = new char[(int) file.length()];
	        reader.read(chars);
	        games = new String(chars);	
	        reader.close();
		} catch (IOException e) {
			e.printStackTrace(); // TODO Noe bedre å gjøre her?
		}
        return games;
	}

	@Override
	public void writeToFile(String txt, String filename) {
		// URL url = getClass().getResource(filename);
		File file = new File("src/main/java/minesweeper/filehandling/" + filename);
		try {
			OutputStream out = new FileOutputStream(file);
			Writer writer = new OutputStreamWriter(out);
			writer.write(txt);
			writer.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	
	public static void main(String[] args) {
		// test
		GameFileHandler f = new GameFileHandler();
		f.writeToFile("Ole: 80", "games.txt"); // Vet ikke om denne funker bra
		System.out.println(f.readFromFile("games.txt"));
		
	}

}

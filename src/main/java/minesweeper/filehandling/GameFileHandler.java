package minesweeper.filehandling;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.Reader;
import java.net.URL;

public class GameFileHandler implements FileHandler {

	@Override
	public String readFromFile(String filename) {
		// TODO Auto-generated method stub
		URL url = getClass().getResource(filename);
		File file = new File(url.getPath());
		String games = null;
		try {
			InputStream in = new FileInputStream(file);			
			Reader reader = new InputStreamReader(in, "UTF-8");
			char[] chars = new char[(int) file.length()];
	        reader.read(chars);
	        games = new String(chars);			
		} catch (IOException e) {
			e.printStackTrace();
		}
        return games;
		
	}

	@Override
	public void writeToFile(String txt, String filename) {
		// TODO Auto-generated method stub
		
	}
	
	
	public static void main(String[] args) {
		// System.out.println("hei");
		GameFileHandler f = new GameFileHandler();
		System.out.println(f.readFromFile("games.txt"));
	}

}

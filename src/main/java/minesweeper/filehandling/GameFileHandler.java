package minesweeper.filehandling;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.NoSuchFileException;
import java.nio.file.Path;
import java.nio.file.Paths;

public class GameFileHandler implements FileHandler {


	@Override
	public String readFromFile(String filename) throws IOException {
        Path path = Paths.get(System.getProperty("user.home"), filename);
        String lines = String.join("\n", Files.readAllLines(path));
        return lines;
	}

	@Override
	public void writeToFile(String txt, String filename) throws IOException {
		Path path = Paths.get(System.getProperty("user.home"), filename);
		Writer writer = new FileWriter(path.toFile(), StandardCharsets.UTF_8);
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

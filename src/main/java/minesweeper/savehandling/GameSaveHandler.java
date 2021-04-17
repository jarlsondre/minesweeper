package minesweeper.savehandling;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class GameSaveHandler implements SaveHandler {

	private final String saveLocation = "user.home";
	private String fileName = "games.txt";


	@Override
	public String loadState() throws IOException {
        Path path = Paths.get(System.getProperty(saveLocation), this.fileName);
        String lines = String.join("\n", Files.readAllLines(path));
        return lines;
	}

	@Override
	public void saveState(String state) throws IOException {
		Path path = Paths.get(System.getProperty(saveLocation), this.fileName);
		Writer writer = new FileWriter(path.toFile(), StandardCharsets.UTF_8);
		writer.write(state);
		writer.close();
	}


}

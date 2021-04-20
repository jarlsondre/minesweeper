package minesweeper.savehandling;

import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class GameSaveHandler implements SaveHandler {

	private final String saveLocation = "user.home";
	private String fileName = "minesweeper_games.txt";


	@Override
	public String loadState() throws IOException {
        Path path = Paths.get(System.getProperty(saveLocation), this.fileName);
        String lines = String.join("\n", Files.readAllLines(path));
        return lines;
	}

	@Override
	public void saveState(final String state) throws IOException {
		Path path = Paths.get(System.getProperty(saveLocation), this.fileName);
		Writer writer = new FileWriter(path.toFile(), StandardCharsets.UTF_8);
		writer.write(state);
		writer.close();
	}

	/**
	 * Metode for å bytte hvilken fil det skal lagres til
	 * @param fileName navnet på filen det lagres til
	 */
	public void setFileName(final String fileName) {
		if(fileName == null || fileName.equals("")) {
			throw new IllegalArgumentException("filename kan ikke være null eller en tom streng");
		}
		this.fileName = fileName;
	}


}

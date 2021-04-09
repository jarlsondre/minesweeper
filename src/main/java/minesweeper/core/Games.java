package minesweeper.core;

import java.io.IOException;
import java.nio.file.NoSuchFileException;
import java.util.*;
import java.util.Map.Entry;

import minesweeper.filehandling.FileHandler;
import minesweeper.filehandling.GameFileHandler;


/**
 * Klasse som tar vare på det beste spillet for alle spillerne 
 * */
public class Games {
	
	// Poengene til hver spiller
	private List<Player> players;
	private FileHandler fileHandler;
	private String fileName = "games.txt";
	
	/**
	 * Konstruktør for game. 
	 * Leser inn tidligere spill fra fil.
	 * */
	public Games() {
		this.players = new ArrayList<>();
		this.fileHandler = new GameFileHandler();
		this.readPreviousGamesFromFile();
	}
	
	/**
	 * Hjelpemetode for å lese inn tidligere spill fra tekstfil
	 * */
	private void readPreviousGamesFromFile() {
		String oldGames = null;
		try {
			oldGames = this.fileHandler.readFromFile(this.fileName);
		} catch (IOException e) {
			System.out.println("test");
		}
		if (oldGames == null) {
			return;
		}
		String[] games = oldGames.split(",");
		for (String game : games) {
			String[] info = game.split(":");
			Player player = new Player(info[0].strip(), Integer.parseInt(info[1].strip()));
			this.players.add(player);
		}
	}
	
	/**
	 * Metode for å lagre alle spillene til fil 
	 * */
	public void saveGames() {
		String txt = "";
		String newline = "";
		// Konstruerer en streng med spillerdata
		for(Player player : this.players) {
			txt += newline + player.getName() +": " + player.getHighScoreTime() + ",";
			newline = "\n";
		}
		try {
			this.fileHandler.writeToFile(txt, this.fileName);
		} catch (IOException e) {
			System.err.println("Lagring av spillet til fil feilet");
			e.printStackTrace();
		}
	}
	
	/**
	 * Registrer et spill for en spiller.
	 * Det er kun det beste spillet som blir lagret
	 * @param username spilleren som har spilt et nytt spill
	 * @param timeElapsed antall sekunder spilleren brukte
	 * */
	public void registerPlay(String username, int timeElapsed) {
		if (timeElapsed < 0) {
			throw new IllegalArgumentException("sec kan ikke være et negativt tall");
		}
		if (username.equals("")) {
			throw new IllegalArgumentException("username kan ikke være en tom streng");
		}
		if (username == null) {
			throw new NullPointerException("username kan ikke være null");
		}
		Player player = new Player(username, timeElapsed);
		// TODO: sjekk om brukeren allerede finnes
        if (this.players.contains(player)) {
        	player = this.players.get(this.players.indexOf(player));
        	if (timeElapsed < player.getHighScoreTime()) {
        		player.setHighScoreTime(timeElapsed);
			}
		}
        else {
        	this.players.add(player);
		}
        this.players.sort(null);
	}
	
	/**
	 * Henter ut alle spillene i synkende rekkelfølge i forhold til poengene
	 * @return En liste med tupler for hvert spill
	 * */
	public Collection<Player> getPlayersResults() {
		List<Player> copiedList = List.copyOf(this.players);
        return copiedList;
	}

}

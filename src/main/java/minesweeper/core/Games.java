package minesweeper.core;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import minesweeper.savehandling.SaveHandler;
import minesweeper.savehandling.GameSaveHandler;


/**
 * Klasse som tar vare på det beste spillet for alle spillerne 
 * */
public class Games {
	
	// Poengene til hver spiller
	private List<Player> players;
	private SaveHandler saveHandler;

	/**
	 * Konstruktør for game. 
	 * Leser inn tidligere spill fra fil.
	 * */
	public Games() {
		this.players = new ArrayList<>();
		this.saveHandler = new GameSaveHandler();
		this.readPreviousGamesFromFile();
	}
	
	/**
	 * Hjelpemetode for å lese inn tidligere spill fra tekstfil
	 * */
	private void readPreviousGamesFromFile() {
		String oldGames;
		try {
			oldGames = this.saveHandler.loadState();
		} catch (IOException e) {
		    // Dersom programmet ikke klarer å lese fra fil så forsøker ikke
			// programmet å legge til tidligere spill
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
			this.saveHandler.saveState(txt);
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
	public void registerPlay(final String username, final int timeElapsed) {
		Player player = new Player(username, timeElapsed);
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

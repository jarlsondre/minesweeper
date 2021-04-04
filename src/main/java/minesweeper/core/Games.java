package minesweeper.core;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import minesweeper.filehandling.FileHandler;
import minesweeper.filehandling.GameFileHandler;


/**
 * Klasse som tar vare på det beste spillet for alle spillerne 
 * */
public class Games {
	
	// Poengene til hver spiller
	private Map<String, String> players;
	private FileHandler fileHandler;
	
	/**
	 * Konstruktør for game. 
	 * Leser inn tidligere spill fra fil.
	 * */
	public Games() {
		this.players = new HashMap<String, String>();
		this.fileHandler = new GameFileHandler();
		this.readOldGames();
	}
	
	/**
	 * Hjelpemetode for å lese inn gamle spill
	 * */
	private void readOldGames() {
		String oldGames = null;
		try {
			oldGames = this.fileHandler.readFromFile("games.txt");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String[] games = oldGames.split(",");
		for(String game : games) {
			String[] info = game.split(":");
			this.players.put(info[0].strip(), info[1].strip());
		}
	}
	
	/**
	 * Metode for å lagre alle spillene til fil 
	 * */
	public void saveGames() {
		String txt = "";
		String newline = "";
		for(Entry<String, String> player : this.players.entrySet()) {
			txt += newline + player.getKey() +": " + player.getValue() + ",";
			newline = "\n";
		}
		try {
			this.fileHandler.writeToFile(txt, "games.txt");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/**
	 * Registrer et spill for en spiller.
	 * Det er kun det beste spillet som blir lagret
	 * @param username spilleren som har spilt et nytt spill
	 * @param sec antall sekunder spilleren brukte
	 * */
	public void registerPlay(String username, int sec) {
		this.players.put(username, String.valueOf(sec));
	}
	
	/**
	 * Henter ut alle spillene i synkende rekkelfølge i forhold til poengene
	 * @return En liste med tupler for hvert spill
	 * */
	public Collection<String[]> getPlayersResults() {
		Collection<String[]> col = new ArrayList<String[]>();
		for(Entry<String, String> player : this.players.entrySet()) {
			String[] s = {player.getKey(), player.getValue()}; 
			col.add(s);
		}
        return col;
	}
	
	public static void main(String[] args) {
		Games g = new Games();
		// test
//		System.out.println(g.getPlayersResults().iterator().next()[0]);
//		System.out.println(g.getPlayersResults().iterator().next()[1]);
		g.registerPlay("Test", 50);
		g.saveGames();
	}
}

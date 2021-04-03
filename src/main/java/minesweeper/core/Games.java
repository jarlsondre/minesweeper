package minesweeper.core;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;


/**
 * Klasse som tar vare på det beste spillet for alle spillerne 
 * */
public class Games {
	
	// Poengene til hver spiller
	private Map<String, Integer> players;
	
	/**
	 * Konstruktør for game. 
	 * Leser inn tidligere spill fra fil.
	 * */
	public Games() {
		this.players = new HashMap<String, Integer>();
		this.readOldGames();
	}
	
	/**
	 * Hjelpemetode for å lese inn gamle spill
	 * */
	private void readOldGames() {
		// TODO
	}
	
	/**
	 * Metode for å lagre alle spillene til fil 
	 * */
	public void saveGame() {
		// TODO save players to file
	}
	
	/**
	 * Legg til en ny spiller
	 * @param username brukernavnet til den nye spilleren
	 * */
	public void addPlayer(String username) {
		
	}
	
	/**
	 * Registrer et spill for en spiller.
	 * Det er kun det beste spillet som blir lagret
	 * @param username spilleren som har spilt et nytt spill
	 * @param sec antall sekunder spilleren brukte
	 * */
	public void registerPlay(String username, int sec) {
		
	}
	
	/**
	 * Henter ut alle spillene i synkende rekkelfølge i forhold til poengene
	 * @return En liste med tupler for hvert spill
	 * */
	public Collection<String[]> getPlayersResults() {
		//TODO
        return null;
	}
}

package minesweeper.savehandling;

import java.io.IOException;

/**
 * Grensesnitt for hvordan spillet skal lagres. Inneholder generelle
 * metoder for innlasting av spill og lagring av spill. Kan implementeres
 * som lokal lagring eller fjernlagring.
 */

public interface SaveHandler {
	
	/**
	 * Laster inn tilstanden til spillet fra lagringsenhet
	 * @return En String som utgjør tilstanden til spillet
	 * @throws IOException Om noe gikk galt under lastingen
	 */
	String loadState() throws IOException;
	
	/**
	 * Lagrer spillet til lagringsenhet
	 * @param state tilstanden til spillet
	 * @throws IOException Om noe gikk galt under lagringen
	 */
	void saveState(String state) throws IOException;

}

package minesweeper.savehandling;

import java.io.IOException;

/**
 * Grensesnitt for hvordan spillet skal lagres. Inneholder generelle
 * metoder for innlasting av spill og lagring av spill. Kan implementeres
 * som lokal lagring eller fjernlagring.
 */

public interface SaveHandler {
	
	String loadState() throws IOException;
	
	void saveState(String state) throws IOException;

}


/*
Kunne ha laget SaveHandler med metoder som loadState og saveStat

testing av save:

- prøve å lagre noe, prøv å laste det inn igjen, sjekk at det er likt
- endre noe, repeat det over, sjekk at alt stemmere
*/

package minesweeper.core;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class GamesTest {

	@Test
	public void testRegisterPlay() {
		Games games = new Games();
		games.registerPlay("Test", 1000);
		Assertions.assertThrows(IllegalArgumentException.class, () -> games.registerPlay("Test", -1));
		Assertions.assertThrows(NullPointerException.class, () -> games.registerPlay("", 5));
		Assertions.assertThrows(NullPointerException.class, () -> games.registerPlay(null, 5));
	}

	@Test
	public void testPlayersResults() {
		Games games = new Games();

		String[] play1 = { "Test1", "20" };
		String[] play2 = { "Test2", "30" };
		String[] play3 = { "Test3", "40" };
		String[] play4 = { "Test4", "50" };

		games.registerPlay(play1[0], Integer.parseInt(play1[1]));
		games.registerPlay(play2[0], Integer.parseInt(play2[1]));
		games.registerPlay(play3[0], Integer.parseInt(play3[1]));
		games.registerPlay(play4[0], Integer.parseInt(play4[1]));
		Collection<String[]> results = games.getPlayersResults();
		Collection<String[]> col = new ArrayList<>(Arrays.asList(play1, play2, play3, play4));
		
		// Teste at results inneholder de fire spillene
		for (String[] play : col) {
			boolean found = false;
			for (String[] res : results) {
				if (this.isEqual(res, play)) {
					found = true;
				}
			}
			Assertions.assertTrue(found);
		}
	}
	
	// Hjelpemetode for å sjekke at to arrays, med to strenger hver, er like
	private boolean isEqual(String[] a1, String[] a2) {
		if(a1[0].equals(a2[0]) && a1[1].equals(a2[1])) {
			return true;
		}
		return false;
	}

}

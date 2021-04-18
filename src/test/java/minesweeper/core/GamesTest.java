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
		Assertions.assertThrows(IllegalArgumentException.class, () -> games.registerPlay("", 5));
		Assertions.assertThrows(IllegalArgumentException.class, () -> games.registerPlay(null, 5));
	}

	@Test
	public void testPlayersResults() {
		Games games = new Games();

		Player player1 = new Player("player1", 20);
		Player player2 = new Player("player2", 30);
		Player player3 = new Player("player3", 40);
		Player player4 = new Player("player4", 50);

		games.registerPlay("player1", 20);
		games.registerPlay("player2", 30);
		games.registerPlay("player3", 40);
		games.registerPlay("player4", 50);

		Collection<Player> results = games.getPlayersResults();
		Collection<Player> col = new ArrayList<>(Arrays.asList(player1, player2, player3, player4));
		
		// Teste at results inneholder de fire spillene
		// og at de ikke har blitt endret
        Assertions.assertTrue(results.containsAll(col));
	}

}

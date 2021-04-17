package minesweeper.core;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class PlayerTest {

    @Test
    public void testConstructor() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            Player player = new Player(null, 60);
        });
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            Player player = new Player("", 60);
        });
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            Player player = new Player("Per", -10);
        });

        // Tester at korrekte parametre fungerer
        Player player1 = new Player("Trym", 0);
        Player player2 = new Player("Tomas", 25);

        // Tester at de riktige verdiene blir satt
        Assertions.assertEquals(player1.getName(), "Trym");
        Assertions.assertEquals(player1.getHighScoreTime(), 0);
        Assertions.assertEquals(player2.getName(), "Tomas");
        Assertions.assertEquals(player2.getHighScoreTime(), 25);
    }

    @Test
    public void testEquals() {
        // Tester to spillere med samme navn men forskjellig tid
        Player player1 = new Player("player1", 60);
        Player player2 = new Player("player1", 90);
        Assertions.assertTrue(player1.equals(player2) && player2.equals(player1));
        Assertions.assertTrue(player1.hashCode() == player2.hashCode());

        // Tester to spillere med forskjellig navn men samme tid
        Player player3 = new Player("player3", 60);
        Player player4 = new Player("player4", 60);
        Assertions.assertFalse(player3.equals(player4) || player4.equals(player3));
        Assertions.assertFalse(player3.hashCode() == player4.hashCode());

    }

    @Test
    public void testCompareTo() {
        Player player1 = new Player("Jarl", 40);
        Player player2 = new Player("Bendik", 50);

        // Sjekker to spillere med forskjellig tid
        Assertions.assertEquals(player1.compareTo(player2), -10);
        Assertions.assertEquals(player2.compareTo(player1), 10);

        // Sjekker to spillere med lik tid
        Player player3 = new Player("Tomas", 50);
        Assertions.assertEquals(player2.compareTo(player3), 0);

    }
}

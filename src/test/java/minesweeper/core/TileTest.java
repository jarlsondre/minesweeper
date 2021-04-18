package minesweeper.core;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class TileTest {
	private Board board;
	
	@BeforeEach
	public void setup() {
		this.board = new Board(10);
	}
	
	@Test
	public void testConstructor() {
		// Sjekker at en BombTile har en bombe
		Tile t = new BombTile(5, 5, this.board);
		Assertions.assertTrue(t.hasBomb());

		// Tester at en SafeTile ikke har en bombe
		t = new SafeTile(5, 5, this.board);
		Assertions.assertFalse(t.hasBomb());
	}
	
	@Test
	public void testGetSurroundingBombAmount() {
		for(Tile t : this.board) {
			if(t instanceof SafeTile) {
				// Sjekker at det går an å hente ut antall bomber og
				// at det ikke er færre enn 0
				SafeTile safeTile = (SafeTile) t;
				Assertions.assertTrue(safeTile.getSurroundingBombAmount() >= 0);
			}
		}
	}
}

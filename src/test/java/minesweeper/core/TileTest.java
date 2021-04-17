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
		Tile t = new BombTile(5, 5, this.board);
		Assertions.assertTrue(t.hasBomb());
		t = new SafeTile(5, 5, this.board);
		Assertions.assertFalse(t.hasBomb());
	}
	
	@Test
	public void testGetSurroundingBombAmount() { // Kunne evt sjekket om det stemmer
		for(Tile t : this.board) {
			if(t instanceof SafeTile) {
				SafeTile safeTile = (SafeTile) t;
					safeTile.getSurroundingBombAmount();
			}
		}
	}
}

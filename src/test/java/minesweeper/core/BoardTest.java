package minesweeper.core;

import java.util.Iterator;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class BoardTest {

	@Test
	public void testConstructor() {
		// Sjekker en normal verdi
		Board board = new Board(13);
		Assertions.assertEquals(13, board.getSize());
		for (int i = 1; i < 14; i++) {
			// Sjekker at man kan hente ut nr. 13 på hver rad
			// for at dimensjonen ikke skal være skjevfordelt
			board.getTile(13, i);
		}
		// Sjekker et kant-tilfelle
		board = new Board(2);
		Assertions.assertEquals(2, board.getSize());
		Assertions.assertThrows(IllegalArgumentException.class, () -> {
			new Board(-10);
		});
		Assertions.assertThrows(IllegalArgumentException.class, () -> {
			new Board(1);
		});
		Assertions.assertThrows(IllegalArgumentException.class, () -> {
			new Board(0);
		});
	}

	@Test
	public void testGetTile() {
		Board board = new Board(10);
		Assertions.assertThrows(IllegalArgumentException.class, () -> {
			board.getTile(0, 0);
		});
		Assertions.assertThrows(IllegalArgumentException.class, () -> {
			board.getTile(11, 11);
		});
		Assertions.assertThrows(IllegalArgumentException.class, () -> {
			board.getTile(-1, -1);
		});
		// Sjekker at man kan hente ut hjørnene
		board.getTile(1, 1);
		board.getTile(10, 10);
	}

	@Test
	public void testBoardIterator() {
		Board board = new Board(2);
		Iterator<Tile> it = board.iterator();

		// Sjekker at hasNext ikke påvirker iteratoren.
		for (int i = 0; i < 5; i++) {
			Assertions.assertTrue(it.hasNext());
		}

		// Sjekker hasNext etter å ha gått gjennom hele brettet.
		for (int i = 0; i < 4; i++) {
			Assertions.assertTrue(it.hasNext());
			it.next();
		}
		Assertions.assertFalse(it.hasNext());

		// Sjekker om iteratoren itererer gjennom riktig antall tiles.
		board = new Board(10);
		it = board.iterator();
		int counter = 0;
		while (it.hasNext()) {
			it.next();
			counter += 1;
		}
		Assertions.assertEquals(10 * 10, counter);
	}

	@Test
	public void testAmountOfBombs() {
		Board board = new Board(40);
		int counter = 0;
		for (Tile t : board) {
			if (t instanceof BombTile) {
				counter += 1;
			}
		}
		Assertions.assertEquals( board.getSize()*board.getSize()*(0.12), counter);
	}

	@Test
	public void testTimer() {
		Board board = new Board(10);
		// Tester at man ikke kan kalle startTimer når tidtakingen har startet
		board.startTimer();
		Assertions.assertThrows(IllegalStateException.class, () -> {
			board.startTimer();
		});

		int timePassed = board.stopTimer();
		Assertions.assertFalse(timePassed < 0);

		// Tester at man ikke kan kalle stopTimer når tidtakingen ikke har startet
		Assertions.assertThrows(IllegalStateException.class, () -> {
			board.stopTimer();
		});
	}

	

	@Test
	public void testTileListeners() {
		Board board = new Board(10);
		SafeTile tile = null;
		// Åpner alle tile ssom ikke har bomber rundt seg
		// og sjekker at alle tiles rundt disse åpnes automatisk
		for (Tile t : board) {
			if (t instanceof SafeTile && !t.isOpened() && ((SafeTile) t).getSurroundingBombAmount() == 0) {
				tile = (SafeTile) t;
				tile.open();
				this.checkListenersOpen(tile, board);
			}
		}
	}


	// Hjelpemetode til testTileListeners
	private void checkListenersOpen(Tile tile, Board board) {
		for (int i = -1; i < 2; i++) {
			for (int j = -1; j < 2; j++) {
				// Sjekker om alle tiles rundt 'tile' har blitt åpnet slik de skal
				if (tile.x + j > 0 && tile.x + j < board.getSize() + 1 && tile.y + i > 0
						&& tile.y + i < board.getSize() + 1) {
					Assertions.assertTrue(board.getTile(tile.x + j, tile.y + i).isOpened());
				}
			}
		}
	}


}
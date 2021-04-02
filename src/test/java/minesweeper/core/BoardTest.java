package minesweeper.core;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.File;
import java.util.Iterator;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;


public class BoardTest {
	
	@Test
	public void testConstructor() {
		Board board = new Board(13);
		Assertions.assertEquals(13, board.getSize());
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
    	board.getTile(1, 1);
    	board.getTile(10, 10);
    }
    
    @Test
    public void testBoardIterator() {
    	Board board = new Board(2);
    	Iterator<Tile> it = board.iterator();
    	
    	// Sjekker at hasNext ikke påvirker iteratoren.
    	for(int i = 0; i < 5; i++) {
    		Assertions.assertTrue(it.hasNext());    		
    	}
    	
    	// Sjekker hasNext etter å ha gått gjennom hele brettet.
    	for(int i = 0; i < 4; i++) {
    		Assertions.assertTrue(it.hasNext());
    		it.next();
    	}
    	Assertions.assertFalse(it.hasNext());
    	
    	// Sjekker om iteratoren itererer gjennom riktig antall tiles.
    	board = new Board(10);
    	it = board.iterator();
    	int counter = 0;
    	while(it.hasNext()) {
    		it.next();
    		counter += 1;
    	}
    	Assertions.assertEquals(10*10, counter);
    }
    
    @Test
    public void testAmountOfBombs() {
    	Board board = new Board(40);
    	int counter = 0;
    	for(Tile t : board) {
    		if(t instanceof BombTile) {
    			counter += 1;
    		}
    	}
    	double p = ((double) counter)/(board.getSize() * board.getSize());
    	Assertions.assertTrue(p > 0.1 && p < 0.3);
    }
    
    @Test
    public void testTileListeners() {
    	Board board = new Board(10);
    	SafeTile tile = null;
    	for(Tile t : board) {
    		if(t instanceof SafeTile && !t.isOpened() && ((SafeTile) t).getSurroundingBombAmount() == 0) {
    			tile = (SafeTile) t;
    			tile.open();
    	    	this.checkListenersOpen(tile, board);
    		}
    	}  	
    }
    
    private void checkListenersOpen(Tile tile, Board board) {
    	for (int i = -1; i < 2; i++) {
			for (int j = -1; j < 2; j++) {
				if (tile.x + j > 0 && tile.x + j < board.getSize() + 1 && tile.y + i > 0 && tile.y + i < board.getSize() + 1
						&& !(i == 0 && j == 0) && board.getTile(tile.x + j, tile.y + i) instanceof SafeTile
						&& ((SafeTile) board.getTile(tile.x + j, tile.y + i)).getSurroundingBombAmount() == 0) {
					SafeTile t = (SafeTile) board.getTile(tile.x + j, tile.y + i);
					assertTrue(board.getTile(tile.x + j, tile.y + i).isOpened());
				}
			}
		}
    }
}
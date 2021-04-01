package minesweeper.core;

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
    	System.out.println(p);
    	Assertions.assertTrue(p > 0.1 && p < 0.3);
    }
}
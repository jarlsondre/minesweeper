package minesweeper.core;

import java.util.Iterator;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;


public class BoardTest {
	
	@Test
	public void testConstructor() {
		Board board = new Board(13);
		Assertions.assertEquals(13, board.getSize());
		board = new Board(0);
		Assertions.assertEquals(0, board.getSize());
		Assertions.assertThrows(IllegalStateException.class, () -> {
            new Board(-10);
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
            board.getTile(100, 100);
        });
        try {
        	board.getTile(1, 1);
        	board.getTile(10, 10);
        }catch (Exception e) {
        	Assertions.fail("Får ikke hentet tiles på brettet.");
        }
    }
    
    @Test
    public void testBoardIterator() {
    	Board board = new Board(10);
    	Iterator<Tile> it = board.iterator();
    	int counter = 0;
    	while(it.hasNext()) {
    		it.next();
    		counter += 1;
    	}
    	Assertions.assertEquals(10*10, counter);
    	board = new Board(15);
    	it = board.iterator();
    	counter = 0;
    	while(it.hasNext()) {
    		it.next();
    		counter += 1;
    	}
    	Assertions.assertEquals(15*15, counter);
    }
    
    @Test
    public void testAmountOfBombs() {
    	Board board = new Board(15);
    	int counter = 0;
    	for(Tile t : board) {
    		if(t instanceof BombTile) {
    			counter += 1;
    		}
    	}
    	double p = ((double) counter)/(board.getSize() * board.getSize());
    	// TODO Fjern den under her
    	System.out.println(p);
    	Assertions.assertTrue(p > 0.1 && p < 0.3);
    }
}
package minesweeper.core;

import java.util.ArrayList;
import java.util.Collection;

public class SafeTile extends Tile {
	
	private Collection<Tile> listeners = new ArrayList<Tile>();
	
	public SafeTile(int x, int y, Board board) {
		super(x, y, board);
	}
	
	public boolean hasBomb() {
		return false;
	}
	
	public int getSurroundingBombAmount() {
		return null;
	}

}

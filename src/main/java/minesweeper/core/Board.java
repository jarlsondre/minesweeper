package minesweeper.core;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Board implements Iterable<Tile>{
	
	private int size;
	
	private List<List<Tile>> tileList = new ArrayList<List<Tile>>();
	
	public Board(int size) {
		this.size = size;
		this.makeBoard();
	}
	
	private void makeBoard() {
	}
	
	public Tile getTile(int x, int y) {
		return null; 	
	}

	@Override
	public Iterator<Tile> iterator() {
		// TODO Auto-generated method stub
		Iterator<Tile> it = new Iterator<Tile>() {
			
			private int currentX = 1;
			private int currentY = 1;
			

			@Override
			public boolean hasNext() {
				// TODO Auto-generated method stub
				return this.currentY < size + 1;
			}

			@Override
			public Tile next() {
				// TODO Auto-generated method stub
				Tile nextTile = getTile (this.currentX, this.currentY);
				if(this.currentX == size) {
					this.currentY += 1;
					this.currentX = 1;
				}
				this.currentX += 1;
				return nextTile;
			}
		};
		return it;
	}
	
	
	
	
	
}

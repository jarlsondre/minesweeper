package minesweeper.core;

public class BombTile extends Tile {

	public BombTile(int x, int y, Board board) {
		super(x, y, board);
	}
	
	public boolean hasBomb() {
		return true;
	}
}

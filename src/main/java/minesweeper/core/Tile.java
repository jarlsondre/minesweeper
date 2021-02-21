package minesweeper.core;

public abstract class Tile {
	
	protected int x;
	protected int y;
	
	protected Board board;
	
	public Tile(int x, int y, Board board) {
		this.x = x;
		this.y = y;
		this.board = board;
	}
	
	public int getX() {
		return this.x;
	}
	
	public int getY() {
		return this.y;
	}
	
	public abstract boolean hasBomb();

}

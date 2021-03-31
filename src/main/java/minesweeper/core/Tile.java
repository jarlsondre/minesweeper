package minesweeper.core;

/**
 * Abstrakt klasse som representerer en Tile, altså en rute på brettet. Denne
 * er koblet til et brett og har en boolsk verdi som forteller om den
 * inneholder en bombe eller ikke.
 */
public abstract class Tile {

	protected int x;
	protected int y;

	protected Board board;

	/**
	 * Konstruktør før å lage Tiles.
	 * @param x x-veriden til Tilen.
	 * @param y y-veriden til Tilen
	 * @param board Brettet Tilen hører til
	 */
	public Tile(final int x, final int y, final Board board) {
		this.x = x;
		this.y = y;
		this.board = board;
	}

	/**
	 * Metode som gir x-verdien til en Tile.
	 * @return x-verdien til Tilen
	 */
	public int getX() {
		return this.x;
	}

	/**
	 * Metode som gir y-verdien til en Tile.
	 * @return y-verdien til Tilen
	 */
	public int getY() {
		return this.y;
	}

	/**
	 * Metode som forteller om en Tile inneholder en bombe eller ikke
	 * @return boolsk verdi basert på om det er bombe eller ikke
	 */
	public abstract boolean hasBomb();

}

package minesweeper.core;

/**
 * Klasse for å representere Tiles som skal inneholde bomber. Denne klassen
 * arver fra Tile.
 */
public class BombTile extends Tile {

	/**
	 * Konstruktør for å lage en BombTile.
	 * @param x x-verdien til Tilen
	 * @param y y-verdien til Tilen
	 * @param board Brettet Tilen hører til
	 */
	public BombTile(final int x, final int y, final Board board) {
		super(x, y, board);
	}

	/**
	 * Metode som forteller om Tilen inneholder en bombe eller ikke.
	 * I dette tilfellet vil den alltid returnere true da dette er
	 * en BombTile.
	 * @return alltid true siden det er en BombTile
	 *
	 */
	public boolean hasBomb() {
		return true;
	}
}

package minesweeper.core;

import java.util.ArrayList;
import java.util.Collection;

/**
 * Klasse for å representere Tiles som ikke skal inneholde bomber. Denne klassen
 * arver fra Tile.
 */
public class SafeTile extends Tile {

	private Collection<Tile> listeners = new ArrayList<Tile>();

	/**
	 * Konstruktør før å lage en SafeTile.
	 * @param x x-verdien til Tilen
	 * @param y y-verdien til Tilen
	 * @param board Brettet Tilen hører til
	 */
	public SafeTile(final int x, final int y, final Board board) {
		super(x, y, board);
	}

	/**
	 * Metode som forteller om Tilen inneholder en bombe eller ikke.
	 * Denne vil alltid være false siden dette er en SafeTile,
	 * altså en Tile uten en bombe.
	 * @return alltid false siden det er en SafeTile
	 */
	public boolean hasBomb() {
		return false;
	}

	/**
	 * Metode som gir ut antall bomber rundt Tilen.
	 * Denne sjekker alle 8 retninger rundt tilen og gir tilbake antallet.
	 * @return et heltall, antallet bomber rundt Tilen
	 */
	public int getSurroundingBombAmount() {
		int bombs = 0;
		for(int i = 0; i < 3; i++) {
			try {
				if(this.board.getTile(this.x - 1 + i, this.y - 1) instanceof BombTile) {
					bombs += 1;
				}				
			} catch (IllegalArgumentException e) {continue;}
		}
		for(int i = 0; i < 3; i++) {
			try {
				if(this.board.getTile(this.x - 1 + i, this.y - 1) instanceof BombTile) {
					bombs += 1;
				}				
			} catch (IllegalArgumentException e) {continue;}
		}
		if(this.x - 1 > 0 && board.getTile(this.x - 1, this.y) instanceof BombTile) {
			bombs+= 1;
		}
		if(this.x + 1 < this.board.getSize() + 1 && board.getTile(this.x - 1, this.y) instanceof BombTile) {
			bombs += 1;
		}
		return bombs;
	}

}

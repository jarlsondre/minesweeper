package minesweeper.core;

import java.util.ArrayList;
import java.util.Collection;

/**
 * Klasse for å representere Tiles som ikke skal inneholde bomber. Denne klassen
 * arver fra Tile.
 */
public class SafeTile extends Tile implements AdjacentListener {

	private Collection<SafeTile> adjacentSafeTiles = new ArrayList<SafeTile>();

	/**
	 * Konstruktør før å lage en SafeTile.
	 * 
	 * @param x     x-verdien til Tilen
	 * @param y     y-verdien til Tilen
	 * @param board Brettet Tilen hører til
	 */
	public SafeTile(final int x, final int y, final Board board) {
		super(x, y, board);
	}

	/**
	 * Metode som forteller om Tilen inneholder en bombe eller ikke. Denne vil
	 * alltid være false siden dette er en SafeTile, altså en Tile uten en bombe.
	 * 
	 * @return alltid false siden det er en SafeTile
	 */
	public boolean hasBomb() {
		return false;
	}

	/**
	 * Metode som gir ut antall bomber rundt Tilen. Denne sjekker alle 8 retninger
	 * rundt tilen og gir tilbake antallet.
	 * 
	 * @return et heltall, antallet bomber rundt Tilen
	 */
	public int getSurroundingBombAmount() {
		int bombs = 0;
		for (int i = -1; i < 2; i++) {
			for (int j = -1; j < 2; j++) {
				if (this.x + j > 0 && this.x + j < this.board.getSize() + 1 && this.y + i > 0
						&& this.y + i < this.board.getSize() + 1
						&& (this.board.getTile(this.x + j, this.y + i) instanceof BombTile)) {
					bombs += 1;
				}
			}
		}
		return bombs;
	}

	/**
	 * Metode som åpner tilen og varlser lytterne.
	 */
	@Override
	public void open() {
		super.open();
		if(this.getSurroundingBombAmount() == 0) {
			fireTileOpened();			
		}
	}

	/**
	 * Metode som blir kalt av en nabo som denne tilen lytter på.
	 * Naboen varsler om at denne tilen skal åpnes
	 */
	@Override
	public void adjacentOpened() {
		int amount = this.getSurroundingBombAmount();
		if (!this.opened && amount == 0) {
			this.open();
			return;
		}
		if(!this.opened) {
			super.open();
		}
	}

	/**
	 * Metode som legger til lyttere
	 * @param SafeTile tile som skal lytte på dette objektet
	 */
	public void addListener(final SafeTile tile) {
		this.adjacentSafeTiles.add(tile);
	}

	/**
	 * Hjelpemetode som varsler alle lyttere om at denne tilen har blitt åpnet.
	 */
	private void fireTileOpened() {
		for (SafeTile t : this.adjacentSafeTiles) {
			t.adjacentOpened();
		}
	}

	@Override
	public String toString() {
		return Integer.toString(this.getSurroundingBombAmount());
	}

}

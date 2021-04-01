package minesweeper.core;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Klasse som skal representere brettet. Dette skal inneholde
 * en liste med Tiles.
 */
public class Board implements Iterable<Tile> {
	private final int size;
	private List<List<Tile>> tileList = new ArrayList<List<Tile>>();

	/**
	 * Konstruktør til brettet.
	 * @param size størrelsen på brettet i en dimensjon.
	 */
	public Board(int size) {
		this.size = size;
		this.makeBoard();
	}

	/**
	 * Metode for å lage brettet.
	 */
	private void makeBoard() {
	}
	
	/**
	 * Metode som retunerer størrelsen på brettet.
	 * @return Størrelsen på brettet i en dimensjon.
	 * */
	public int getSize() {
		return this.tileList.size();
	}

	/**
	 * Metode for å hente ut hvilken Tile som er på en gitt posisjon.
	 * @param x x-posisjonen som skal sjekkes
	 * @param y y-posisjonen som skal sjekkes
	 * @return tilen som ble funnet
	 */
	public Tile getTile(final int x, final int y) {
		// Merk: Legg til validering
		return null;
	}

	/**
	 * Iterator som blir brukt for å iterere gjennom brettet.
	 * Brettet blir iterert gjennom rad for rad fra (1, 1)
	 * til (size, size)
	 * @return Iteratoren som brukes til å iterere
	 */
	@Override
	public Iterator<Tile> iterator() {
		// TODO Auto-generated method stub
		Iterator<Tile> it = new Iterator<Tile>() {
			private int currentX = 1;
			private int currentY = 1;

			/**
			 * Sjekker om brettet har flere tiles å iterere gjennom
			 * @return boolsk verdi som forteller om det finnes flere tiles
			 */
			@Override
			public boolean hasNext() {
				// TODO Auto-generated method stub
				return this.currentY < size + 1;
			}

			/**
			 * Metode som gir ut neste Tile i brettet. Itererer rad for rad.
			 * @return neste Tile
			 */
			@Override
			public Tile next() {
				// TODO Auto-generated method stub
				Tile nextTile = getTile(this.currentX, this.currentY);
				if (this.currentX == size) {
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

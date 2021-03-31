package minesweeper.core;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Random;

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
		for (int i = 0; i < size; i++) {
			List<Tile> temp = new ArrayList<Tile>();
			for (int j = 0; j < size; j++) {
				Random rand = new Random();
				float rand_num = rand.nextFloat();
				if (rand_num < 0.2) {
					temp.add(new BombTile(i+1, j+1, this));
				}
				else {
					temp.add(new SafeTile(i+1, j+1, this));
				}
			}
			this.tileList.add(temp);
		}
	}

	/**
	 * Metode for å hente ut hvilken Tile som er på en gitt posisjon.
     * Kaster unntak dersom x eller y er utenfor brettet.
	 * @param x x-posisjonen som skal sjekkes
	 * @param y y-posisjonen som skal sjekkes
	 * @return tilen som ble funnet
	 */
	public Tile getTile(final int x, final int y) {
		if (x < 1 || x > this.size || y < 1 || y > this.size) {
			throw new IllegalArgumentException("Kan ikke hente ut Tiles utenfor brettet");
		}
		return this.tileList.get(y-1).get(x-1);
	}

	@Override
	public String toString() {
		String temp = "";
		for (List<Tile> row : this.tileList) {
			temp += row.toString() + "\n";
		}
		return temp;
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
				else {
					this.currentX += 1;					
				}
				return nextTile;
			}
		};
		return it;
	}
}

package minesweeper.core;

import java.util.*;

/**
 * Klasse som skal representere brettet. Dette skal inneholde en liste med
 * Tiles.
 */
public class Board implements Iterable<Tile> {
	private final int size;
	private final List<List<Tile>> tileList = new ArrayList<List<Tile>>();

	/**
	 * Konstruktør til brettet.
	 * 
	 * @param size størrelsen på brettet i en dimensjon.
	 */
	public Board(int size) {
		if (size < 2) {
			throw new IllegalArgumentException("Brettet kan ikke ha mindre størrelse enn 2");
		}
		this.size = size;
		this.makeBoard();
		for (Tile t : this) {
			if (t instanceof SafeTile) {
				this.addListenersToTile((SafeTile) t);
			}
		}
	}

	/**
	 * Hjelpemetode for å lage brettet.
	 */
	private void makeBoard() {

		// Initialiserer en 2d-liste med bare 0
		List<List<Integer>> bombMap = new ArrayList();
		Collections.nCopies(size, new ArrayList(Collections.nCopies(size, 0))).forEach((list) -> bombMap.add(new ArrayList(list)));

		// Setter antall (12% skal være bomber)
		int bombsNotPlaced = (int) (size*size*(0.12));
		while (bombsNotPlaced != 0) {
			Random random = new Random();
			int rand_x = random.nextInt(size);
			int rand_y = random.nextInt(size);
			if (bombMap.get(rand_y).get(rand_x) == 0) {
				bombMap.get(rand_y).set(rand_x, 1);
				bombsNotPlaced -= 1;
			}
		}



		for (int i = 0; i < size; i++) {
			List<Tile> temp = new ArrayList<Tile>();
			for (int j = 0; j < size; j++) {
				if (bombMap.get(i).get(j) == 1) {
					temp.add(new BombTile(j + 1, i + 1, this));
				} else {
					temp.add(new SafeTile(j + 1, i + 1, this));
				}
			}
			this.tileList.add(temp);
		}
	}

	/**
	 * Hjelpemetode som legger til alle lytterne tilhørende en tile.
	 * @param tile tilen som skal få tildelt lyttere
	 * */
	private void addListenersToTile(SafeTile tile) {
		for (int i = -1; i < 2; i++) {
			for (int j = -1; j < 2; j++) {
				if (tile.x + j > 0 && tile.x + j < this.getSize() + 1 && tile.y + i > 0 && tile.y + i < this.getSize() + 1
						&& !(j == 0 && i == 0) && this.getTile(tile.x + j, tile.y + i) instanceof SafeTile) {
					tile.addListener((SafeTile) this.getTile(tile.x + j, tile.y + i));
				}
			}
		}
	}

	/**
	 * Metode som retunerer størrelsen på brettet.
	 * 
	 * @return Størrelsen på brettet i en dimensjon.
	 */
	public int getSize() {
		return this.tileList.size();
	}
	
	/**
	 * Metode som gir ut antall SafeTiles brettet inndeholder.
	 * @return Antall SafeTiles
	 * */
	public int getSafeTilesAmount() {
		int counter = 0;
		for(Tile t : this) {
			if(t instanceof SafeTile) {
				counter += 1;
			}
		}
		return counter;
	}

	/**
	 * Metode for å hente ut hvilken Tile som er på en gitt posisjon. Kaster unntak
	 * dersom x eller y er utenfor brettet.
	 * 
	 * @param x x-posisjonen som skal sjekkes
	 * @param y y-posisjonen som skal sjekkes
	 * @return tilen som ble funnet
	 */
	public Tile getTile(final int x, final int y) {
		if (x < 1 || x > this.size || y < 1 || y > this.size) {
			String s = "(" + x + ", " + y + ")";
			throw new IllegalArgumentException("Kan ikke hente ut til på " + s + ", tilen ligger utenfor brettet.");
		}
		return this.tileList.get(y - 1).get(x - 1);
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
	 * Iterator som blir brukt for å iterere gjennom brettet. Brettet blir iterert
	 * gjennom rad for rad fra (1, 1) til (size, size)
	 * 
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
			 * 
			 * @return boolsk verdi som forteller om det finnes flere tiles
			 */
			@Override
			public boolean hasNext() {
				// TODO Auto-generated method stub
				return this.currentY < size + 1;
			}

			/**
			 * Metode som gir ut neste Tile i brettet. Itererer rad for rad.
			 * 
			 * @return neste Tile
			 */
			@Override
			public Tile next() {
				// TODO Auto-generated method stub
				Tile nextTile = getTile(this.currentX, this.currentY);
				if (this.currentX == size) {
					this.currentY += 1;
					this.currentX = 1;
				} else {
					this.currentX += 1;
				}
				return nextTile;
			}
		};
		return it;
	}
}

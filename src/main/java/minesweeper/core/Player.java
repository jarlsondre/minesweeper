package minesweeper.core;

import java.util.Objects;

public class Player implements Comparable<Player> {

    private int highScoreTime;
    private String name;

    /**
     * Konstruktør som lager en spiller med rekordtid og navn
     * @param name navnet til spilleren
     * @param highScoreTime spillerens beste tid
     */
    public Player(final String name, final int highScoreTime) {
        if (name == null) {
            throw new IllegalArgumentException("Navn kan ikke være null");
        }
        if (name.equals("")) {
            throw new IllegalArgumentException("Navn kan ikke være en tom streng");
        }
        this.setHighScoreTime(highScoreTime);
        this.name = name;   
    }

    /**
     * Metode som returnerer spillerens beste tid
     * @return En int som er spillerens beste tid
     */
    public int getHighScoreTime() {
        return this.highScoreTime;
    }

    /**
     * Metode som returnerer spillerens navn
     * @return En string som representerer spillerens navn
     */
    public String getName() {
        return this.name;
    }

    /**
     * Metode som setter spillerens beste tid
     * @param highScoreTime En int som utgjør spillerens beste tid
     */
    public void setHighScoreTime(final int highScoreTime) {
    	if (highScoreTime < 0) {
            throw new IllegalArgumentException("HighScoreTime kan ikke være negativ");
        }
        this.highScoreTime = highScoreTime;
    }

    /**
     * Metode som returnerer om Object o er lik dette objektet
     * @return En boolean som utgjør om Object o er lik denne spilleren
     */
    @Override
    public boolean equals(final Object o) {
       if (!(o instanceof Player) || o == null) {
           return false;
       }
       return ((Player) o).getName().equals(this.getName());
    }

    /**
     * Metode som returnerer en hashcode for spilleren
     */
    @Override
    public int hashCode() {
        return Objects.hash(this.name);
    }

    /**
     * Metode som sammenlikner denne spilleren med Player o
     * og returner om Player o er større, lik eller mindre
     * @return Om Player o er større, lik eller mindre enn dette objektet
     */
	@Override
	public int compareTo(final Player o) {
    	if (o == null) {
    		throw new NullPointerException("Objektet som er forsøkt sammenlignet er null");
    	}
    	return this.highScoreTime - ((Player) o).getHighScoreTime();
    }
}

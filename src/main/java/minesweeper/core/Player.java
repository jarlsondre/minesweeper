package minesweeper.core;

public class Player implements Comparable {

    private int highScoreTime;
    private String name;

    public Player(String name, int highScoreTime) {
        this.name = name;
        this.highScoreTime = highScoreTime;
    }

    public int getHighScoreTime() {
        return this.highScoreTime;
    }

    public String getName() {
        return this.name;
    }

    public void setHighScoreTime(int highScoreTime) {
        this.highScoreTime = highScoreTime;
    }

    @Override
    public boolean equals(Object o) {
       if (!(o instanceof Player) || o == null) {
           return false;
       }
       return ((Player) o).getName().equals(this.getName());
    }


    @Override
    public int compareTo(Object o) {
        if (!(o instanceof Player)) {
            throw new ClassCastException("Kan ikke sammenligne en Player med et objekt som ikke er av typen Player");
        }
        if (o == null) {
            throw new NullPointerException("Objektet som er forsøkt sammenlignet er null");
        }
        return this.highScoreTime - ((Player) o).getHighScoreTime();
    }
}

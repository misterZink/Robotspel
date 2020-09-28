package spel.robots;

public class Zebra extends Robot implements Moveable{
    private boolean isDead;

    /**
     * Default konstruktor, Zebran lever när man skapar objektet
     */
    public Zebra() {
        super.displaySymbol = 'Z';
        isDead = false;
    }

    /**
     * Getter, returnerar boolean isDead till den som anropar metoden
     * @return
     */
    public boolean isDead() {
        return isDead;
    }

    /**
     * Setter, sätter värdet på isDead
     * @param dead
     */
    public void setDead(boolean dead) {
        isDead = dead;
    }


    public boolean move() {
        return true;
    }
}


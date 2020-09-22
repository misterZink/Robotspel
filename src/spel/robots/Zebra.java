package spel.robots;

public class Zebra extends Robot {
    private Boolean isDead;


    //deafult konstruktor, Zebran när man skapar objektet
    public Zebra() {
        isDead=false;
    }
    // getter returnera isdead till den som anropar metoden.
    public boolean isDead() {
        return isDead;
    }
    //setters sätter värdet på is dead
    public void setDead(boolean dead) {
        isDead=dead;
    }
}


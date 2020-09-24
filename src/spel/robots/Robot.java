package spel.robots;

import spel.Robotspel;

import java.util.Random;

// Klasses som geopard och zebra ärver ifrån
abstract public class Robot {
    private int positionX;
    private int positionY;
    protected char displaySymbol;
    private int antalSteg;
    Direction r;

    /**
     * Börjar utan diagnonal riktning för att hålla det simpelt till en början.
     * Den behöver röra sig i i vektorn som ligger i spelplanen tror jag?
     */

    enum Direction{
        UP,
        DOWN,
        RIGHT,
        LEFT;

    }

    /**
     * Constructor
     */
    public Robot()
    {
        this.positionX = 0;
        this.positionY = 0;
        this.displaySymbol = 'O';
        this.antalSteg = 1;
        decideDirection();
    }

    /**
     * Getters
     * @return
     */
    public int getPositionX()
    {
        return getPositionX();
    }
    public int getPositionY()
    {
        return getPositionY();
    }
    public int getAntalSteg()
    {
        return getAntalSteg();
    }
    public char getDisplaySymbol()
    {
        return displaySymbol;
    }


    public Direction getDirection()
    {
        return this.r;
    }

    /**
     * Setters
     */
    public void setPositionX(int positionX)
    {
        this.positionX = positionX;
    }
    public void setPositionY(int positionY)
    {
        this.positionY = positionY;
    }
    public void setAntalSteg(int antalSteg)
    {
        this.antalSteg = 1;
    }


    public void decideDirection()
    {
        Random rand = new Random();
        final int randNum = rand.nextInt(3);

        switch (randNum) {
            case 0 -> r = Direction.DOWN;
            case 1 -> r = Direction.UP;
            case 2 -> r = Direction.LEFT;
            case 3 -> r = Direction.RIGHT;
        }
    }
    public void move()
    {

    }
}

package spel.robots;

import spel.Robotspel;
import spel.robots.directions.Direction;

import java.util.Random;

// Klasses som geopard och zebra ärver ifrån
abstract public class Robot {
    private int positionX;
    private int positionY;
    protected char displaySymbol;
    private int antalSteg;
    private Direction direction;
  
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
        return this.direction;
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

    Riktning r = Riktning.DOWN;

    public void decideDirection()
    {
        Random rand = new Random();
        final int randNum = rand.nextInt(3);

        switch (randNum) {
            case 0 -> direction = Direction.DOWN;
            case 1 -> direction = Direction.UP;
            case 2 -> direction = Direction.LEFT;
            case 3 -> direction = Direction.RIGHT;
        }
    }
    public void move()
    {

    }
}

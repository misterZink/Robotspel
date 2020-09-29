package spel.robots;

import spel.Robotspel;
import spel.robots.directions.Direction;

import java.util.Random;

/**
 *  Klasses som geopard och zebra ärver ifrån
 */
abstract public class Robot{
    private int positionX;
    private int positionY;
    protected char displaySymbol;
    private int antalSteg;
    private Direction direction;

    protected Robot robotTarget;

    public void setRobotTarget(Robot robot) {
        this.robotTarget = robot;
    }

    /**
     * Constructor e
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
     * Denna metoden ska köras varje runda/update för spelet.
     * Här ska data uppdateras beroende på conditions.
     * T.ex: Om trött antalSteg = 0. Else antalSteg = 1;
     */
    public void update() {
        decideDirection();
    }

    /**
     * Getters
     * @return
     */
    public int getPositionX()
    {
        return this.positionX;
    }
    public int getPositionY()
    {
        return this.positionY;
    }
    public int getAntalSteg()
    {
        return this.antalSteg;
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
        this.antalSteg = antalSteg;
    }

    /**
     * tillger roboten en slumpis rikting
     */
    public void decideDirection()
    {
        Random rand = new Random();
        final int randNum = rand.nextInt(4);

        switch (randNum) {
            case 0 -> direction = Direction.DOWN;
            case 1 -> direction = Direction.UP;
            case 2 -> direction = Direction.LEFT;
            case 3 -> direction = Direction.RIGHT;
        }
    }




}

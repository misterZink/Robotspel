package spel.robots;

<<<<<<< Updated upstream
=======
import spel.Robotspel;
import spel.robots.directions.Direction;

import java.util.Random;

>>>>>>> Stashed changes
// Klasses som geopard och zebra ärver ifrån
abstract public class Robot {
    private int positionX;
    private int positionY;
    protected char displaySymbol;
    private int antalSteg;

    /**
     * Börjar utan diagnonal riktning för att hålla det simpelt till en början.
     * Den behöver röra sig i i vektorn som ligger i spelplanen tror jag?
     */

<<<<<<< Updated upstream
    enum Riktning{
        UP,
        DOWN,
        RIGHT,
        LEFT;
    }


=======
>>>>>>> Stashed changes
    /**
     * Constructor
     */
    public Robot()
    {
        this.positionX = 0;
        this.positionY = 0;
        this.displaySymbol = 'O';
        this.antalSteg = 1;
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


    /**
     * Riktning med enums
     */
<<<<<<< Updated upstream
    Riktning r = Riktning.DOWN;
=======
    Direction r = Direction.UP;

    public void decideDirection()
    {
        Random rand = new Random();
        final int randNum = rand.nextInt(3);
>>>>>>> Stashed changes

    public void move()
    {
        r = Riktning.UP;
    }
}

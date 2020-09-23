package spel.robots;

// Klasses som geopard och zebra ärver ifrån
abstract public class Robot {
    private int positionX;
    private int positionY;
    char displaySymbol;
    private int antalSteg;

    /**
     * Börjar utan diagnonal riktning för att hålla det simpelt till en början.
     * Den behöver röra sig i i vektorn som ligger i spelplanen tror jag?
     */

    enum Riktning{
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
    Riktning r = Riktning.DOWN;

    public void move()
    {
        r = Riktning.UP;
    }
}

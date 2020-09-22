package spel.robots;

// Klasses som geopard och zebra ärver ifrån
public class Robot {
    private int positionX;
    private int positionY;
    char displaySymbol;
    private int antalSteg;

    // Börjar utan diagnonal riktning för att hålla det simpelt till en början.
    // Den behöver röra sig i i vektorn som ligger i spelplanen tror jag?
    enum riktning{
        UP,
        DOWN,
        RIGHT,
        LEFT;
    }


    //Constructor
    public Robot()
    {

    }

    //Getter
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

    //Setter
    public void setPositionX()
    {
        this.setPositionX();
    }
    public void setPositionY()
    {

    }
    public void setAntalSteg()
    {

    }

    public void bestämAntalSteg()
    {
        int antalSteg = 1;
    }

    //Vet inte varför R och L inte funkar?
    public void move()
    {
        riktning U = riktning.UP;
        riktning D = riktning.DOWN;
        riktning.R = riktning.RIGHT;
        riktning.L = riktning.LEFT;

    }
}

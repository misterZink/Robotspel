package spel.robots;

public class Geopard extends Robot implements Moveable {

    /**
     * Default konstruktor för geopard
     */

    private boolean isHungry;
    private boolean isTired;

    private int antalVila;
    private final int antalVilaFull;

    /**
     * Overload konstruktor för geopard
     */

    public Geopard() {
        super.displaySymbol = 'G';
        isHungry = true;
        isTired = false;
        antalVila = 0;
        antalVilaFull = 3;
    }

    /**
     * geopardens beteende, geoparden blir mätt efter den har ätit
     * @param z
     */
    public void eatZebra(Zebra z) {
        if (!z.isDead()) {
            z.setDead(true);
            setHungry(false);
        }
    }

    /**
     * Geoparden är hungrig när den börjar.
     * @return
     */
    public boolean isHungry() {
        return isHungry;
    }

    /**
     * Geoparden gå som vanlig vid normalläge(1 steg), står still när den är mätt.
     * @param hungry
     */
    public void setHungry(boolean hungry) {
        if (hungry) {
            antalVila = 0;
            setAntalSteg(1);
        } else {
            antalVila = antalVilaFull;
            setAntalSteg(0);
        }
        isHungry = hungry;
    }

    /**
     *  Geoparden är trött.
     * @return
     */
    public boolean isTired() {
        return isTired;
    }

    /**
     * När geoparden är trött gå den 1 steg, annars den gå den 6 steg.
     * @param tired
     */
    public void setTired(boolean tired) {
        if (tired) {
            setAntalSteg(1);
        } else {
            setAntalSteg(6);
        }
        this.isTired = tired;
    }

    /**
     *  Antal vila returneras
     * @return
     */
    public int getAntalVila() {
        return antalVila;
    }

    @Override
    public void update() {
        super.update();

        if (robotTarget != null) {
            if (isHungry) {
                eatZebra((Zebra) robotTarget);
                System.out.println("Gepard x: " + this.getPositionX() + " y: " + this.getPositionY() + " åt Zebra x: " + robotTarget.getPositionX() + " y: " + robotTarget.getPositionY());
                robotTarget = null;
            }
        }


        if (!isHungry) {
            if (antalVila > 0) {
                antalVila--;
            }
            if (antalVila == 0) {
                setHungry(true);
                System.out.println("Geparden är nu hungrig");
            }
        }
    }

    /**
     * Bekräfta att geoparden är hungrig om den är hungrig(returneras).
      * @return
     */
    public boolean move() {
        if (!isHungry) {
            return true;
        } else {
            return false;
        }
    }
}

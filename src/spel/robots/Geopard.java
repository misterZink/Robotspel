package spel.robots;

public class Geopard extends Robot {

    private boolean isHungry;
    private boolean isTired;

    private int antalVila;
    private final int antalVilaFull;

    public Geopard() {
        super.displaySymbol = 'G';
        isHungry = true;
        isTired = false;
        antalVila = 0;
        antalVilaFull = 2;
    }

    public void eatZebra(Zebra z) {
        z.setDead(true);
        setHungry(false);
    }

    public boolean isHungry() {
        return isHungry;
    }

    public void setHungry(boolean hungry) {
        if (hungry) {
            antalVila = 0;
        } else {
            antalVila = antalVilaFull;
        }
        isHungry = hungry;
    }

    public boolean isTired() {
        return isTired;
    }

    public void setTired(boolean tired) {
        if (tired) {
            setAntalSteg(1);
        } else {
            setAntalSteg(6);
        }
        isTired = tired;
    }

    public int getAntalVila() {
        return antalVila;
    }
}

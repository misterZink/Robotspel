package spel.robots;

public class Geopard extends Robot {

    private boolean isHungry;
    private boolean isTired;

    private int antalVila;

    public Geopard() {
        super.displaySymbol = 'G';
        isHungry = true;
        isTired = false;
        antalVila = 0;
    }

    public void eatZebra(Zebra z) {
        z.setDead(true);
    }

    public boolean isHungry() {
        return isHungry;
    }

    public void setHungry(boolean hungry) {
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

    public void setAntalVila(int antalVila) {
        this.antalVila = antalVila;
    }


}

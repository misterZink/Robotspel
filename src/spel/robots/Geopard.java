package spel.robots;

public class Geopard extends Robot implements Moveable {

    private boolean isHungry;
    private boolean isTired;

    private int antalVila;
    private final int antalVilaFull;

    public Geopard() {
        super.displaySymbol = 'G';
        isHungry = true;
        isTired = false;
        antalVila = 0;
        antalVilaFull = 3;
    }

    public void eatZebra(Zebra z) {
        if (isHungry) {
            z.setDead(true);
            setHungry(false);
        }

    }

    public boolean isHungry() {
        return isHungry;
    }

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

    public boolean isTired() {
        return isTired;
    }

    public void setTired(boolean tired) {
        if (tired) {
            setAntalSteg(1);
        } else {
            setAntalSteg(6);
        }
        this.isTired = tired;
    }

    public int getAntalVila() {
        return antalVila;
    }

    @Override
    public void update() {
        super.update();

        if (robotTarget != null) {
            eatZebra((Zebra) robotTarget);

            System.out.println("Gepard x: " + this.getPositionX() + " y: " + this.getPositionY() + " åt Zebra x: " + robotTarget.getPositionX() + " y: " + robotTarget.getPositionY());
            robotTarget = null;
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

    public boolean move() {
        if (!isHungry) {
            return true;
        } else {
            return false;
        }
    }
}

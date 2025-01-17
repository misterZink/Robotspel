package spel;

import spel.playfield.Playfield;
import spel.robots.Geopard;
import spel.robots.Robot;
import spel.robots.Zebra;
import spel.robots.directions.Direction;

import java.io.IOException;
import java.util.Scanner;

public class Robotspel {
    private int zebraAmount = 0, geopardAmount = 0; // Starta spelet med 0 zebra och 0 geopard
    private int frames = 1; // frame = omgång

    private Playfield playfield; // skapa ett minne till playfield

    /**
     * I loppen när den inte finmns någon robot ska användaren mata in antal Zebra
     * geopard samt playfield storlek på spelplanen.
     */
    public Robotspel() {
        runGame();
    }

    private void runGame() {
        while (!countRobots()) {
            askAmountOfRobots();
        }

        askForPlayfieldSize();

        /* sant att spelet ska köras igång
        försa rundan pågår
        om det inte är den första runda så uppdateras Robotstatus,
        Kontrollera vem som är bredvid roboten (granne) och
        robot tar en random steg, antingen up,ner, vänster eller höger */

        boolean exit = false;
        boolean isFirstRun = true;
        while (!exit) {
            if (!isFirstRun) {
                updateDirectionForAllRobots();
                updateDirectionIfRobotAtEdge();
                updateAllRobots();
                getNextRobotInDirection();
                moveRobotsInDirection();
                deleteDeadZebras();
                moveCheetahsWithUpdatedPosition();
            }

            /* Skriver ut playfield på konsolen, inväntar input från
             * användaren och då är det inte längre första runda.
             * fram räknar antal omgångar */
            printPlayfield();
            waitForInput();

            if (isFirstRun) {
                isFirstRun = false;
            }


            exit = !countRobots();
            frames++;
        }

        final String gameQuitMsg = "- Game Quit! -";
        System.out.println(gameQuitMsg);
    }

    /**
     * Ta bort zebra från array om död. Minska antalet zebror.
     *
     * @param x
     * @param y
     */
    private void deleteDeadZebra(int x, int y) {
        if (playfield.getRobots()[x][y].getDisplaySymbol() == 'Z') {
            Zebra zebra = (Zebra) playfield.getRobots()[x][y];

            if (zebra.isDead()) {
                zebraAmount--;
                playfield.getRobots()[x][y] = null;
            }
        }

    }

    /**
     * Wait for any input into console.
     * Inväntar input från användaren (metod).
     */
    private void waitForInput() {
        final String pressKeyMsg = "- Press any key -";
        System.out.println(pressKeyMsg);
        Scanner scanner = new Scanner(System.in);
        scanner.nextLine();
    }

    /**
     * Räknar antal robotar, returnerar false om antalet är mindre än noll eller geoparder är fler än zebror.
     *
     * @return
     */
    private boolean countRobots() {
        if (zebraAmount <= 0 || geopardAmount <= 0) {
            final String warningAmountOfRobots = "The amount of Zebras and Geopards must be more than 0.";
            System.out.println(warningAmountOfRobots);
            return false;
        }

        if (geopardAmount > zebraAmount) {
            final String warningMoreZebrasThanGeopards = "There must exist more Zebras than Geopards.";
            System.out.println(warningMoreZebrasThanGeopards);
            return false;
        }

        return true;
    }

    /**
     * Ber spelaren mata in antal geoparder och zebror.
     */
    private void askAmountOfRobots() {
        final Scanner scanner = new Scanner(System.in);

        final String questionZebras = "How many zebras?";
        System.out.println(questionZebras);
        this.zebraAmount = scanner.nextInt();

        final String questionGeopards = "How many geopards?";
        System.out.println(questionGeopards);
        this.geopardAmount = scanner.nextInt();
    }

    /**
     * Frågar efter storleken på spelplanen.
     */
    private void askForPlayfieldSize() {
        final Scanner scanner = new Scanner(System.in);

        final String questionPlayfieldWidth = "How wide should playfield be?";
        System.out.println(questionPlayfieldWidth);
        final int width = scanner.nextInt();

        final String questionPlayfieldHeight = "How tall should playfield be?";
        System.out.println(questionPlayfieldHeight);
        final int height = scanner.nextInt();

        playfield = new Playfield(width, height);
        playfield.spawnGeopards(geopardAmount);
        playfield.spawnZebras(zebraAmount);
    }

    /**
     * Då spelplanen består av en 2D array printas spelplanen inte som på vanligt vis.
     * Varje cell åt höger är egentligen en cell neråt.
     */
    private void printPlayfield() {
        final String gameStatusMessage = "- Playfield - Zebras left: " + zebraAmount + " Frames: " + frames;
        System.out.println(gameStatusMessage);

        for (Robot[] x : playfield.getRobots()) {
            for (Robot y : x) {
                if (y != null) {
                    System.out.print(y.getDisplaySymbol() + " ");
                } else {
                    System.out.print("# ");
                }
            }
            System.out.println();
        }
    }

    /**
     * Ska användas för att få vilken robot som är framför denna robot.
     * Metoden som kontrollera vem som står bredvid roboten (granne)
     */
    public void getNextRobotInDirection() {
        for (int x = 0; x < playfield.getRobots().length; x++) {
            for (int y = 0; y < playfield.getRobots()[x].length; y++) {

                Robot otherRobot = null;
                final Robot currentRobot = playfield.getRobots()[x][y];

                // check for neighbour
                if (currentRobot != null) {
                    switch (currentRobot.getDirection()) {
                        case DOWN:
                            if (x != 0) {
                                otherRobot = playfield.getRobots()[x - 1][y];
                            }
                            break;
                        case UP:
                            if (x != playfield.getRobots().length - 1) {
                                otherRobot = playfield.getRobots()[x + 1][y];
                            }
                            break;
                        case LEFT:
                            if (y != 0) {
                                otherRobot = playfield.getRobots()[x][y - 1];
                            }
                            break;
                        case RIGHT:
                            if (y != playfield.getRobots()[x].length - 1) {
                                otherRobot = playfield.getRobots()[x][y + 1];
                            }
                            break;
                    }

                    if (otherRobot != null) {
                        if (otherRobot.getDisplaySymbol() == 'Z') {
                            currentRobot.setRobotTarget(otherRobot);
                        }
                    }

                }
            }
        }
    }

    /**
     * Uppdaterar alla robotar.
     */
    private void updateAllRobots() {
        for (int x = 0; x < playfield.getRobots().length; x++) {
            for (int y = 0; y < playfield.getRobots()[x].length; y++) {
                if (playfield.getRobots()[x][y] != null) {
                    playfield.getRobots()[x][y].update();
                }
            }
        }
    }

    /**
     * för varje robot, ta bort död zebra
     */
    private void deleteDeadZebras() {
        for (int x = 0; x < playfield.getRobots().length; x++) {
            for (int y = 0; y < playfield.getRobots()[x].length; y++) {
                if (playfield.getRobots()[x][y] != null) {
                    deleteDeadZebra(x, y);
                }
            }
        }
    }

    /**
     * Första array är x.
     * Array inuti array är y.
     * Om man tänker som ett koordinatsystem x/y så blir det lite omvänt:
     * Upp: x-
     * Ner: x+
     * Vänster: y-
     * Höger: y+
     */
    private void moveRobotsInDirection() {
        for (int x = 0; x < playfield.getRobots().length; x++) {
            for (int y = 0; y < playfield.getRobots()[x].length; y++) {

                if (playfield.getRobots()[x][y] != null) {
                    if (playfield.getRobots()[x][y].getAntalSteg() != 0) {
                        final Robot currentRobot = playfield.getRobots()[x][y];

                        switch (currentRobot.getDirection()) {
                            case UP:
                                if (x != 0) {
                                    if (playfield.getRobots()[x - 1][y] == null) {
                                        playfield.getRobots()[x - 1][y] = currentRobot;
                                        playfield.getRobots()[x][y] = null;
                                    }
                                }
                                break;
                            case DOWN:
                                if (x < playfield.getRobots().length - 1) {
                                    if (playfield.getRobots()[x + 1][y] == null) {
                                        playfield.getRobots()[x + 1][y] = currentRobot;
                                        playfield.getRobots()[x][y] = null;
                                    }
                                }
                                break;
                            case LEFT:
                                if (y != 0) {
                                    if (playfield.getRobots()[x][y - 1] == null) {
                                        playfield.getRobots()[x][y - 1] = currentRobot;
                                        playfield.getRobots()[x][y] = null;
                                    }
                                }
                                break;
                            case RIGHT:
                                if (y < playfield.getRobots()[x].length - 1) {
                                    if (playfield.getRobots()[x][y + 1] == null) {
                                        playfield.getRobots()[x][y + 1] = currentRobot;
                                        playfield.getRobots()[x][y] = null;
                                    }
                                }
                                break;
                        }

                        currentRobot.setPositionX(x);
                        currentRobot.setPositionY(y);

                        currentRobot.setAntalSteg(currentRobot.getAntalSteg() - 1);
                    }
                }
            }
        }
    }

    /**
     * Kollar ifall gepard har fått ny position, isåfall får den nya positionen.
     */
    private void moveCheetahsWithUpdatedPosition() {
        for (int x = 0; x < playfield.getRobots().length; x++) {
            for (int y = 0; y < playfield.getRobots()[x].length; y++) {

                if (playfield.getRobots()[x][y] != null) {

                    if (playfield.getRobots()[x][y].getDisplaySymbol() == 'G') {
                        final Geopard tempGeo = (Geopard) playfield.getRobots()[x][y];

                        if (tempGeo.newPosSet) {
                            playfield.getRobots()[tempGeo.newPosX][tempGeo.newPosY] = tempGeo;
                            playfield.getRobots()[x][y] = null;
                            tempGeo.newPosSet = false;
                        }
                    }
                }
            }
        }
    }

    /**
     * Uppdatera direction för alla robotar.
     */
    private void updateDirectionForAllRobots() {
        for (int x = 0; x < playfield.getRobots().length; x++) {
            for (int y = 0; y < playfield.getRobots()[x].length; y++) {
                if (playfield.getRobots()[x][y] != null) {
                    playfield.getRobots()[x][y].decideDirection();
                }
            }
        }
    }

    /**
     * Om roboten är en av kanterna och direction pekar utåt ändra direction: vänd 90 grader.
     */
    private void updateDirectionIfRobotAtEdge() {
        for (int x = 0; x < playfield.getRobots().length; x++) {
            for (int y = 0; y < playfield.getRobots()[x].length; y++) {
                if (playfield.getRobots()[x][y] != null) {
                    final Robot currentRobot = playfield.getRobots()[x][y];

                    if (y == 0) { // längst åt vänster
                        if (currentRobot.getDirection() == Direction.LEFT) {
                            currentRobot.setDirection(Direction.DOWN);
                            //System.out.println("Robot X= " + x + " Y= " + y + " försökte gå ut på VÄNSTER sidan! 1");
                            if (x == playfield.getRobots().length - 1) {
                                currentRobot.setDirection(Direction.RIGHT);
                                //System.out.println("Robot X= " + x + " Y= " + y + " försökte gå ut på VÄNSTER sidan! 2");
                            }
                        }
                    } else if (y == playfield.getRobots()[x].length - 1) { // längst åt höger
                        if (currentRobot.getDirection() == Direction.RIGHT) {
                            currentRobot.setDirection(Direction.UP);
                            //System.out.println("Robot X= " + x + " Y= " + y + " försökte gå ut på HÖGRA sidan! 1");
                            if (x == 0) {
                                currentRobot.setDirection(Direction.LEFT);
                                //System.out.println("Robot X= " + x + " Y= " + y + " försökte gå ut på HÖGRA sidan! 2");
                            }
                        }
                    }

                    if (x == 0) { // längst till upp
                        if (currentRobot.getDirection() == Direction.UP) {
                            currentRobot.setDirection(Direction.LEFT);
                            //System.out.println("Robot X= " + x + " Y= " + y + " försökte gå ut på ÖVRE sidan! 1");
                            if (y == 0) {
                                currentRobot.setDirection(Direction.DOWN);
                                //System.out.println("Robot X= " + x + " Y= " + y + " försökte gå ut på ÖVRE sidan! 2");
                            }
                        }
                    } else if (x == playfield.getRobots().length - 1) { // längst ner
                        if (currentRobot.getDirection() == Direction.DOWN) {
                            currentRobot.setDirection(Direction.RIGHT);
                            //System.out.println("Robot X= " + x + " Y= " + y + " försökte gå ut på UNDRE sidan! 1");
                            if (y == playfield.getRobots()[x].length - 1) {
                                currentRobot.setDirection(Direction.UP);
                                //System.out.println("Robot X= " + x + " Y= " + y + " försökte gå ut på UNDRE sidan! 2");
                            }
                        }
                    }
                }
            }
        }
    }
}

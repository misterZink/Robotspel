package spel;

import spel.playfield.Playfield;
import spel.robots.Robot;
import spel.robots.directions.Direction;

import java.util.Scanner;

public class Robotspel {
    int zebraAmount = 0, geopardAmount = 0;
    int frames = 1;

    private Playfield playfield;

    public Robotspel() {
        while (!countRobots()) {
            askAmountOfRobots();
        }

        askForPlayfieldSize();

        boolean exit = false;
        //boolean fistRun = true;
        while (!exit) {

            //if (!fistRun) {
                for (Robot[] robotX : playfield.getRobots()) {
                    for (Robot robot : robotX) {
                        if (robot != null) {
                            moveRobotWithDirection(robot);
                        }
                    }
                }
            //}

            printPlayfield();
            waitForInput();
            frames++;

            //fistRun = false;
        }
    }

    /**
     * Wait for any input into console.
     */
    private void waitForInput() {
        try {
            System.in.read();
        } catch (Exception e) {
            e.printStackTrace();
        }
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

    private void printPlayfield() {
        final String playfieldAndFrmes = "-PLAYFIELD- Frames: " + frames;
        System.out.println(playfieldAndFrmes);

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

        getNextRobotInDirection();
    }

    public void getNextRobotInDirection() { //skicka in direciton enum
        for (int y = 0; y < playfield.getRobots().length; y++) {
            for (int x = 0; x < playfield.getRobots()[y].length; x++) {

                final Robot otherRobot;
                final Robot currentRobot = playfield.getRobots()[y][x];

                int direction = 3; // 0 up, 1, down, 2, left, 3 right

                // check for neighbour
                if (currentRobot != null) {

                    switch (direction) {
                        case 0:
                            if (y != 0) {
                                otherRobot = playfield.getRobots()[y - 1][x];

                                if (otherRobot != null) {
                                    System.out.println(otherRobot.getClass().getSimpleName() + " @x: " + x + " y: " + (y - 1) + " norr om "
                                            + currentRobot.getClass().getSimpleName());
                                }
                            }
                            break;
                        case 1:
                            if (y != playfield.getRobots().length - 1) {
                                otherRobot = playfield.getRobots()[y + 1][x];

                                if (otherRobot != null) {
                                    System.out.println(otherRobot.getClass().getSimpleName() + " @x: " + x + " y: " + (y + 1) + " söder om "
                                            + currentRobot.getClass().getSimpleName());
                                }
                            }
                            break;
                        case 2:
                            if (x != 0) {
                                otherRobot = playfield.getRobots()[y][x - 1];

                                if (otherRobot != null) {
                                    System.out.println(otherRobot.getClass().getSimpleName() + " @x: " + (x - 1) + " y: " + y + " väst om "
                                            + currentRobot.getClass().getSimpleName());
                                }
                            }
                            break;
                        case 3:
                            if (x != playfield.getRobots()[y].length - 1) {
                                otherRobot = playfield.getRobots()[y][x + 1];

                                if (otherRobot != null) {
                                    System.out.println(otherRobot.getClass().getSimpleName() + " @x: " + (x + 1) + " y: " + y + " öst om "
                                            + currentRobot.getClass().getSimpleName());
                                }
                            }
                            break;
                    }
                }
            }
        }
    }

    private void moveRobotWithDirection(final Robot robot) {

            switch (robot.getDirection()) {
                case UP:
                    for (int y = 0; y < playfield.getRobots().length; y++) {
                        for (int x = 0; x < playfield.getRobots()[y].length; x++) {
                            if (robot == playfield.getRobots()[y][x]) {
                                if (y != 0) {
                                    if (playfield.getRobots()[y - 1][x] == null) {
                                        playfield.getRobots()[y - 1][x] = robot;
                                        playfield.getRobots()[y][x] = null;
                                    }
                                }
                            }
                        }
                    }

                    break;
                case DOWN:

                    break;
                case LEFT:

                    break;
                case RIGHT:

                    break;

        }
    }
}

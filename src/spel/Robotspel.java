package spel;

import spel.playfield.Playfield;
import spel.robots.Robot;

import java.io.IOException;
import java.util.Random;
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
        while (!exit) {
            printPlayfield();
            waitForInput();
            frames++;
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
    }
}

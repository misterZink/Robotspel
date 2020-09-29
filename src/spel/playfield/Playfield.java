package spel.playfield;

import spel.robots.Geopard;
import spel.robots.Robot;
import spel.robots.Zebra;

import java.util.Random;

public class Playfield {
    /**
     * 7 % att roboten spawnas
     */
    private Robot[][] robots;
    final int spawnIntLimit = 100;
    final int spawnChance = 7;

    /**
     * retunera roboten tillbacks till spelplanen
     * @return
     */
    public Robot[][] getRobots() {
        return robots;
    }

    /**
     * Standard konstruktor för klassen
     */
    public Playfield() {
    }

    /**
     * En konstruktor för klassen som tar in bredd och höjd för spelplanen
     * @param width
     * @param height
     */
    public Playfield(final int width, final int height) {
        robots = new Robot[width][height];
    }

    /**
     * Spawna antal rätt antal geoparder.
     * @param amountOfGeopards
     */
    public void spawnGeopards(final int amountOfGeopards) {
        int cheetahsSpawned = 0;
        boolean allCheetahsSpawned = false;
        Random random = new Random();

        while (!allCheetahsSpawned) {
            for (int j = 0; j < robots.length; j++) {
                for (int k = 0; k < robots[j].length; k++) {

                    if (cheetahsSpawned < amountOfGeopards) {
                        if (robots[j][k] == null) {
                            final int toSpawn = random.nextInt(spawnIntLimit);

                            if (toSpawn < spawnChance) {
                                robots[j][k] = new Geopard();
                                cheetahsSpawned++;
                                System.out.println("Geopard " + cheetahsSpawned + "/" + amountOfGeopards + " spawned @" + " [" + j + "][" + k + "]");
                            }
                        }
                    } else {
                        allCheetahsSpawned = true;
                    }
                }
            }
        }
    }

    /**
     * Spawna antal rätt antal Zebra.
     * @param amountOfZebras
     */
    public void spawnZebras(final int amountOfZebras) {
        int zebrasSpawned = 0;
        boolean allZebrasSpawned = false;
        Random random = new Random();

        while (!allZebrasSpawned) {
            for (int j = 0; j < robots.length; j++) {
                for (int k = 0; k < robots[j].length; k++) {

                    if (zebrasSpawned < amountOfZebras) {
                        if (robots[j][k] == null) {
                            final int toSpawn = random.nextInt(spawnIntLimit);

                            if (toSpawn < spawnChance) {
                                robots[j][k] = new Zebra();
                                zebrasSpawned++;
                                System.out.println("Zebra " + zebrasSpawned + "/" + amountOfZebras + " spawned @" + " [" + j + "][" + k + "]");
                            }
                        }
                    } else {
                        allZebrasSpawned = true;
                    }
                }
            }
        }
    }

}

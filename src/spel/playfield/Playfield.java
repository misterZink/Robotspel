package spel.playfield;

import spel.robots.Geopard;
import spel.robots.Robot;
import spel.robots.Zebra;

import java.util.Random;

public class Playfield {

    private Robot[][] robots;
    final int spawnIntLimit = 100;
    final int spawnChance = 7;

    /**
     * retunera robotar
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
            for (int x = 0; x < robots.length; x++) {
                for (int y = 0; y < robots[x].length; y++) {

                    if (cheetahsSpawned < amountOfGeopards) {
                        if (robots[x][y] == null) {
                            final int toSpawn = random.nextInt(spawnIntLimit);

                            if (toSpawn < spawnChance) {
                                robots[x][y] = new Geopard();
                                cheetahsSpawned++;
                                System.out.println("Geopard " + cheetahsSpawned + "/" + amountOfGeopards + " spawned @" + " [" + x + "][" + y + "]");
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
     * Spawna antal rätt antal zebror.
     * @param amountOfZebras
     */
    public void spawnZebras(final int amountOfZebras) {
        int zebrasSpawned = 0;
        boolean allZebrasSpawned = false;
        Random random = new Random();

        while (!allZebrasSpawned) {
            for (int x = 0; x < robots.length; x++) {
                for (int y = 0; y < robots[x].length; y++) {

                    if (zebrasSpawned < amountOfZebras) {
                        if (robots[x][y] == null) {
                            final int toSpawn = random.nextInt(spawnIntLimit);

                            if (toSpawn < spawnChance) {
                                robots[x][y] = new Zebra();
                                zebrasSpawned++;
                                System.out.println("Zebra " + zebrasSpawned + "/" + amountOfZebras + " spawned @" + " [" + x + "][" + y + "]");
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

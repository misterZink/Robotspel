package spel.playfield;

import spel.robots.Geopard;
import spel.robots.Robot;
import spel.robots.Zebra;

import java.util.Random;

public class Playfield {

    private Robot[][] robots;

    public Robot[][] getRobots() {
        return robots;
    }

    public Playfield() {
    }

    public Playfield(final int width, final int height) {
        robots = new Robot[width][height];
    }

    public void spawnGeopards(final int amountOfGeopards) {
        int geopardsSpawned = 0;
        Random random = new Random();

        for (int j = 0; j < robots.length; j++) {
            for (int k = 0; k < robots[j].length; k++) {

                if (geopardsSpawned < amountOfGeopards) {
                    if (robots[j][k] == null) {
                        final boolean toSpawn = random.nextBoolean();

                        if (toSpawn) {
                            robots[j][k] = new Geopard();
                            geopardsSpawned++;
                            System.out.println("Geopard " + geopardsSpawned + "/" + amountOfGeopards + " spawned @" + " [" + j + "][" + k + "]");
                        }
                    }
                }
            }
        }

    }

    public void spawnZebras(final int amountOfZebras) {
        int zebrasSpawned = 0;
        Random random = new Random();

        for (int j = 0; j < robots.length; j++) {
            for (int k = 0; k < robots[j].length; k++) {

                if (zebrasSpawned < amountOfZebras) {
                    if (robots[j][k] == null) {
                        final boolean toSpawn = random.nextBoolean();

                        if (toSpawn) {
                            robots[j][k] = new Zebra();
                            zebrasSpawned++;
                            System.out.println("Zebra " + zebrasSpawned + "/" + amountOfZebras + " spawned @" + " [" + j + "][" + k + "]");
                        }
                    }
                }
            }
        }
    }
}

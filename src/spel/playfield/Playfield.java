package spel.playfield;

import spel.robots.Robot;

public class Playfield {

    private Robot[][] robots;

    public Playfield() { }

    public Playfield(final int width, final int height) {
        robots = new Robot[width][height];
    }



}

package edu.pitt.cs;

import java.util.Random;
public class Batter {
    private Random random = new Random();

    /**
     * Returns whether bat swung, with 50/50 chance of swinging.
     *
     * @return true if bat is swung or false if otherwise
     */
    public boolean swingBat() {
        return (random.nextInt(2) == 1);
    }
}
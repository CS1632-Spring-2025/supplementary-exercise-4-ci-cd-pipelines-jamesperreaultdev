package edu.pitt.cs;

public class BallGame {
    private Pitcher pitcher;
    private Batter batter;

    public BallGame(Pitcher pitcher, Batter batter) {
        this.pitcher = pitcher;
        this.batter = batter;
    }

    public String pitch() {
        boolean isStrike = pitcher.throwBall();
        boolean swung = batter.swingBat();
        if (isStrike && swung) {
            pitcher.addStress(10);
            return "hit";
        } else if (isStrike && !swung) {
            return "strike";
        } else if (!isStrike && swung) {
            return "strike";
        } else { // !isStrike && !swung
            pitcher.addStress(1);
            return "ball";
        }
    }
}
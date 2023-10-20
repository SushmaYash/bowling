package com.example.bowling.models;

public class Frame {
    private int roll1;
    private int roll2;
    private boolean isStrike;
    private boolean isSpare;
    private int bonusRoll1;
    private int bonusRoll2;
    private int frameScore;


    public Frame() {
        this.roll1 = -1;
        this.roll2 = -1;
        this.isStrike = false;
        this.isSpare = false;
        this.bonusRoll1 = -1;
        this.bonusRoll2 = -1;
        this.frameScore = 0;
    }

    public int getFrameScore() {
        return frameScore;
    }

    public int getBonusRoll1() {
        return bonusRoll1;
    }

    public void setBonusRoll1(int bonusRoll1) {
        this.bonusRoll1 = bonusRoll1;
    }

    public int getBonusRoll2() {
        return bonusRoll2;
    }

    public void setBonusRoll2(int bonusRoll2) {
        this.bonusRoll2 = bonusRoll2;
    }

    public void setFrameScore(int frameScore) {
        this.frameScore = frameScore;
    }

    public int getRoll1() {
        return roll1;
    }

    public int getRoll2() {
        return roll2;
    }

    public boolean isStrike() {
        return isStrike;
    }

    public boolean isSpare() {
        return isSpare;
    }

    public void setRoll1(int roll1) {
        this.roll1 = roll1;
    }

    public void setRoll2(int roll2) {
        this.roll2 = roll2;
    }

    public void setStrike(boolean strike) {
        isStrike = strike;
    }

    public void setSpare(boolean spare) {
        isSpare = spare;
    }

    public boolean isComplete() {
        return (roll1 != -1 && roll2 != -1) || (isStrike) || (isSpare);
    }

}


package com.example.bowling.data;

public class FrameData {
    private int cumulativeScore;
    private Integer pins;
    private String textContent;
    public String getTextContent() {
        return textContent;
    }

    public void setTextContent(String textContent) {
        this.textContent = textContent;
    }

    public int getCumulativeScore() {
        return cumulativeScore;
    }

    public void setCumulativeScore(int cumulativeScore) {
        this.cumulativeScore = cumulativeScore;
    }

    public Integer getPins() {
        return pins;
    }

    public void setPins(Integer pins) {
        this.pins = pins;
    }
}
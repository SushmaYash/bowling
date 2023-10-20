package com.example.bowling.data;

import java.util.List;

public class ScoreBoardData {
    private List<FrameData> frames;
    private int totalScore;

    public List<FrameData> getFrames() {
        return frames;
    }

    public void setFrames(List<FrameData> frames) {
        this.frames = frames;
    }

    public int getTotalScore() {
        return totalScore;
    }

    public void setTotalScore(int totalScore) {
        this.totalScore = totalScore;
    }
}

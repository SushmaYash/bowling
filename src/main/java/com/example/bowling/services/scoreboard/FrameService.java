package com.example.bowling.services.scoreboard;

import com.example.bowling.models.Frame;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FrameService {
    public void addRoll(Frame frame, int pins) {
        if (frame.getRoll1() == -1) {
            frame.setRoll1(pins);
            if (frame.getRoll1() == 10) {
                frame.setStrike(true);
            }
        } else if (frame.getRoll2() == -1 && !frame.isStrike()) {
            frame.setRoll2(pins);
            if (frame.getRoll1() + frame.getRoll2() == 10) {
                frame.setSpare(true);
            }
        } else if (frame.isStrike()) {
            if (frame.getBonusRoll1() == -1) {
                frame.setBonusRoll1(pins);
            } else {
                frame.setBonusRoll2(pins);
            }
        } else if (frame.isSpare()) {
            frame.setBonusRoll1(pins);
        }

    }


    public int calculateTenthFrame(Frame frame) {
        int frameScore = 0;
        if (frame.isStrike()) {
            frameScore = 10 + getFrameRollScore(frame.getBonusRoll1(), frame.getBonusRoll2());
        } else if (frame.isSpare()) {
            frameScore = 10 + ((frame.getBonusRoll1() != -1) ? frame.getBonusRoll1() : 0);
        }
        frame.setFrameScore(frameScore);
        return frameScore;
    }

    public int calculateScore(List<Frame> frames, Frame currentFrame) {
        int frameScore = 0;
        if (!frames.isEmpty()) {
            if (currentFrame.isStrike()) {
                frameScore = 10 + getFrameScoreForStrike(frames);
            } else if (currentFrame.isSpare()) {
                frameScore = 10 + getFrameScoreForSpare(frames);
            } else {
                frameScore = getFrameRollScore(currentFrame.getRoll1(), currentFrame.getRoll2());
            }
        } else {
            frameScore = getFrameRollScore(currentFrame.getRoll1(), currentFrame.getRoll2());
        }
        currentFrame.setFrameScore(frameScore);
        return frameScore;
    }

    private int getFrameRollScore(int roll1, int roll2) {
        int totalPins = 0;
        if (roll1 != -1) {
            totalPins += roll1;
        }
        if (roll2 != -1) {
            totalPins += roll2;
        }
        return totalPins;
    }

    private int getFrameScoreForStrike(List<Frame> frames) {
        int bonus = 0;
        Frame nextFrame = frames.get(0);
        if (nextFrame.isStrike()) {
            bonus += nextFrame.getRoll1();
            if(nextFrame.getBonusRoll1() != -1){
                bonus+=nextFrame.getBonusRoll1();
                return bonus;
            }
            if (frames.size() > 1) {
                bonus += frames.get(1).getRoll1();
            }
        } else {
            bonus += getFrameRollScore(frames.get(0).getRoll1(), frames.get(0).getRoll2());
        }
        return bonus;
    }


    private int getFrameScoreForSpare(List<Frame> frames) {
        int bonus = 0;
        if (!frames.isEmpty()) {
            Frame nextFrame = frames.get(0);
            bonus += nextFrame.getRoll1();
        }
        return bonus;
    }

}
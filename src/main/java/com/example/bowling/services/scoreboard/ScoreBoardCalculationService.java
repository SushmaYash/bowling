package com.example.bowling.services.scoreboard;

import com.example.bowling.data.FrameData;
import com.example.bowling.data.ScoreBoardData;
import com.example.bowling.models.Frame;
import com.example.bowling.models.UserGame;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.List;

@Service
public class ScoreBoardCalculationService {

    private final FrameService frameService;

    @Autowired
    public ScoreBoardCalculationService(FrameService frameService) {
        this.frameService = frameService;
    }

    public void addRollForUser(String username, int pins) {
        UserGame.createNewFrameIfNecessary(username);
        Frame userFrame = UserGame.getFrames(username).get(UserGame.getFrames(username).size() - 1);
        frameService.addRoll(userFrame, pins);
    }

    public ScoreBoardData calculateScoreboardForUser(String username) {
        List<Frame> frames = UserGame.getFrames(username);
        List<FrameData> frameDataList = new LinkedList<>();
        int cumulativeFrameScore = 0;
        int frameSize = frames.size();
        for (int i = 0; i < frameSize; i++) {
            Frame frame = frames.get(i);
            if(i == 9){
                cumulativeFrameScore+=frameService.calculateTenthFrame(frame);
            }else{
                cumulativeFrameScore+=frameService.calculateScore(frames.subList(i+1, frameSize), frame);
            }

            String textContent = calculateTextContent(frame);
            FrameData frameData = new FrameData();
            frameData.setCumulativeScore(cumulativeFrameScore);
            frameData.setTextContent(textContent);
            frameData.setPins(getFirstPinHit(frame));
            frameDataList.add(frameData);
        }
        ScoreBoardData scoreboardData = new ScoreBoardData();
        scoreboardData.setFrames(frameDataList);
        scoreboardData.setTotalScore(cumulativeFrameScore);

        return scoreboardData;
    }

    private String calculateTextContent(Frame frame) {
        if (frame.isStrike()) {
            return "X";
        } else if (frame.isSpare()) {
            return "/";
        } else if (frame.getRoll1() == 0 && frame.getRoll2() == 0) {
            return "-";
        }
        return "";
    }

    private Integer getFirstPinHit(final Frame frame){
        if(frame.getRoll1() != -1 && frame.getRoll2() == -1 && !frame.isComplete()){
            return frame.getRoll1();
        }
        return null;
    }

}





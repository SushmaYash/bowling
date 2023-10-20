package com.example.bowling.services;


import com.example.bowling.models.Frame;
import com.example.bowling.services.scoreboard.FrameService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class FrameServiceTest {
    private FrameService frameService;

    @BeforeEach
    public void setUp() {
        frameService = new FrameService();
    }

    @Test
     void testAddRollWithStrike() {
        Frame frame = new Frame();
        frameService.addRoll(frame, 10);
        assertTrue(frame.isStrike());
    }

    @Test
     void testAddRollWithSpare() {
        Frame frame = new Frame();
        frameService.addRoll(frame, 5);
        frameService.addRoll(frame, 5);
        assertTrue(frame.isSpare());
    }

    @Test
     void testAddRollWithBonusRolls() {
        Frame frame = new Frame();
        frameService.addRoll(frame, 10);
        frameService.addRoll(frame, 7);
        frameService.addRoll(frame, 2);
        assertEquals(7, frame.getBonusRoll1());
        assertEquals(2, frame.getBonusRoll2());
    }

    @Test
     void testCalculateTenthFrameWithSpare() {
        Frame frame = new Frame();
        frame.setSpare(true);
        frame.setBonusRoll1(5);

        int frameScore = frameService.calculateTenthFrame(frame);
        assertEquals(10 + 5, frameScore);
    }

    @Test
     void testCalculateScoreWithFrames() {
        Frame frame1 = new Frame();
        frame1.setRoll1(4);
        frame1.setRoll2(3);

        Frame frame2 = new Frame();
        frame2.setStrike(true);
        frame2.setBonusRoll1(7);
        frame2.setBonusRoll2(2);

        List<Frame> frames = new ArrayList<>();
        frames.add(frame1);
        frames.add(frame2);

        int frameScore = frameService.calculateScore(frames, frame1);
        assertEquals(4 + 3, frameScore);
    }

    @Test
     void testCalculateScoreWithEmptyFrames() {
        Frame frame = new Frame();
        frame.setRoll1(5);
        frame.setRoll2(4);

        List<Frame> frames = new ArrayList<>();

        int frameScore = frameService.calculateScore(frames, frame);
        assertEquals(5 + 4, frameScore);
    }
}
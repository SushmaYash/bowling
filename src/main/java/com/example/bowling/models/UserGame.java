package com.example.bowling.models;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class UserGame {

    private UserGame(){}

    private static final Map<String, List<Frame>> userGameDetails = new ConcurrentHashMap<>();

    public static List<Frame> getFrames(final String username) {
        return userGameDetails.computeIfAbsent(username, k -> new LinkedList<>());
    }

    public static void createNewFrameIfNecessary(final String username){
        List<Frame> frames = getFrames(username);
        if (frames.isEmpty() || frames.get(frames.size() - 1).isComplete() && frames.size() < 10) {
            frames.add(new Frame());
        }
    }

    public static void createUserGame(final String userName){
            userGameDetails.put(userName, new LinkedList<>());
    }

    public static void addUserFrame(String username, Frame frame) {
        getFrames(username).add(frame);
    }

}
